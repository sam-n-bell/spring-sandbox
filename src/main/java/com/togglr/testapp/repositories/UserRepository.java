package com.togglr.testapp.repositories;

import com.togglr.testapp.entities.ApplicationUserEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users", path="users") //makes it /users/ and not /applicationUsers/
public interface UserRepository extends JpaRepository<ApplicationUserEntity, Integer> {

    /**
     * Find one and only one (requirement) by email
     */
    public ApplicationUserEntity findByEmail(@Param("email") String email);

    /**
     * How to do search using order (sort) by in method name (probably not good?)
     * @param email
     * @return
     */
    public List<ApplicationUserEntity> findAllByEmailOrderByIdDesc(@Param("email") String email);

    /**
     * Example of how to do sorting in a search. If no search query param is included, data is still returned
     * @param email
     * @param sort
     * @return
     */
    public List<ApplicationUserEntity> findAllByEmail(@Param("email") String email, Sort sort);

    public ApplicationUserEntity findById(@Param("id") int id);
}
