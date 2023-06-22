package com.m2i.cda.tp_todolist.controller;

import com.m2i.cda.tp_todolist.entity.Tache;
import com.m2i.cda.tp_todolist.service.ITacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/task")
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

    @GetMapping("/{id}")
    public Tache getTache(@PathVariable("id") Integer id){
        return tacheService.findById(id);
    }

    @GetMapping("/search")
    public String searchTacheById(@RequestParam("tacheId") Integer tacheId, Model model) {
        Tache tache = tacheService.findById(tacheId);
        model.addAttribute("tache", tache);
        return "todo-details";
    }

    @GetMapping("/delete/{id}")
    public String deleteTache(@PathVariable("id") Integer id) {
        Tache t = tacheService.findById(id);
        if (t != null && tacheService.delete(t)) {
            return "redirect:/task";
        }
        return "Aucune tâche avec cet id";
    }

    @GetMapping("/form")
    public String afficherFormulaireCreationTache(Model model) {
        model.addAttribute("tache", new Tache());
        return "formulaire";
    }

    @PostMapping("/create")
    public String postTache(@ModelAttribute Tache tache) {

        System.out.println("tache" + tache);
        if (tache.getId() == null) {
            if (tacheService.create(tache)) {
                return "redirect:/task";
            }
            return "task/error";

        } else {
            Tache existTache = tacheService.findById(tache.getId());
            if (existTache != null) {
                existTache.setDateToDo(tache.getDateToDo());
                existTache.setTitre(tache.getTitre());
                existTache.setDescription(tache.getDescription());
                if (tacheService.update(existTache)) {
                    return "redirect:/task";
                }
            }
            return "task/error";
        }
    }

    @GetMapping("/edit/{id}")
    public String editTacheForm(@PathVariable Integer id, Model model) {
        Tache ta = tacheService.findById(id);
        System.out.println("ta " + ta);
        model.addAttribute("tache", ta);
        return "formulaire";
    }

    @PostMapping("/update/{id}")
    public Tache updateTache(@PathVariable("id") Integer id, @RequestBody Tache tache) {
        Tache existTache = tacheService.findById(id);
        if (existTache != null) {
            existTache.setDateToDo(tache.getDateToDo());
            existTache.setTitre(tache.getTitre());
            existTache.setDescription(tache.getDescription());
            if (tacheService.update( existTache)) {
                return  existTache;
            }
        }
        return  existTache;
    }





}
