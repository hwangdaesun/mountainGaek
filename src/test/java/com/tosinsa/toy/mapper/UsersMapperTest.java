package com.tosinsa.toy.mapper;
import com.tosinsa.toy.domain.Address;
import com.tosinsa.toy.domain.Users;
import com.tosinsa.toy.web.UsersDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsersMapperTest {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void basic_mapperTest() {

        Users users = Users.createUsers("jack", "jack12@gmail.com", new Address("두레", "dure@naver.com", "0000"));

        UsersDto usersDto = usersMapper.usersToUsersDto(users);

        Assertions.assertThat(users.getUpdatedAt()).isEqualTo(usersDto.getUpdated_at());
        Assertions.assertThat(users.getCreatedAt()).isEqualTo(usersDto.getCreated_at());

        System.out.println(usersDto.toString());
    }
}
