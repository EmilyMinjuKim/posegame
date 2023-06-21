package com.ai.posegame.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/game")
@Log4j2
@RequiredArgsConstructor
public class GameController {
    @GetMapping("/main")
    public void mainGET(){
        log.info("game main get...");
    }

    @GetMapping("/test")
    public void testGET(){
        log.info("test..............");
    }

    @GetMapping("/1")
    public void game1GET(Model model){
        log.info("game1 get...");
        String title = "pose1";
        model.addAttribute("imgUrl", "/assets/pictures/" + title + ".jpg");
        model.addAttribute("title", title);
    }

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

}
