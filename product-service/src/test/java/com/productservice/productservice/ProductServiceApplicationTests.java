package com.productservice.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productservice.productservice.controller.ProductController;
import com.productservice.productservice.dto.ProductRequest;
import com.productservice.productservice.entity.Product;
import com.productservice.productservice.repository.ProductRepository;
import com.productservice.productservice.service.serviceimpl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest

class ProductServiceApplicationTests {

	@Autowired
	private MockMvc  mockMvc;

	@MockBean
	private ProductRepository productRepository;
	@Autowired
	private ProductServiceImpl productService;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ObjectMapper objectMapper;
	@Test
	void contextLoads() {
	}
	@Test
	public void TestSaveProduct() throws Exception {
		ProductRequest productRequest=new ProductRequest("IPhone"," Mobile Iphone ",23002);

		when(productRepository.save(modelMapper.map(productRequest, Product.class))).thenReturn(new Product("Mobile@123Id","IPhone"," Mobile Iphone ",23002));

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/product/save").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(productRequest))).andReturn();
		assertEquals(mvcResult.getResponse().getStatus(),HttpStatus.CREATED);
	}

}
