package edu.spring.btp.repositories

import edu.spring.btp.entities.Domain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DomainRepository:JpaRepository<Domain, Int> {
    @Query(nativeQuery = true,value="SELECT * FROM \"domain\" ORDER BY rand() LIMIT 1")
    fun getRandomDomain(): Domain

    @Query(nativeQuery = true,value="SELECT * FROM \"domain\" INNER JOIN \"domain_providers\" ON \"providers_id\"=:providerId ORDER BY rand() LIMIT 1")
    fun getRandomDomainFromProvider(providerId:Int): Domain

    @Query(nativeQuery = true,value="SELECT * FROM \"domain\" WHERE \"domain_name\" = :name")
    fun findByName(name: String): Domain?


    @Query(nativeQuery = true,value="SELECT * FROM \"domain\" WHERE \"domain_parent_id\" = :parentName")
    fun findByParentName(parentName: String): List<Domain>

    @Query(nativeQuery = true,value="SELECT * FROM \"domain\" WHERE \"domain_parent\" IS NULL")
    fun findByParentIsNull(): List<Domain>

    @Query(nativeQuery = true,value="SELECT * FROM \"domain\" WHERE \"domain_parent_id\" = :parentId")
    fun findByParentId(parentId: Long): List<Domain>

    @Query(nativeQuery = true,value="SELECT * FROM \"domain\" WHERE \"domain_parent_id\" = :parentId ORDER BY RAND() LIMIT 1")
    fun findByParentIdOrderByRandom(parentId: Long): Domain?


}