package com.springboot.gameResult.controller;

import com.springboot.gameResult.dto.GameResultDto;
import com.springboot.gameResult.entity.GameResult;
import com.springboot.gameResult.mapper.GameResultMapper;
import com.springboot.gameResult.service.GameResultService;
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
@RequestMapping("/addGameResults")
public class GameResultController {
    private final static String GAME_RESULT_DEFAULT_URL = "/addGameResults";
    private final GameResultMapper gameResultMapper;
    private final GameResultService gameResultService;

    public GameResultController(GameResultMapper gameResultMapper, GameResultService gameResultService) {
        this.gameResultMapper = gameResultMapper;
        this.gameResultService = gameResultService;
    }

    @PostMapping
    public ResponseEntity postGameResult(@Valid @RequestBody GameResultDto.Post requestBody) {
        GameResult gameResult = gameResultMapper.gameResultPostToGameResult(requestBody);
        GameResult createdGameResult = gameResultService.createGameResult(gameResult);

        URI location = UriCreator.createUri(GAME_RESULT_DEFAULT_URL, createdGameResult.getGameResultId());

        return ResponseEntity.created(location).build();
    }
}
