package com.ai.posegame.repository;

import com.ai.posegame.domain.Score;
import com.ai.posegame.dto.ScoreDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, String> {

    List<Score> findTop5ByMidAndGnameOrderByRegDateDesc(String mid, String gname);

    @Query("select sum(s.score) from Score s where s.mid = :mid and s.gname = :gname")
    int sum(String mid, String gname);

    @Query("select max(s.score) from Score s where s.mid = :mid and s.gname = :gname")
    int max(String mid, String gname);

}
