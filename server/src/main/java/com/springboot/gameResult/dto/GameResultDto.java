package com.springboot.gameResult.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

public class GameResultDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotNull
        private long playerId;

        @NotNull
        private int resource_grain;

        @NotNull
        private int resource_tree;

        @NotNull
        private int soldier_train;

        @NotNull
        private int soldier_death;

        @NotNull
        private int soldier_kill;

        @NotNull
        private int building_build;

        @NotNull
        private int building_lose;

        @NotNull
        private int building_destroy;
    }
}
