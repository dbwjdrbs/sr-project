package com.springboot.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


public class MemberDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank
        private String email;

        @NotBlank
        private String password;

        @NotBlank
        private String nickname;
    }
}
