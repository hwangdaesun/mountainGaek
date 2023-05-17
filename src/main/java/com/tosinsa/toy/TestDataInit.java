package com.tosinsa.toy;

import com.tosinsa.toy.domain.Users;
import com.tosinsa.toy.domain.UsersRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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

        Users users1 = new Users("martin","martin@naver.com", LocalDateTime.now(),LocalDateTime.now());
        Users users2 = new Users("dennis","dennis@naver.com",LocalDateTime.now(),LocalDateTime.now());
        Users users3 = new Users("sophia","sophia@naver.com",LocalDateTime.now(),LocalDateTime.now());
        Users users4 = new Users("james","james@naver.com",LocalDateTime.now(),LocalDateTime.now());
        Users users5 = new Users("martin","martin@google.com",LocalDateTime.now(),LocalDateTime.now());

        usersRepository.save(users1);
        usersRepository.save(users2);
        usersRepository.save(users3);
        usersRepository.save(users4);
        usersRepository.save(users5);

    }

}
