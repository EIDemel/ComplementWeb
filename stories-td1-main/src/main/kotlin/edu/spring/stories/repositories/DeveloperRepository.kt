package edu.spring.stories.repositories

import edu.spring.stories.entities.Developer
import edu.spring.stories.entities.Story
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DeveloperRepository : CrudRepository<Developer, Int> {

    fun findByStoriesName(name : String) : List<Developer>

    fun findByFirstnameAndLastname(firstname : String, name : String) : Developer

}