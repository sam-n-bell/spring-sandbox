package com.togglr.testapp.controllers;

import com.togglr.testapp.entities.ApplicationUserEntity;
import com.togglr.testapp.entities.TaskEntity;
import com.togglr.testapp.repositories.TaskRepository;
import com.togglr.testapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class ApplicationUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    /**
     * One way to sort a nested entity
     * @param id
     * @param sort
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}/details")
    public ResponseEntity<?> returnUserDetails(@PathVariable("id") int id, Sort sort) {
        ApplicationUserEntity appUser = userRepository.findById(id);
        List<TaskEntity> userTasks = taskRepository.findAllByUserId(id, sort);
        appUser.setTasksById(userTasks);
        return ResponseEntity.ok(appUser);
    }



}
