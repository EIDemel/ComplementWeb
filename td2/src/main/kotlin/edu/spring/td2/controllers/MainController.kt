package edu.spring.td2.controllers

import edu.spring.td2.entities.Organization
import edu.spring.td2.repositories.OrganizationRepository
import edu.spring.td2.services.UIMessage
import edu.spring.td2.services.UISuppression
import jakarta.persistence.Id
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes


@Controller
@RequestMapping("/orgas")
class MainController {

    private fun addMsg(resp:Boolean, attrs: RedirectAttributes, title:String, success:String, error:String){
        if(resp) {
            attrs.addFlashAttribute("msg",
                UIMessage.message(title, success))
        } else {
            attrs.addFlashAttribute("msg",
                UIMessage.message(title, error,"error","warning circle"))

        }
    }


    @Autowired
    lateinit var repo: OrganizationRepository
    @GetMapping("")
    fun index(model: ModelMap , @RequestAttribute("dlt") dlt:UISuppression.Suppression? , @RequestAttribute("msg") msg:UIMessage.Message?): String {
        model["orgas"] =  repo.findAll()
        return "accueil"
    }
    @GetMapping("/new")
    fun newOrga(): String {
        return "newOrga"
    }
    @PostMapping("/addNew")
    fun addNewOrga(@ModelAttribute orga: Organization, attrs:RedirectAttributes): String {
        if (orga.name == "" || orga.domain == "" || orga.aliases == "") {
            attrs.addFlashAttribute("msg", UIMessage.message("Ajout", "Champ null", "error", "warning circle"))
        }
        else {
            addMsg(true,attrs,"Ajout","L'orga à été ajoutée","Error")
            repo.save(orga)
        }

        return "redirect:/orgas"
    }
    @GetMapping("/edit/{id}")
    fun editOrganization(model: ModelMap, @PathVariable id: Int) : String {
        model["orga"] = repo.findById(id.toLong()).get()
        return "editOrga"
    }
    @GetMapping("/updateOrga/{id}")
    fun updateOrga(@PathVariable id: Int, @ModelAttribute orga: Organization): String {
        if (orga.name == null || orga.domain == null || orga.aliases == null) {
            return "redirect:/orgas/"
        }
        orga.id = id
        repo.save(orga)
        return "redirect:/orgas"
    }
    @GetMapping("/delete/{id}")
    fun deleteOrganization(@PathVariable id: Int,@ModelAttribute orga: Organization, attrs:RedirectAttributes) : String {
        attrs.addFlashAttribute("dlt",
            UISuppression.suppression("Suppression", "Etes vous sur ?","error", id))
        return "redirect:/orgas?{id}"
    }

    @GetMapping("/deleteValid/{id}")
    fun deleteValidation(@PathVariable id: Int,@ModelAttribute orga: Organization, attrs:RedirectAttributes) : String {
        repo.deleteById(id.toLong())
        return "redirect:/orgas"
    }

    @GetMapping("/display/{id}")
    fun displayOrganization(@PathVariable id: Long) : String {
        return "Afficher les détails de l'organisation avec l'id $id"
    }
    @GetMapping("/search/")
    fun searchOrganization() : String {
        return "Rechercher une organisation"
    }
    @GetMapping("/details/{id}")
    fun detailsOrganization(@PathVariable id: Long) : String {
        return "Afficher les détails de l'organisation avec l'id $id"
    }
}