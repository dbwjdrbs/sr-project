package com.springboot.player.mapper;

import com.springboot.game.entity.Game;
import com.springboot.gameResult.entity.GameResult;
import com.springboot.member.entity.Member;
import com.springboot.player.dto.PlayerDto;
import com.springboot.player.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlayerMapper {
    default Player playerPostToPlayer(PlayerDto.Post requestBody) {
        Player player = new Player();

        // member 세팅
        Member member = new Member();
        member.setMemberId(requestBody.getMemberId());
        player.setMember(member);

        // game 세팅
        Game game = new Game();
        game.setGameId(requestBody.getGameId());
        player.setGame(game);

        GameResult gameResult = new GameResult();
        player.setGameResult(gameResult);

        // nation 세팅
        Player.Nation nation = Player.Nation.fromCode(requestBody.getNation());
        player.setNation(nation);

        player.setPlayerNumber(requestBody.getPlayerNumber());
        player.setTeamNumber(requestBody.getTeamNumber());
        player.setIsWin(requestBody.getIsWin());

        return player;
    }
}
