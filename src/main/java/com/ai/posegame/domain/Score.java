package com.ai.posegame.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Score extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    /*
    @ManyToOne
    @JoinColumn(name="mid")
    private Member member;
     */

    private String mid;

    private String gname;

    private int score;

    private LocalDate date;
}
