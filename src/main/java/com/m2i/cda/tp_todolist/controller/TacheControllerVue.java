package com.m2i.cda.tp_todolist.controller;

import com.m2i.cda.tp_todolist.service.ITacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tache")
public class TacheControllerVue {

    @Autowired
    ITacheService tacheService;

    @GetMapping("")
    public ModelAndView getTaches(){
        ModelAndView modelAndView = new ModelAndView();
        if(tacheService.findAll().isEmpty()){
            modelAndView.setViewName("error");
        } else {
            modelAndView.setViewName("taches");
            modelAndView.addObject("taches",tacheService.findAll());
        }
        return modelAndView;
    }

}
