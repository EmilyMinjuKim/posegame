package com.ai.posegame.repository;

import com.ai.posegame.domain.Score;
import com.ai.posegame.dto.ScoreDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class ScoreRepositoryTests {

    @Autowired
    private ScoreRepository scoreRepository;

    /*
    @Test
    public void insertMembers(){

        IntStream.rangeClosed(1,100).forEach(i -> {

            Member member = Member.builder()
                    .mid("member"+i)
                    .mpw(passwordEncoder.encode("1111"))
                    .build();

            member.addRole(MemberRole.USER);

            if(i >= 90){
                member.addRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);

        });
    }


    @Test
    public void testRead() {

        List<Score> list = scoreRepository.findAllByMidAndGnameOrderByDateDesc("member1","pose");

        list.forEach(score -> log.info(score.getScore()));

    }

     */
}
