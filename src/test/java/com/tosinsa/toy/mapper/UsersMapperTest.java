package com.tosinsa.toy.mapper;
import com.tosinsa.toy.domain.Users;
import com.tosinsa.toy.web.UsersDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


@SpringBootTest
class UsersMapperTest {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void basic_mapperTest() {

        Users users = Users.builder()
                .email("jack12@gmail.com")
                .name("jack")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        UsersDto usersDto = usersMapper.usersToUsersDto(users);

        Assertions.assertThat(users.getUpdatedAt()).isEqualTo(usersDto.getUpdated_at());
        Assertions.assertThat(users.getCreatedAt()).isEqualTo(usersDto.getCreated_at());

        System.out.println(usersDto.toString());
    }
}