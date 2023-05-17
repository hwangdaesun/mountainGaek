package com.tosinsa.toy.mapper;

import com.tosinsa.toy.domain.Users;
import com.tosinsa.toy.web.UsersDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UsersMapper {

    @Mapping(target = "created_at", source = "users.createdAt")
    @Mapping(target = "updated_at", source = "users.updatedAt")
    UsersDto usersToUsersDto(Users users);

}
