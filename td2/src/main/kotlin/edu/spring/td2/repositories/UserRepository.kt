package edu.spring.td2.repositories

import edu.spring.td2.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository : JpaRepository<User?, Long?>