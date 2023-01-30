package edu.spring.td2.entities

import jakarta.persistence.*


@Entity
@Table(name = "TD2_Organization")
class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id = 0
    private var name: String? = null
    private var domain: String? = null
    private var aliases: String? = null
    //private val organizationsettings: String[] = null

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "organization")
    private var groupes: Set<Groupe>? = null

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "organization")
    private var users: Set<User>? = null
}