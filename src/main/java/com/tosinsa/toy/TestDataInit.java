package com.tosinsa.toy;

import com.tosinsa.toy.domain.member.Member;
import com.tosinsa.toy.service.ItemService;
import com.tosinsa.toy.service.MemberService;
import com.tosinsa.toy.service.OrderService;
import com.tosinsa.toy.web.ItemForm;
import com.tosinsa.toy.web.MemberForm;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


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
        memberForm1.setName("관리자");
        memberForm1.setLoginId("dure");
        memberForm1.setPassword("1234");
        memberService.specialJoin(memberForm1); // 관리자
        Member member1 = memberService.findOne(memberForm1.getLoginId());


        MemberForm memberForm2 = new MemberForm();
        memberForm2.setEmail("sase@naver.com");
        memberForm2.setName("돈 많은 등산가");
        memberForm2.setLoginId("sase");
        memberForm2.setPassword("0000");
        memberService.join(memberForm2); // 유저
        Member member2 = memberService.findOne(memberForm2.getLoginId());

//        /**
//         * 테스트 상품 추가
//         */
//
//        ItemForm itemForm1 = new ItemForm();
//        itemForm1.setName("알타이카 남성용 등산 운동 작업 트레킹화 AT-01");
//        itemForm1.setPrice(30000);
//        itemForm1.setStockQuantity(100);
//
//        ItemForm itemForm2 = new ItemForm();
//        itemForm2.setName("슈패리 방수 등산화 공용 트레킹화 노티 A1");
//        itemForm2.setPrice(60000);
//        itemForm2.setStockQuantity(20);
//
//        ItemForm itemForm3 = new ItemForm();
//        itemForm3.setName("유세븐 남성용 등산화 D002");
//        itemForm3.setPrice(20000);
//        itemForm3.setStockQuantity(200);
//
//        ItemForm itemForm4 = new ItemForm();
//        itemForm4.setName("공용 방수 등산화 트레킹화 작업화 NOFODAN");
//        itemForm4.setPrice(60000);
//        itemForm4.setStockQuantity(30);
//
//        ItemForm itemForm5 = new ItemForm();
//        itemForm5.setName("노크캠프 로이본버 보나티 가죽어퍼 방수 등산화 남성");
//        itemForm5.setPrice(50000);
//        itemForm5.setStockQuantity(500);
//
//        ItemForm itemForm6 = new ItemForm();
//        itemForm6.setName("아그네스 남성용 다이얼 트레일 러닝화 AG-906");
//        itemForm6.setPrice(60000);
//        itemForm6.setStockQuantity(20);
//
//        ItemForm itemForm7 = new ItemForm();
//        itemForm7.setName("노스랜드 다이얼 트레킹화 클라우드폼 쉐마");
//        itemForm7.setPrice(70000);
//        itemForm7.setStockQuantity(30);
//
//        ItemForm itemForm8 = new ItemForm();
//        itemForm2.setName("알타이카 남서용 다이얼 등산화 AT-8136");
//        itemForm2.setPrice(40000);
//        itemForm2.setStockQuantity(50);
//
//        itemService.saveItem(itemForm1);
//        itemService.saveItem(itemForm2);
//        itemService.saveItem(itemForm3);
//        itemService.saveItem(itemForm4);
//        itemService.saveItem(itemForm5);
//        itemService.saveItem(itemForm6);
//        itemService.saveItem(itemForm7);
//        itemService.saveItem(itemForm8);
//
//        /**
//         * 테스트 주문 추가
//         */
//
//        orderService.order(1L,1L,3);
//        orderService.order(1L,3L,1);
//        orderService.order(1L,5L,1);
//        orderService.order(1L,6L,2);
//        orderService.order(2L,2L,5);

    }
}