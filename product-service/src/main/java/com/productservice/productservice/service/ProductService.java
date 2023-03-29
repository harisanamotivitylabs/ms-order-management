package com.productservice.productservice.service;

import com.productservice.productservice.dto.ProductRequest;
import com.productservice.productservice.dto.ProductResponse;
import com.productservice.productservice.entity.Product;

import java.util.List;

public interface ProductService {
    public ProductResponse saveProduct(ProductRequest productRequest);
    public List<ProductResponse> getAllProducts();
}
