package edu.spring.stories.controllers

import edu.spring.stories.entities.Developer
import edu.spring.stories.repositories.DeveloperRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/")
class MainController {

    @Autowired
    lateinit var devRepo: DeveloperRepository

    @RequestMapping( "")
    fun index(): String {
        return "index"
    }

    @PostMapping("developer/add")
    fun addItem(@ModelAttribute developer: Developer, attrs: RedirectAttributes): ModelAndView {
        devRepo.save(developer)
        return ModelAndView("redirect:/")
    }


}