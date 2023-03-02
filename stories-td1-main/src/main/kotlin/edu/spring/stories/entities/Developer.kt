package edu.spring.stories.entities

import jakarta.persistence.*

@Entity
@Table(name = "Developer")
class Developer () {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Int = 0
    var firstname : String = ""
    var lastname : String = ""

    @OneToMany
    @JoinColumn( nullable = false)
    var stories: MutableSet<Story> = mutableSetOf()

    constructor(firstname: String, lastname: String) : this() {
        this.firstname = firstname
        this.lastname = lastname
    }

    fun addStory(sto : Story){
        this.stories?.add(sto)
        for(storie in stories!!) {
            storie.developer = null
        }
    }

    fun giveUpStory(sto : Story){
        this.stories?.remove(sto)
        sto.developer = null
    }

    @PreRemove
    fun preRemove(){
        this.stories?.clear()
    }

}

