package com.inventoryservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Inventory {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String skuCode;
    private int quantity;


}
