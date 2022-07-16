package com.in28minutes.learning.jpa.jpain10steps;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.in28minutes.learning.jpa.jpain10steps.entity.User;
import com.in28minutes.learning.jpa.jpain10steps.repository.UserReposity;
import com.in28minutes.learning.jpa.jpain10steps.service.UserDAOService;

@Component
public class UserRepositoryCommandLineRunner2 implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(UserRepositoryCommandLineRunner2.class);
	
	@Autowired
	private UserReposity userReposity;
	
	@Override
	public void run(String... args) throws Exception {
	
		User user = new User("Jill", "Admin");
		//New User is created : User(id=1, name=Jack, role=Admin)
		userReposity.save(user);
		
		log.info("New User is created : "+ user);
		
		Optional<User> userWithIdOne = userReposity.findById(1L);
		log.info("User 1 : "+ userWithIdOne);
		
		List<User> users = userReposity.findAll();
		log.info("All users : "+users );
		
	}

}
