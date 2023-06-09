package com.tosinsa.toy.service;


import com.tosinsa.toy.domain.member.Member;
import com.tosinsa.toy.domain.member.MemberRepository;
import com.tosinsa.toy.web.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(LoginForm loginForm) {
        Member member = memberRepository.findByLoginId(loginForm.getLoginId()).orElseThrow(() -> new IllegalStateException("존재하지 않는 아이디입니다."));
        if(!member.getPassword().equals(loginForm.getPassword())) {
            throw new IllegalStateException("존재하지 않는 비밀번호입니다.");
        }
        return member;
    }

}