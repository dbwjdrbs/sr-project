package com.springboot.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    GAME_NOT_FOUND(404, "Game not found"),
    IS_NOT_HUMAN(400, "Is Not Human"),
    MEMBER_EXISTS(409, "Member exists"),
    PLAYER_EXISTS(409, "Player exists"),
    INVALID_CODE(400, "Invalid Code");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message){
        this.status = code;
        this.message = message;
    }
}
