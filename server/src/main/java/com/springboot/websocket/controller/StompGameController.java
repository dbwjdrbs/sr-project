package com.springboot.websocket.controller;

import com.springboot.gameResult.service.GameResultService;
import com.springboot.websocket.dto.GameChatPayload;
import com.springboot.websocket.dto.GameOverPayload;
import com.springboot.websocket.dto.GameStartPayload;
import com.springboot.websocket.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class GameController {
    private final RoomService roomService;
    private final GameResultService resultService;
    private final SimpMessagingTemplate messaging;

    /** 로비 입장: 클라 → /app/lobby.enter */
    @MessageMapping("/lobby.enter")
    public void enterLobby(Principal principal, @Payload(required=false) String nickOpt) {
        String nickname = (nickOpt != null && !nickOpt.isBlank()) ? nickOpt : principal.getName();
        roomService.enterLobby(nickname); // Mongo upsert & /topic/lobby 브로드캐스트
    }

//    /** 게임 시작: 클라 → /app/game.start */
//    @MessageMapping("/game.start")
//    public void gameStart(@Valid GameStartPayload p) {
//        String roomId = roomService.startGame(p.getNicknames(), p.getRoomId()); // 상태 전환 & 개인 큐 알림
//        // 클라는 /user/queue/system 에서 ROOM_ASSIGNED(roomId) 수신 → /topic/game.{roomId} 구독 전환
//    }
//
//    /** 인게임 메시지: 클라 → /app/game.send → 구독자: /topic/game.{roomId} */
//    @MessageMapping("/game.send")
//    public void sendInGame(@Valid GameChatPayload p) {
//        // (권장) p.sender 가 room.members 인지 서버에서 검증
//        messaging.convertAndSend("/topic/game." + p.getRoomId(), p);
//    }
//
//    /** 게임 종료: 클라 → /app/game.over */
//    @MessageMapping("/game.over")
//    public void gameOver(@Valid GameOverPayload p) {
//        resultService.save(p); // MySQL 저장 (멱등키 운영 권장)
//        messaging.convertAndSend("/topic/game." + p.getRoomId(), p);
//        roomService.backToLobby(p.getNicknames()); // Mongo 상태 롤백
//    }
}
