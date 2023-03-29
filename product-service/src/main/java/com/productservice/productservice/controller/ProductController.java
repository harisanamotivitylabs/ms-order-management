package com.productservice.productservice.controller;

import com.productservice.productservice.dto.ProductRequest;
import com.productservice.productservice.dto.ProductResponse;
import com.productservice.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/save")
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody ProductRequest productRequest)
    {
       return new ResponseEntity<>(productService.saveProduct(productRequest), HttpStatus.CREATED);
    }
    @GetMapping("/getAllProducts")
    public List<ProductResponse> getAllProduct()
    {
        return productService.getAllProducts();
    }

}
