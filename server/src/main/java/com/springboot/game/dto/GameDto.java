package com.springboot.game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class GameDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotNull
        private int isComputer; // valid용

        @NotBlank
        private String gameVersion;

        @NotNull
        private int gameType;
    }
}
