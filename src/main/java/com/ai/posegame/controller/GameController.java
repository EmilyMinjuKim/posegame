package com.ai.posegame.controller;

import com.ai.posegame.domain.Score;
import com.ai.posegame.dto.ScoreDTO;
import com.ai.posegame.service.ScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/game")
@Log4j2
@RequiredArgsConstructor
public class GameController {

    private final ScoreService scoreService;

    @GetMapping("/main")
    public void mainGET(){
        log.info("game main get...");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/1")
    public void game1GET(Model model,
                         Principal principal){
        log.info("game1 get...");
        String mid = principal.getName();
        String gname = "pose";

        ScoreDTO scoreDTO = new ScoreDTO();
        scoreDTO.setMid(mid);
        scoreDTO.setGname(gname);
        model.addAttribute("gameInfo", scoreDTO);

        String title = "pose1";
        model.addAttribute("imgUrl", "/assets/pictures/" + title + ".jpg");
        model.addAttribute("title", title);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/2")
    public void game2GET(Model model){
        log.info("game2 get...");

        String title = "pose1";
        model.addAttribute("imgUrl", "/assets/pictures/" + title + ".jpg");
        model.addAttribute("title", title);

    }

    @PostMapping("/ajax")
    @ResponseBody
    public Map<String, String> ajaxPOST(){
        log.info("ajax.................................................................");

        String title = "pose2";
        Map<String, String> map = new HashMap<String, String>();
        map.put("imgUrl", "/assets/pictures/" + title + ".jpg");
        map.put("title", title);

        return map;
    }

    @GetMapping("/result")
    public void resultGET(ScoreDTO scoreDTO,
                          Model model){

        log.info("result..............");

        scoreDTO.setDate(LocalDate.now());
        scoreService.addScore(scoreDTO);

        String id = scoreDTO.getMid();
        String game = scoreDTO.getGname();

        List<ScoreDTO> list = scoreService.getHistory(id, game);

        ScoreDTO total = new ScoreDTO();
        total.setMax_score(scoreService.maxScore(id, game));
        total.setSum_score(scoreService.sumScore(id, game));

        model.addAttribute("list", list);
        model.addAttribute("total", total);
    }

}
