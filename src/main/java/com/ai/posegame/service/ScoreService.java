package com.ai.posegame.service;

import com.ai.posegame.dto.ScoreDTO;

import java.util.List;

public interface ScoreService {

    List<ScoreDTO> getHistory(String mid, String gname);

    int maxScore(String mid, String gname);

    int sumScore(String mid, String gname);

    void addScore(ScoreDTO scoreDTO);
}
