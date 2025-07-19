package com.springboot.player.service;

import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.game.entity.Game;
import com.springboot.game.service.GameService;
import com.springboot.gameResult.entity.GameResult;
import com.springboot.member.entity.Member;
import com.springboot.member.service.MemberService;
import com.springboot.player.entity.Player;
import com.springboot.player.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final MemberService memberService;
    private final GameService gameService;

    public PlayerService(PlayerRepository playerRepository, MemberService memberService, GameService gameService) {
        this.playerRepository = playerRepository;
        this.memberService = memberService;
        this.gameService = gameService;
    }

    public Player createPlayer(Player player) {
        Member member = memberService.findVerifiedMember(player.getMember().getMemberId());
        player.setMember(member);

        long gameId = player.getGame().getGameId();
        Game game = gameService.findVerifiedGame(gameId);
        player.setGame(game);

        player.setGameResult(null);

        player.setNation(player.getNation());

        player.setPlayerNumber(player.getPlayerNumber() + 1);
        player.setTeamNumber(player.getTeamNumber() + 1);
        player.setIsWin(player.getIsWin());

        Player savedPlayer = playerRepository.save(player);
        // 이거 이벤트 리스너 발행해서 실시하도록.
        gameService.updatePlayer(player);
        return savedPlayer;
    }

    public void updateGameResult(GameResult gameResult) {
        Player player = verifyExistsPlayer(gameResult.getPlayer().getPlayerId());
        player.setGameResult(gameResult);

        playerRepository.save(player);
    }

    public Player verifyExistsPlayer(long playerId) {
        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        Player findPlayer = optionalPlayer.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.PLAYER_EXISTS));

        return findPlayer;
    }
}
