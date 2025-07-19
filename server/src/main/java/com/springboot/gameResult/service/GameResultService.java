package com.springboot.gameResult.service;

import com.springboot.game.mapper.GameMapper;
import com.springboot.gameResult.entity.GameResult;
import com.springboot.gameResult.repository.GameResultRepository;
import com.springboot.player.entity.Player;
import com.springboot.player.service.PlayerService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class GameResultService {
    private final GameResultRepository gameResultRepository;
    private final ApplicationEventPublisher publisher;
    private final PlayerService playerService;

    public GameResultService(GameResultRepository gameResultRepository, ApplicationEventPublisher publisher, PlayerService playerService) {
        this.gameResultRepository = gameResultRepository;
        this.publisher = publisher;
        this.playerService = playerService;
    }

    public GameResult createGameResult(GameResult gameResult) {
        Player player = playerService.verifyExistsPlayer(gameResult.getPlayer().getPlayerId());

        gameResult.setPlayer(player);

        gameResult.setResource_grain(gameResult.getResource_grain());
        gameResult.setResource_tree(gameResult.getResource_tree());
        gameResult.setSoldier_train(gameResult.getSoldier_train());
        gameResult.setSoldier_death(gameResult.getSoldier_death());
        gameResult.setSoldier_kill(gameResult.getSoldier_kill());
        gameResult.setBuilding_build(gameResult.getBuilding_build());
        gameResult.setBuilding_lose(gameResult.getBuilding_lose());
        gameResult.setBuilding_destroy(gameResult.getBuilding_destroy());

        // 이벤트 리스너 발행해서 조인
        GameResult savedGameResult = gameResultRepository.save(gameResult);

        // 이것도 이벤트 리스너 발행
        playerService.updateGameResult(savedGameResult);
        // 이벤트 리스너 발행해서 웹소켓 종료
        return savedGameResult;
    }
}
