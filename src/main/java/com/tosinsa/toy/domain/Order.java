package com.tosinsa.toy.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import static jakarta.persistence.FetchType.LAZY;


@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id") // 외래키 가져오기
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery; //배송정보


    private OrderStatus status; // 주문상태 [ORDER, CANCEL]

    //== 연관관계 메서드 ==//
    public void setMember(Member member){
        this.member = member; // User를 Order에 저장
        member.getOrders().add(this); // Order를 User에 저장 // ex) order.setUser(user) 한 줄로 양쪽 모두 저장 가능
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem); // OrderItem를 Order에 저장
        orderItem.setOrder(this); // Order를 orderItem에 저장
    }


    private void setOrderStatusToOrder(){
        this.status = OrderStatus.ORDER;
    }

    private void setOrderStatusToCancel(){
        this.status = OrderStatus.CANCEL;
    }

    //== 생성 메서드 ==//
    public static Order createOrder(Member member, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        for (OrderItem orderItem: orderItems){
            order.addOrderItem(orderItem);
        }
        order.setOrderStatusToOrder();
        return order;
    }

    //== 비즈니스 로직 ==//
    /**
     * 주문 취소
     */
    public void cancel(){
        this.setOrderStatusToCancel();
        for (OrderItem orderItem: orderItems){
            orderItem.cancel();
        }
    }

    //== 조회 로직 ==//

    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice(){
        int totalPrice = 0;
        for (OrderItem orderItem: orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}


