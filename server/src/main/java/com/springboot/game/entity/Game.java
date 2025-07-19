package com.springboot.game.entity;

import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.player.entity.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long gameId;

    @Column
    private String gameVersion;

    @Enumerated(EnumType.STRING)
    private GameType gameType;

    @Column
    private int isComputer;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players = new ArrayList<>();

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // 편의 메서드 (양방향 관계 동기화)
    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    @Getter
    public enum GameType {
        NORMAL(0),
        FLAG_GAME(1),
        TIMEOUT(2),
        HERO_MODE(3);

        private final int code;

        GameType(int code) {
            this.code = code;
        }

        public static GameType fromCode(int code) {
            return Arrays.stream(values())
                    .filter(gt -> gt.code == code)
                    .findFirst()
                    .orElseThrow(() -> new BusinessLogicException(ExceptionCode.INVALID_CODE));
        }
    }
}
