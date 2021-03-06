package com.codegym.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class CategoryController {
    @GetMapping("category")
    public ModelAndView showAll(){
        ModelAndView index = new ModelAndView("category");
        return index;
    }

    @GetMapping("home")
    public ModelAndView home(){
        ModelAndView index = new ModelAndView("homepage");
        return index;
    }

    @GetMapping("/admin")
    public ModelAndView admin(){
        ModelAndView modelAndView = new ModelAndView("admin");
        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView user(){
        ModelAndView modelAndView = new ModelAndView("user");
        return modelAndView;
    }

    @GetMapping("donthaveaccess")
    public ModelAndView p403(){
        ModelAndView modelAndView = new ModelAndView("donthaveaccess");
        return modelAndView;
    }
}
