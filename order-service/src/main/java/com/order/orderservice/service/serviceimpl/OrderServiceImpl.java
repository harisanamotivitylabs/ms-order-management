package com.order.orderservice.service.serviceimpl;

import com.order.orderservice.dto.InventoryResponse;
import com.order.orderservice.dto.OrderRequest;
import com.order.orderservice.entity.OrderLineItems;
import com.order.orderservice.entity.Orders;
import com.order.orderservice.event.OrderPlacedEvent;
import com.order.orderservice.repository.OrderRepository;
import com.order.orderservice.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.
        stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private WebClient.Builder webBuilder;
    @Autowired
    private KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;
    @Override
    public String placeOrder(OrderRequest orderRequest) {
        Orders order = new Orders();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDto()
                .stream()
                .map(orderLineItemsDto -> modelMapper.map(orderLineItemsDto, OrderLineItems.class)).collect(Collectors.toList());
        order.setOrderLineItems(orderLineItemsList);

        //list of skuCode
        List<String> skuCodeList = order.getOrderLineItems().stream()
                .map(OrderLineItems::getSkuCode).collect(Collectors.toList());

        // calling inventory service is ordered item is there in inventory or not
        InventoryResponse[] inventoryResponseArray = webBuilder.build().get()
                .uri("http://INVENTORY-SERVICE/inventory/skuCode", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodeList).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        assert inventoryResponseArray != null;
        boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStoke);
        if(allProductsInStock) {
            OrderPlacedEvent event = new OrderPlacedEvent(order.getOrderNumber());

            Message<OrderPlacedEvent> message= MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC,"notificationTopic")
                            .build();
            kafkaTemplate.send(message);

             return "Order Placed successfully";
        }
        else {
            throw new IllegalArgumentException("product in out of stock please try again");
        }

    }
}
