package com.springboot.websocket.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class GameStartPayload {
    @NotEmpty List<String> nicknames;
    String roomId;
}

@Data
class GameChatPayload {
    @NotBlank String roomId;
    @NotBlank String sender;
    @NotBlank String message;
}

@Data
class GameOverPayload {
    @NotBlank String roomId;
    @NotEmpty List<String> nicknames;
    String result;
} // result는 JSON 문자열 등