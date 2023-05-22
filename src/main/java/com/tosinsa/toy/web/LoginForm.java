package com.tosinsa.toy.web;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class LoginForm {

    @NotEmpty(message = "회원 id는 필수입니다.")
    private String loginId;

    @NotEmpty(message = "회원 비밀번호는 필수입니다.")
    private String password;
}
