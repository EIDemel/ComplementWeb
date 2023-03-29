package edu.spring.btp.controllers

import edu.spring.btp.entities.Complaint
import edu.spring.btp.entities.Domain
import edu.spring.btp.entities.Provider
import edu.spring.btp.repositories.ComplaintRepository
import edu.spring.btp.repositories.DomainRepository
import edu.spring.btp.repositories.ProviderRepository
import edu.spring.btp.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*

@Controller
class MainController {

    @Autowired
    private lateinit var domainRepository: DomainRepository

    @Autowired
    private lateinit var complaintRepository: ComplaintRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var providerRepository: ProviderRepository


    @GetMapping(value = ["/", "", "/index"])
    fun getDomainList(model: Model): String {
        // Ajoutez vos domaines ici à partir de la racine
        model["domain"] = domainRepository.findByParentIsNull().get(0)
        model["domain-children"] = domainRepository.findByParentIsNull().get(0).children
        model["domain-children-cumule"] = domainRepository.findByParentIsNull().get(0).getComplaintsCount()

        return "index"
    }

    @GetMapping("/domain/{domain}")
    fun domainChild(@PathVariable domain: String, model: Model ): String{

        model["domain-child"] = domainRepository.findByParentName(domain)
        model["parent"] = domainRepository.findByParentName(domain).get(0).parent!!
        return "domainList"
    }

    @GetMapping("/complaints/{domain}")
    fun complaintsList(@PathVariable domain: String, model: Model ): String{

        model["parent"] = domainRepository.findByName(domain)?.parent!!
        model["complaints"] = domainRepository.findByName(domain)?.complaints!!
        model["complaints-provider"] = domain

        return "listeDeComplaints"
    }
    @RequestMapping(value = ["/complaints/{domain}/new"], method = [RequestMethod.GET, RequestMethod.POST])
    fun complaintsNew(@RequestParam("story-action") storyAction: String, @PathVariable domain: String, model: Model): String {

        model["domain"] = domain
        model["provider"] = domainRepository.findByName(domain)!!.providers



        if (storyAction == "travel"){
            return "forms/complaint"
        }

        if (storyAction == "add"){
            val title = model.getAttribute("title") as String?
            val description = model.getAttribute("description") as String?
            val provider = model.getAttribute("provider") as String

            if (title != null && description != null) {
                    val complaint = Complaint(title, description, userRepository.findById(1).get(), providerRepository.findById(provider.toInt()).get() , domainRepository.findByName(domain)!!)
                    complaintRepository.save(complaint)
            }
            return ""
        }

        return "redirect:/"

    }

}