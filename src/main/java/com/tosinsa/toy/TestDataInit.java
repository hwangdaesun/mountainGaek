package com.tosinsa.toy;

import com.tosinsa.toy.domain.Address;
import com.tosinsa.toy.domain.Users;
import com.tosinsa.toy.domain.UsersRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class TestDataInit {


    private final UsersRepository usersRepository;

    @PostConstruct
    public void init() {

        System.out.println(" = ");

        /**
         * 테스트 유저 추가
         */

        Users users1 = new Users("martin","martin@naver.com", new Address("서울","달빛로","1234"));
        Users users2 = new Users("dennis","dennis@naver.com", new Address("부산","새벽로","1534"));
        Users users3 = new Users("sophia","sophia@naver.com", new Address("광주", "도로","0000"));
        Users users4 = new Users("james","james@naver.com", new Address("대구","가나다로","3333"));
        Users users5 = new Users("martin","martin@google.com", new Address("서울","가기로","1554"));

        usersRepository.save(users1);
        usersRepository.save(users2);
        usersRepository.save(users3);
        usersRepository.save(users4);
        usersRepository.save(users5);
    }

}
