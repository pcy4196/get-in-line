package com.uno.getinline.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController implements ErrorController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

    // implements ErrorController Error 피에지 컨트롤 하기 위해 ErrorController inerface 구현 필요
    @RequestMapping("/error")
    public String error() {
        return "error";
    }
}
