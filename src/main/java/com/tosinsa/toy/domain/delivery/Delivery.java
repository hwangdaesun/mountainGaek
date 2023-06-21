package com.tosinsa.toy.domain.delivery;

import com.tosinsa.toy.domain.Address;
import com.tosinsa.toy.domain.order.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //ENUM [READY(준비), COMP(배송)]

    public void setOrder(Order order){
        this.order = order;
    }
    @Builder()
    public Delivery(Order order, Address address){
        Assert.notNull(order,"order must not be null");
        Assert.notNull(address,"address not be null");
        this.order = order;
        this.address = address;
        this.status = DeliveryStatus.READY;
    }
    public Delivery createDelivery(Order order, Address address){
        Delivery delivery = Delivery.builder()
                .order(order)
                .address(address)
                .build();
        return delivery;
    }
    
    public void setDeliveryStatusToCOMP(){
        this.status = DeliveryStatus.COMP;
    }
}
