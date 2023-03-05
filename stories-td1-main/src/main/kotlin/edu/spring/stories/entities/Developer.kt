package edu.spring.stories.entities

import jakarta.persistence.*

@Entity
class Developer () {
    constructor(firstname: String, lastname: String) : this() {
        this.firstname = firstname
        this.lastname = lastname
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id : Int = 0

    @Column(length = 20, unique = true, nullable = false)
    open var firstname : String = ""

    @Column(length = 20, unique = true, nullable = false)
    open var lastname : String = ""

    @OneToMany(mappedBy = "developer", fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    open val stories = mutableSetOf<Story>()

    fun addStory(sto : Story){
        if(stories.add(sto)) sto.developer = this
    }
    fun giveUpStory(sto : Story){
        if(stories.remove(sto)) sto.developer = null
    }
    @PreRemove
    fun preRemove(){
        stories.forEach{ it.developer = null}
    }

}

