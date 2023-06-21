package com.ai.posegame.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ScoreDTO {
    private String mid;
    private String gname;
    private int score;
    private int max_score;
    private int sum_score;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
