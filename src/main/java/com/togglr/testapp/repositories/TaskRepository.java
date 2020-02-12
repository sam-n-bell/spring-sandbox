package com.togglr.testapp.repositories;

import com.togglr.testapp.entities.ApplicationUserEntity;
import com.togglr.testapp.entities.TaskEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "tasks", path="tasks") //makes it /tasks/ and not /taskentity/
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

}
