package edu.spring.td1kotlin.controllers

import ch.qos.logback.core.model.Model
import edu.spring.models.Items
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.view.RedirectView

@SessionAttributes(value = ["categories"])
@Controller
@RequestMapping("/")
class MainController {

    @get:ModelAttribute("categories")
    val categories: List<String>
        get() = listOf("Amis", "Famille", "Professionnels")

   /* @get:ModelAttribute("items")
    val items: Set<Items>
        get() = HashSet()*/

    @GetMapping("")
    fun listAccueil(model: Model): String {
        return "categories"
    }

    @GetMapping("hello")
    @ResponseBody
    fun index(): String {
        return "Hello world!"
    }

    @RequestMapping(path = ["items"], method = [RequestMethod.POST, RequestMethod.GET])
    fun listItems(model: Model): String {
        //println(items)
        return "items"
    }

    @GetMapping("items/new")
    fun addItem(model: Model): String {
        return "itemsNew"
    }

    @PostMapping("items/addNew")
    fun addItem(@SessionAttribute("items") items: MutableSet<Items>, @RequestParam(name = "name", required = false) name: String): ModelAndView {
        items.add(Items(name))
        //println(items.get(0).nom)
        return ModelAndView("redirect:/items")
    }

    @GetMapping("items/inc/{nom}")
    fun incEval(@SessionAttribute("items") items: MutableSet<Items>, @PathVariable(name = "nom", required = false) nom: String): RedirectView {
        for (item in items) if (nom == item.nom) {
            item.evaluation++
        }
        return RedirectView("../../items")
    }

    @GetMapping("items/dec/{nom}")
    fun decEval(@SessionAttribute("items") items: MutableSet<Items>, @PathVariable(name = "nom", required = false) nom: String): RedirectView {
        for (item in items) if (nom == item.nom) {
            item.evaluation--
        }
        return RedirectView("../../items")
    }

    @GetMapping("items/delete/{nom}")
    fun deleteItem(@SessionAttribute("items") items: MutableSet<Items>, @PathVariable(name = "nom", required = false) nom: String): RedirectView {
        for (i in items.indices) if (nom == items.elementAt(i).nom) {
            items.remove(items.elementAt(i))
        }
        return RedirectView("../../items")
    }
}