package com.tosinsa.toy.service;

import com.tosinsa.toy.domain.member.Member;
import com.tosinsa.toy.domain.member.MemberRepository;
import com.tosinsa.toy.domain.member.Role;
import com.tosinsa.toy.web.MemberForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입 ==============> 이후 회원이 이미 존재할 경우 코드 구현할 예정
     */
    @Transactional
    public void join(MemberForm memberForm) {
        validateDuplicateUser(memberForm);
        Member member = new Member(memberForm.getEmail(),memberForm.getLoginId(),memberForm.getPassword(),memberForm.getName(),Role.USER);
        memberRepository.save(member);
    }

    @Transactional
    public void specialJoin(MemberForm memberForm) {
        validateDuplicateUser(memberForm);
        Member member = new Member(memberForm.getEmail(),memberForm.getLoginId(),memberForm.getPassword(),memberForm.getName(),Role.ADMINISTER);
        memberRepository.save(member);
    }

    private void validateDuplicateUser(MemberForm memberForm) {
        Optional<Member> findUsers = memberRepository.findByLoginId(memberForm.getLoginId());
        if (!findUsers.isEmpty()) {
            throw new IllegalStateException("Already Existing User");
        }
    }

    /**
     * 회원 전체 조회
     */

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(String loginId){
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(() -> new IllegalStateException("존재하지 않는 아이디입니다."));
        return member;
    }

}
