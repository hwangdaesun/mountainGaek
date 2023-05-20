package com.tosinsa.toy.service;

import com.tosinsa.toy.domain.Address;
import com.tosinsa.toy.domain.Users;
import com.tosinsa.toy.domain.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @Spy
    @InjectMocks
    private UsersService usersService;

    private Users users;

    @BeforeEach
    void init(){
        Address address = new Address("두레","두레로","0000");
        users = new Users("두레","dure@naver.com",address);
    }

    @Test
    void join() {
        when(usersRepository.save(users)).thenReturn(users);
        Long id = usersService.join(users);
        Assertions.assertEquals(id,users.getId());
    }

    @Test()
    @DisplayName("중복_회원_에러")
    void 중복_회원() throws Exception{
        when(usersRepository.findByName(users.getName())).thenReturn(List.of(users));
        Assertions.assertThrows(IllegalStateException.class,() -> usersService.join(users));
    }

}