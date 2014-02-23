package com.thesullies.allotment.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.thesullies.allotment.jpa.dto.User;

/**
 * Specifies methods used to store and retrieve CrewAuthorisation info.
 * 
 * @see http
 *      ://blog.springsource.com/2011/02/10/getting-started-with-spring-data-
 *      jpa/ for background on this pattern
 */
@Transactional(readOnly = true)
public interface I_Users extends JpaRepository<User, Long> {

   @Query("SELECT e FROM User e where  UPPER(userName) = upper(?1)")
   User loadUserByUserNameForSpringSecurity(String username);
}
