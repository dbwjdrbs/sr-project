package com.springboot.game.mapper;

import com.springboot.game.dto.GameDto;
import com.springboot.game.entity.Game;
import com.springboot.player.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameMapper {
    default Game gamePostToGame(GameDto.Post requestBody) {
        Game game = new Game();
        game.setGameVersion(requestBody.getGameVersion());

        Game.GameType type = Game.GameType.fromCode(requestBody.getGameType());
        game.setGameType(type);

        game.setIsComputer(requestBody.getIsComputer());

        List<Player> players = new ArrayList<>();
        game.setPlayers(players);

        return game;
    }
}
