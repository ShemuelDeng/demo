package com.shemuel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dengshaoxiang on 2019/11/21 14:14
 * description:
 */
@Controller
public class PageController {
    @RequestMapping("/main")
    public String index(){
        return "index";
    }
}
