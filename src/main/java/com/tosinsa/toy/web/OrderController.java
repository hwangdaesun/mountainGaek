package com.tosinsa.toy.web;

import com.tosinsa.toy.SessionConst;
import com.tosinsa.toy.domain.Item;
import com.tosinsa.toy.domain.Member;
import com.tosinsa.toy.domain.Order;
import com.tosinsa.toy.domain.OrderSearch;
import com.tosinsa.toy.service.ItemService;
import com.tosinsa.toy.service.OrderService;
import com.tosinsa.toy.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ItemService itemService;

    @GetMapping("/order/items/{itemId}")
    public String createOrderForm(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) Member loginMember, @PathVariable("itemId") Long itemId, Model model) {

        Item item = itemService.findOne(itemId);
        model.addAttribute("item",item);
        model.addAttribute("member", loginMember);
        OrderForm orderForm = new OrderForm();
        orderForm.setItemId(itemId);
        model.addAttribute("orderForm", orderForm);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(OrderForm orderForm, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Object memberId = session.getAttribute(SessionConst.LOGIN_USER);
        orderService.order((Long)memberId, orderForm.getItemId(), orderForm.getCount());
        // delivery 배송 준비 상태로 변경해야한다.
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) Member loginMember,Model model){
        List<Order> orders = orderService.findOrdersByMemberId(loginMember.getId());
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId){
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }

}

