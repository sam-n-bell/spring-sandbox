package com.togglr.testapp.repositories;

import com.togglr.testapp.entities.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "users", path="users") //makes it /users/ and not /applicationUsers/
public interface UserRepository extends JpaRepository<ApplicationUser, Integer> {

    public ApplicationUser findByEmail(@Param("email") String email);
}
