package edu.spring.stories.entities

import jakarta.persistence.*
import java.awt.Color

@Entity
@Table(name = "Tag")
class  Tag () {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Int = 0

    var color: String = ""
    var label: String = ""

    constructor(color: String, label: String) : this() {
        this.color = color
        this.label = label
    }
}