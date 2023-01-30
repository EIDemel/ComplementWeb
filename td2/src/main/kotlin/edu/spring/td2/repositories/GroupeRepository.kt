package edu.spring.td2.repositories

import edu.spring.td2.entities.Groupe
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GroupeRepository : CrudRepository<Groupe?, Long?>{

}