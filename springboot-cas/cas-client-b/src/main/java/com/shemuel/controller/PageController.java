package com.shemuel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: dengshaoxiang
 * @Date: 2019/7/29 13:33
 * @Description:
 */
@Controller
@RequestMapping("/test")
public class PageController {
    @RequestMapping("/index")
    public String index () {
        return "index";
    }
}
