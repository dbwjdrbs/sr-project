package com.springboot.player.controller;

import com.springboot.player.dto.PlayerDto;
import com.springboot.player.entity.Player;
import com.springboot.player.mapper.PlayerMapper;
import com.springboot.player.service.PlayerService;
import com.springboot.utils.UriCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Validated
@RequestMapping("/addPlayers")
public class PlayerController {
    private final static String PLAYER_DEFAULT_URL = "/addPlayers";
    private final PlayerMapper playerMapper;
    private final PlayerService playerService;

    public PlayerController(PlayerMapper playerMapper, PlayerService playerService) {
        this.playerMapper = playerMapper;
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity postPlayer(@Valid @RequestBody PlayerDto.Post requestBody) {
        Player player = playerMapper.playerPostToPlayer(requestBody);
        Player createdPlayer = playerService.createPlayer(player);

        URI location = UriCreator.createUri(PLAYER_DEFAULT_URL, createdPlayer.getPlayerId());

        return ResponseEntity.created(location).build();
    }
}
