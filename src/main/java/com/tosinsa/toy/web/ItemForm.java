package com.tosinsa.toy.web;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ItemForm {

    private Long itemId;
    private String name;
    private int price;
    private int stockQuantity;

}
