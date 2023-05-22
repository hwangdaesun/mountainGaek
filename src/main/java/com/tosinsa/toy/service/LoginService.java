package com.tosinsa.toy.service;


import com.tosinsa.toy.domain.Member;
import com.tosinsa.toy.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(() -> new IllegalStateException("존재하지 않는 아이디입니다."));
        if(!member.getPassword().equals(password)) {
            throw new IllegalStateException("존재하지 않는 비밀번호입니다.");
        }
        return member;
    }

}