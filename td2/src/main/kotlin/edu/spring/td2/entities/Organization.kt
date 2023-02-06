package edu.spring.td2.entities

import jakarta.persistence.*


@Entity
@Table(name = "TD2_Organization")
class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id = 0
    var name: String? = null
    var domain: String? = null
    var aliases: String? = null
    //private val organizationsettings: String[] = null

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "organization")
    private var groupes: Set<Groupe>? = null

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "organization")
    private var users: Set<User>? = null
}