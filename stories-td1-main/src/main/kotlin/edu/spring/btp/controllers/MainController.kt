package edu.spring.btp.controllers

import edu.spring.btp.repositories.DomainRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class MainController {

    @Autowired
    private lateinit var domainRepository: DomainRepository
    @GetMapping(value = ["/", "", "/index"])
    fun getDomainList(model: Model): String {
        // Ajoutez vos domaines ici à partir de la racine
        model["domain"] = domainRepository.findByParentIsNull().get(0)
        model["domain-children"] = domainRepository.findByParentIsNull().get(0).children
        return "index"
    }
}