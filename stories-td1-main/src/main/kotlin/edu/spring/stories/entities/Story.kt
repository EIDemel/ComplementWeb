package edu.spring.stories.entities

import jakarta.persistence.*

@Entity
@Table(name = "Story")
class Story() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Int = 0
    var name : String = ""

    @ManyToOne
    @JoinColumn( nullable = false)
    var developer: Developer? = null

    @ManyToMany
    @JoinColumn( nullable = false)
    var tags: MutableSet<Tag> = mutableSetOf()


    constructor(name: String) : this() {
        this.name = name
    }




}
