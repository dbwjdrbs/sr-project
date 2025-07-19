package com.springboot.player.entity;

import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.game.entity.Game;
import com.springboot.gameResult.entity.GameResult;
import com.springboot.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long playerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToOne
    @JoinColumn(name = "gameResult_id", unique = true)
    private GameResult gameResult;

    @Enumerated(EnumType.STRING)
    private Nation nation;

    @Column
    private int playerNumber;

    @Column
    private int teamNumber;

    @Column
    private int isWin;

    @Getter
    public enum Nation {
        NO_NATION(0),
        JOSEON(1),
        JAPAN(2),
        CHINA(3),
        OBSERVER(6);

        private final int code;

        Nation(int code) { this.code = code; }

        public static Nation fromCode(int code) {
            return Arrays.stream(values())
                    .filter(gt -> gt.code == code)
                    .findFirst()
                    .orElseThrow(() -> new BusinessLogicException(ExceptionCode.INVALID_CODE));
        }
    }
}
