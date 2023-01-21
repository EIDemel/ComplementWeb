package edu.spring.tds.controllers;

import ch.qos.logback.core.model.Model;
import ch.qos.logback.core.net.server.Client;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.SessionAttributes;
import edu.spring.models.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SessionAttributes(value = "items")
@Controller
@RequestMapping("/")
public class MainController {

    @ModelAttribute("items")
    public List<Element> getItems(){
        return new ArrayList<>();
    }

    @RequestMapping(path="items", method = {RequestMethod.POST, RequestMethod.GET})
    public String listItems(Model model) {
        return "items";
    }

    @GetMapping("items/new")
    public String addItem(Model model) {
        return "itemsNew";
    }

    @PostMapping("items/addNew")
    public ModelAndView addItem(@ModelAttribute("items") List<Element> items, @RequestParam(name="name", required = false) String name){
        items.add(new Element(name, 0));
        ModelAndView mav = new ModelAndView("redirect:/items");
        return mav;
    }

    @GetMapping("items/inc/{nom}")
    public RedirectView incEval(@ModelAttribute("items") List<Element> items, @PathVariable(name="nom", required = false) String nom) {
        for (Element item : items)
            if (nom.equals(item.getNom())){
                item.setEvaluation(item.getEvaluation()+1);
            }
        return new RedirectView("../../items");
    }

    @GetMapping("items/dec/{nom}")
    public RedirectView decEval(@ModelAttribute("items") List<Element> items, @PathVariable(name="nom", required = false) String nom) {
        for (Element item : items)
            if (nom.equals(item.getNom())){
                item.setEvaluation(item.getEvaluation()-1);
            }
        return new RedirectView("../../items");
    }



}
