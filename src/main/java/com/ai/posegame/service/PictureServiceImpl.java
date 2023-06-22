package com.ai.posegame.service;

import com.ai.posegame.domain.Picture;
import com.ai.posegame.dto.PictureDTO;
import com.ai.posegame.repository.PictureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class PictureServiceImpl implements PictureService {

    private final ModelMapper modelMapper;
    private final PictureRepository pictureRepository;

    @Override
    public String selectTitle(String gname) {
        List<Picture> list = pictureRepository.findAllByGname(gname);
        List<PictureDTO> titleList = list.stream()
                .map(pic -> modelMapper.map(pic, PictureDTO.class))
                .collect(Collectors.toList());

        // 순서 랜덤으로 가지고 오기
        Collections.shuffle(titleList);

        String title = titleList.get(0).getTitle();

        return title;
    }
}
