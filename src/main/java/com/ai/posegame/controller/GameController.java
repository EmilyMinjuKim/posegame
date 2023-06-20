package com.ai.posegame.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
@Log4j2
@RequiredArgsConstructor
public class GameController {
    @GetMapping("/main")
    public void mainGET(){
        log.info("game main get...");
    }

    @GetMapping("/1")
    public void game1GET(Model model){
        log.info("game1 get...");
        String title = "pose1";
        model.addAttribute("imgUrl", "/assets/pictures/" + title + ".jpg");
        model.addAttribute("title", title);
    }

    @GetMapping("/posenet")
    public void posenetGET(){
        log.info("posenet get...");

    }

}
