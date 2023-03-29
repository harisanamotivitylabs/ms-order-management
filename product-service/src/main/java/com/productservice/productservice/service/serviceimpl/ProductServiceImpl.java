package com.productservice.productservice.service.serviceimpl;

import com.productservice.productservice.dto.ProductRequest;
import com.productservice.productservice.dto.ProductResponse;
import com.productservice.productservice.entity.Product;
import com.productservice.productservice.repository.ProductRepository;
import com.productservice.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductResponse saveProduct(ProductRequest productRequest)
    {
        ProductResponse productResponse = modelMapper.map(productRepository.save(modelMapper.map(productRequest, Product.class)), ProductResponse.class);
        log.info("product {} saved",productResponse.getName());
        return productResponse;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(product -> modelMapper.map(product,ProductResponse.class)).collect(Collectors.toList());
    }
}
