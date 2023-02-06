package edu.spring.td2.repositories

import edu.spring.td2.entities.Organization
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface OrganizationRepository : JpaRepository<Organization?, Long?>{
    fun findByDomain(domain: String?): List<Organization?>?
    fun findByName(name: String?): List<Organization?>?
}