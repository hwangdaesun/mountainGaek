package com.tosinsa.toy.service;

import com.tosinsa.toy.domain.Item;
import com.tosinsa.toy.domain.Member;
import com.tosinsa.toy.domain.Order;
import com.tosinsa.toy.domain.OrderItem;
import com.tosinsa.toy.domain.ItemRepository;
import com.tosinsa.toy.domain.OrderRepository;
import com.tosinsa.toy.domain.OrderSearch;
import com.tosinsa.toy.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        // 엔티티 조회
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalStateException("회원이 존재하지 않습니다."));
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalStateException("아이템이 존재하지 않습니다."));
        // 주문상품생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        // 주문생성
        Order order = Order.createOrder(member, orderItem);
        // 주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId){
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancel();
    }

    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAllByString(orderSearch);
    }


}
