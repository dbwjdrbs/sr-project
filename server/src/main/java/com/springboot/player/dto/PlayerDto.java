package com.springboot.player.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PlayerDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotNull
        private long gameId;

        @NotNull
        private long memberId;

        @NotNull
        private int nation;

        @NotNull
        private int playerNumber;

        @NotNull
        private int teamNumber;

        @NotNull
        private int isWin;
    }
}
