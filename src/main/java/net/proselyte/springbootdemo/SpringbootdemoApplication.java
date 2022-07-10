package net.proselyte.springbootdemo;

import net.proselyte.springbootdemo.model.Role;
import net.proselyte.springbootdemo.model.User;
import net.proselyte.springbootdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringbootdemoApplication {

	@Autowired
	UserRepository userRepository;
//	@Autowired
//	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void test() {
		List<Role> roles1 = new ArrayList<>();
		roles1.add(new Role(3L, "p"));
		User user = new User(3L, "p", "p", "p", "p", roles1);
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setPassword(user.getPassword());
//		String user = userRepository.findByEmail("admin").toString();
		System.out.println(user);

		System.out.println(userRepository.findAll());
	}

}
