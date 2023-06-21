package com.tosinsa.toy.domain;

import com.tosinsa.toy.domain.order.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;

}
