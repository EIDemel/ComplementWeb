package edu.spring.td2.entities

import edu.spring.td2.entities.Groupe
import edu.spring.td2.entities.Organization
import jakarta.persistence.*


@Entity
@Table(name = "TD2_User")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id = 0
    private var firstname: String? = null
    private var lastname: String? = null
    private var email: String? = null
    private var password: String? = null
    private var suspended: Boolean = false

    @ManyToOne
    private var organization: Organization? = null

    @ManyToMany(mappedBy = "users")
    private var groupes: Set<Groupe>? = null
}