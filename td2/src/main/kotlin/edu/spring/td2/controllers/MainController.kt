package edu.spring.td2.controllers

import edu.spring.td2.entities.Organization
import edu.spring.td2.repositories.OrganizationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/orgas")
class MainController {


    @Autowired
    lateinit var repo: OrganizationRepository
    @GetMapping("")
    fun index(model: ModelMap): String {
        model["orgas"] =  repo.findAll()
        return "accueil"
    }


    @GetMapping("/new")
    fun newOrga(): String {
        return "newOrga"
    }
    @PostMapping("/addNew")
    fun addNewOrga(@ModelAttribute orga: Organization): String {
        if (orga.name == null || orga.domain == null || orga.aliases == null) {
            return "redirect:/orgas/new"
        }
        repo.save(orga)
        return "redirect:/orgas"
    }

    @GetMapping("/edit/{id}")
    fun editOrganization(@PathVariable id: Long) : String {
        return "Modifier l'organisation avec l'id $id"
    }

    @GetMapping("/delete/{id}")
    fun deleteOrganization(@PathVariable id: Long) : String {
        return "Supprimer l'organisation avec l'id $id"
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