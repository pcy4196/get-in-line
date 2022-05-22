package com.uno.getinline.controller;

import com.uno.getinline.exception.GeneralException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {

    @GetMapping("/")
    public String root() {
//        throw new Exception("테스트");
//        throw new GeneralException("테스트");
        return "index";
    }

}
