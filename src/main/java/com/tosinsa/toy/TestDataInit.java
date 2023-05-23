package com.tosinsa.toy;

import com.tosinsa.toy.domain.*;
import com.tosinsa.toy.service.ItemService;
import com.tosinsa.toy.service.MemberService;
import com.tosinsa.toy.service.OrderService;
import com.tosinsa.toy.web.ItemForm;
import com.tosinsa.toy.web.MemberForm;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberService memberService;
    private final ItemService itemService;
    private final OrderService orderService;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {

        System.out.println(" = ");
        /**
         * 테스트 유저 추가
         */

        MemberForm memberForm1 = new MemberForm();
        memberForm1.setEmail("dure@naver.com");
        memberForm1.setName("dure");
        memberForm1.setLoginId("dure");
        memberForm1.setPassword("1234");
        memberService.join(memberForm1);
        Member member1 = memberService.findOne(memberForm1.getLoginId());


        MemberForm memberForm2 = new MemberForm();
        memberForm2.setEmail("sase@naver.com");
        memberForm2.setName("sase");
        memberForm2.setLoginId("sase");
        memberForm2.setPassword("0000");
        memberService.join(memberForm2);
        Member member2 = memberService.findOne(memberForm2.getLoginId());

        /**
         * 테스트 상품 추가
         */
        ItemForm itemForm1 = new ItemForm();
        itemForm1.setName("itemA");
        itemForm1.setPrice(10000);
        itemForm1.setStockQuantity(10);

        ItemForm itemForm2 = new ItemForm();
        itemForm2.setName("itemB");
        itemForm2.setPrice(20000);
        itemForm2.setStockQuantity(20);

        itemService.saveItem(itemForm1);
        itemService.saveItem(itemForm2);

        /**
         * 테스트 주문 추가
         */

        orderService.order(1L,1L,3);

        orderService.order(2L,2L,5);

    }
}