package com.lunatech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Justin Seyvecou
 */
@Controller

public class RootController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
