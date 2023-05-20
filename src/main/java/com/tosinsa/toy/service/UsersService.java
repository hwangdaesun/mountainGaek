package com.tosinsa.toy.service;

import com.tosinsa.toy.domain.Users;
import com.tosinsa.toy.domain.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    @Transactional //변경
    public Long join(Users users) {
        validateDuplicateMember(users); //중복 회원 검증
        usersRepository.save(users);
        return users.getId();
    }

    private void validateDuplicateMember(Users users) {
        List<Users> findMembers = usersRepository.findByName(users.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Users> findMembers() {
        return usersRepository.findAll();
    }

    public Users findOne(Long usersId) {
        return usersRepository.findById(usersId).orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
    }

}
