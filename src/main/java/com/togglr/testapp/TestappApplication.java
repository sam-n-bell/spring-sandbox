package com.togglr.testapp;

import com.togglr.testapp.entities.ApplicationUser;
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

	// run at startup with PostConstruct
	@PostConstruct
	public void init() {
		List<ApplicationUser> users = new ArrayList<>();
		users.add(new ApplicationUser(1, "Sam Bell", "sam.bell@utexas.edu"));
		users.add(new ApplicationUser(2, "Joshua Bell", "jbell@testing.io"));
		userRepository.saveAll(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(TestappApplication.class, args);
	}

}
