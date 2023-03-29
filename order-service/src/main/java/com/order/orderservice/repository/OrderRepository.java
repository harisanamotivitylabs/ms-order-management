package com.order.orderservice.repository;


import com.order.orderservice.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long> {
}
