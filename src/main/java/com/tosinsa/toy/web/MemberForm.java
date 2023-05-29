package com.tosinsa.toy.web;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

}
