package com.tosinsa.toy.web;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Data
public class MemberForm {

    @NotEmpty(message = "회원 id는 필수입니다.")
    private String loginId;

    @NotEmpty(message = "회원 비밀번호는 필수입니다.")
    private String password;

    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;

    @NotEmpty(message = "회원 이메일은 필수입니다.")
    private String email;

}
