package com.ai.posegame.repository;

import com.ai.posegame.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, String> {
    List<Picture> findAllByGname(String gname);
}
