package edu.spring.td2.entities

import jakarta.persistence.*


@Entity
@Table(name = "TD2_Groupe")
class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id = 0
    private var name: String? = null
    private var email: String? = null
    private var aliases: String? = null

    @ManyToOne
    @JoinColumn(name = "idOrganization", nullable = false)
    private var organization: Organization? = null

    @ManyToMany
    @JoinTable(name = "user_group")
    private var users: Set<User>? = null
}