package com.springboot.gameResult.mapper;

import com.springboot.gameResult.dto.GameResultDto;
import com.springboot.gameResult.entity.GameResult;
import com.springboot.player.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameResultMapper {
    default GameResult gameResultPostToGameResult(GameResultDto.Post requestBody) {
        Player player = new Player();
        player.setPlayerId(requestBody.getPlayerId());

        GameResult gameResult = new GameResult();
        gameResult.setPlayer(player);

        gameResult.setResource_grain(requestBody.getResource_grain());
        gameResult.setResource_tree(requestBody.getResource_tree());
        gameResult.setSoldier_train(requestBody.getSoldier_train());
        gameResult.setSoldier_death(requestBody.getSoldier_death());
        gameResult.setSoldier_kill(requestBody.getSoldier_kill());
        gameResult.setBuilding_build(requestBody.getBuilding_build());
        gameResult.setBuilding_lose(requestBody.getBuilding_lose());
        gameResult.setBuilding_destroy(requestBody.getBuilding_destroy());

        return gameResult;
    }
}
