package com.springboot.game.service;

import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.game.entity.Game;
import com.springboot.game.repository.GameRepository;
import com.springboot.gameResult.entity.GameResult;
import com.springboot.player.entity.Player;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class GameService {
    private final GameRepository gameRepository;
    private final ApplicationEventPublisher publisher;

    public GameService(GameRepository gameRepository, ApplicationEventPublisher publisher) {
        this.gameRepository = gameRepository;
        this.publisher = publisher;
    }

    public Game createGame(Game game) {
        verifyHuman(game);

        // 나중에 최신버전인지 체크하는 로직 필요할지도...
        game.setGameVersion(game.getGameVersion());
        game.setGameType(game.getGameType());
        game.setPlayers(game.getPlayers()); // null

        Game savedGame = gameRepository.save(game);

        // 게임 생성 이벤트 필요시 추가.

        return savedGame;
    }

    public void updatePlayer(Player player) {
        Game game = findVerifiedGame(player.getGame().getGameId());
        game.getPlayers().add(player);

        gameRepository.save(game);
    }

    private void verifyHuman(Game game) {
        int isComputer = game.getIsComputer();

        if (isComputer == 1) {
            throw new BusinessLogicException(ExceptionCode.IS_NOT_HUMAN);
        }
    }

    public Game findVerifiedGame(long gameId) {
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        Game findGame = optionalGame.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.GAME_NOT_FOUND));

        return findGame;
    }
}
