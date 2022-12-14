package net.proselyte.springbootdemo;

import net.proselyte.springbootdemo.model.Role;
import net.proselyte.springbootdemo.model.User;
import net.proselyte.springbootdemo.repository.UserRepository;
import net.proselyte.springbootdemo.service.RoleService;
import net.proselyte.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class SpringBootDemoApplication {

	@Autowired
	public SpringBootDemoApplication(UserRepository userRepository) {
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService, RoleService roleService) {
		return args -> {
			Role roleAdmin = new Role("ADMIN");
			Role roleUser = new Role("USER");

			User admin1 = new User("admin1", "admin1", 10, "admin1@mail", "admin1", Arrays.asList(roleAdmin, roleUser));
			User admin2 = new User("admin2", "admin2", 20, "admin2@mail", "admin2", Collections.singletonList(roleAdmin));
			User user1 = new User("user1", "user1", 10, "user1@mail", "user1", Collections.singletonList(roleUser));
			User user2 = new User("user2", "user2", 20, "user2@mail", "user2", Collections.singletonList(roleUser));


			roleService.create(roleAdmin);
			roleService.create(roleUser);

			userService.create(admin1);
			userService.create(admin2);
			userService.create(user1);
			userService.create(user2);
		};
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void test() {
//		List<User> user1 = userRepository.findAll();
//		System.out.println(user1);
//	}

}
