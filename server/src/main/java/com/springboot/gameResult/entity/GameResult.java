package com.springboot.gameResult.entity;

import com.springboot.player.entity.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class GameResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long gameResultId;

    @OneToOne(mappedBy = "gameResult", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Player player;

    @Column
    private int resource_grain;

    @Column
    private int resource_tree;

    @Column
    private int soldier_train;

    @Column
    private int soldier_death;

    @Column
    private int soldier_kill;

    @Column
    private int building_build;

    @Column
    private int building_lose;

    @Column
    private int building_destroy;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
