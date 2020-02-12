package com.togglr.testapp;

import com.togglr.testapp.entities.ApplicationUserEntity;
import com.togglr.testapp.entities.TaskEntity;
import com.togglr.testapp.repositories.TaskRepository;
import com.togglr.testapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TestappApplication {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TaskRepository taskRepository;

	// run at startup with PostConstruct
	@PostConstruct
	public void init() {



	}

	public static void main(String[] args) {
		SpringApplication.run(TestappApplication.class, args);
	}

}
