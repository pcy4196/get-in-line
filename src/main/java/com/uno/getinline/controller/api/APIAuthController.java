package com.uno.getinline.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
// @ResponseBody 포함하는 어노테이션
public class APIAuthController {

    @GetMapping("/sign-up")
    public String signUp() {
        return "done!";
    }

    @GetMapping("/login")
    public String login() {
        return "done!";
    }
}
