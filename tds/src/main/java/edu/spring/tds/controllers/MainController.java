package edu.spring.tds.controllers;

import ch.qos.logback.core.model.Model;
import ch.qos.logback.core.net.server.Client;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.SessionAttributes;
import edu.spring.models.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@SessionAttributes(value = "items")
@Controller
@RequestMapping("/")
public class MainController {

    @ModelAttribute("items")
    public List<Element> getItems(){
        return new ArrayList<>();
    }


    @RequestMapping("modelViewSample")
    public ModelAndView mvSample() {
        ModelAndView mv = new ModelAndView("items");
        mv.addObject("items", getItems());
        return mv;
    }

    @GetMapping("items/new/{name}")
    public ModelAndView addItem(@ModelAttribute("items") List<Element> items, @PathVariable(name="name", required = false) String name){
        items.add(new Element(name));
        ModelAndView mav = new ModelAndView("items");
        return mav;
    }

    @RequestMapping(path="items", method = {RequestMethod.POST, RequestMethod.GET})
    public String listItems(Model model) {
        return "items";
    }





}
