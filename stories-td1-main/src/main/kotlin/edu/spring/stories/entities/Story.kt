package edu.spring.stories.entities

import jakarta.persistence.*

@Entity
class Story() {
    constructor(name: String) : this() {
        this.name = name
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id : Int = 0

    @Column(length = 20, unique = true, nullable = false)
    open lateinit var name : String

    @ManyToOne
    open var developer: Developer? = null

    @OneToMany
    open var tags = mutableSetOf<Tag>()

}
