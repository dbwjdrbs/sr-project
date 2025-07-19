package com.springboot.game.controller;

import com.springboot.game.dto.GameDto;
import com.springboot.game.entity.Game;
import com.springboot.game.mapper.GameMapper;
import com.springboot.game.service.GameService;
import com.springboot.utils.UriCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;

@RestController
@Validated
@RequestMapping("/addGames")
public class GameController {
    private final static String GAME_DEFAULT_URL = "/addGames";
    private final GameMapper gameMapper;
    private final GameService gameService;

    public GameController(GameMapper gameMapper, GameService gameService) {
        this.gameMapper = gameMapper;
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity postGame(@Valid @RequestBody GameDto.Post requestBody) {
        Game game = gameMapper.gamePostToGame(requestBody);
        Game createdGame = gameService.createGame(game);

        URI location = UriCreator.createUri(GAME_DEFAULT_URL, createdGame.getGameId());

        return ResponseEntity.created(location).build();
    }

    // 일단 보류.
    /*
    @PatchMapping("/{game-id}")
    public ResponseEntity patchGame(@PathVariable("game-id") @Positive long game_id,
                                        @Valid @RequestBody GameDto.Patch requestBody) {
        requestBody.setGameId(game_id);
        return null;
    }
     */
}
