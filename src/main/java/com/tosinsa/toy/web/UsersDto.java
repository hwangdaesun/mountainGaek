package com.tosinsa.toy.web;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
public class UsersDto {

    private String name;
    private String email;
    private Instant created_at;
    private Instant updated_at;

}
