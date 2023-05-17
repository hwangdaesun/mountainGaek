package com.tosinsa.toy.web;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsersDto {

    private String name;
    private String email;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
