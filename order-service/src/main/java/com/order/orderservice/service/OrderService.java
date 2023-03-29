package com.order.orderservice.service;
import com.order.orderservice.dto.OrderRequest;
import com.order.orderservice.entity.Orders;


public interface OrderService {
    public String placeOrder(OrderRequest orderRequest);
}
