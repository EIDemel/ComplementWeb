package edu.spring.stories.controllers

import edu.spring.stories.entities.Developer
import edu.spring.stories.entities.Story
import edu.spring.stories.repositories.DeveloperRepository
import edu.spring.stories.repositories.StoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/")
class MainController {

    @Autowired
    lateinit var devRepo: DeveloperRepository

    @Autowired
    lateinit var storyRepo: StoryRepository

    @RequestMapping( "", "/")
    fun index(model: ModelMap, attrs:RedirectAttributes): String {
        model["devs"] =  devRepo.findAll()
        model["stories"] = storyRepo.findByDeveloperIsNull()
        return "index"
    }

    @PostMapping("developer/add")
    fun addDev(@ModelAttribute developer: Developer,attrs:RedirectAttributes): ModelAndView {
        devRepo.save(developer)
        return ModelAndView("redirect:/")
    }

    @PostMapping("developer/{id}/story")
    fun addStory(
            @PathVariable id: Int,
            @ModelAttribute("name") name: String
        ): RedirectView {
            val developpeur = devRepo.findById(id).orElse(null)
            val storie = storyRepo.findByNameAndDeveloperId(name, id)

                if (storie == null && name.isNotBlank()) {
                    val newStorie = Story(name = name)
                    newStorie.developer = developpeur
                    storyRepo.save(newStorie)
                }

            return RedirectView("/")
    }

    @GetMapping("developer/{id}/delete")
    fun delete(@PathVariable id: Int,@ModelAttribute developer: Developer, attrs:RedirectAttributes) : String {
        devRepo.deleteById(id)
        return "redirect:/"
    }

    @GetMapping("story/{id}/giveup")
    fun giveUpStory(@PathVariable id: Int,@ModelAttribute("id_sto") id_sto:  Int, attrs:RedirectAttributes) : String {
        val developpeur = devRepo.findById(id).orElse(null)
        val storie = storyRepo.findById(id_sto).get()

        if (storie != null) {
            System.out.println("DEV NAMEEEEEEEEEEEEE " + storie.id)
            developpeur.giveUpStory(storie)
            storyRepo.save(storie)
        }
        return "redirect:/"
    }

    @PostMapping("/story/{id}/action")
    fun addStoryToDeveloper(
        @RequestParam("story-action") storyAction: String,
        @PathVariable id: Int,
        @ModelAttribute("id_dev") id_dev: Int
    ): RedirectView {
        val developpeur = devRepo.findById(id_dev).get()
        val storie = storyRepo.findById(id).get()

        if (storyAction == "remove") {
            if (storie != null) {
                storyRepo.deleteById(storie.id)
            }
        }

        if (storyAction == "affect") {
            if (storie != null) {

                storie.developer = developpeur
                storyRepo.save(storie)
            }
        }

        return RedirectView("/")
    }

}