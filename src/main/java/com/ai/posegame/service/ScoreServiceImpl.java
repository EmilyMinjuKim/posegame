package com.ai.posegame.service;

import com.ai.posegame.domain.Score;
import com.ai.posegame.dto.ScoreDTO;
import com.ai.posegame.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService{

    private final ModelMapper modelMapper;
    private final ScoreRepository scoreRepository;

    @Override
    public List<ScoreDTO> getHistory(String mid, String gname) {
        List<Score> list = scoreRepository.findTop5ByMidAndGnameOrderByRegDateDesc(mid, gname);
        List<ScoreDTO> scoreList = list.stream()
                .map(score -> modelMapper.map(score, ScoreDTO.class))
                .collect(Collectors.toList());

        return scoreList;
    }

    @Override
    public int maxScore(String mid, String gname) {

        return scoreRepository.max(mid, gname);
    }

    @Override
    public int sumScore(String mid, String gname) {

        return scoreRepository.sum(mid, gname);
    }

    @Override
    public void addScore(ScoreDTO scoreDTO) {
        Score score = modelMapper.map(scoreDTO, Score.class);
        scoreRepository.save(score);
    }


}
