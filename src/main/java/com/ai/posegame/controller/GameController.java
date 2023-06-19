package com.ai.posegame.controller;

import com.ai.posegame.dto.MemberJoinDTO;
import com.ai.posegame.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/game")
@Log4j2
@RequiredArgsConstructor
public class GameController {

    @GetMapping("/main")
    public void mainGET(){
        log.info("game main get...");
    }

}
