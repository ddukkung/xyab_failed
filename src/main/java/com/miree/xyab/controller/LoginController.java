package com.miree.xyab.controller;

import com.miree.xyab.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/loginSuccess")
    public String loginComplete(User user) {
        return "redirect:/board/list";
    }

}
