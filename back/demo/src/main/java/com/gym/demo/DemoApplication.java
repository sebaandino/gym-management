package com.gym.demo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gym.demo.models.Role;
import com.gym.demo.models.RoleEnum;
import com.gym.demo.models.UserEntity;
import com.gym.demo.repository.RoleRepository;
import com.gym.demo.repository.UserRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository, RoleRepository roleRepository) {
		return (args) -> {

			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			Role roleAdmin = Role.builder()
					.roleEnum(RoleEnum.ADMIN)
					.build();

			Role roleTrainer = Role.builder()
					.roleEnum(RoleEnum.TRAINER)
					.build();

			Role roleUser = Role.builder()
					.roleEnum(RoleEnum.USER)
					.build();

			// Guardar roles en la base de datos
			roleAdmin = roleRepository.save(roleAdmin);
			roleTrainer = roleRepository.save(roleTrainer);
			roleUser = roleRepository.save(roleUser);

			UserEntity user1 = UserEntity.builder()
					.name("elias")
					.dni("41069568")
					.email("elias@gmail.com")
					.phone("1132901512")
					.password(passwordEncoder.encode("1234"))
					.createdAt(LocalDateTime.now())
					.isEnabled(true)
					.accountNoLocked(true)
					.accountNoExpired(true)
					.credentialNoExpired(true)
					.role(roleAdmin)
					.build();

			UserEntity user2 = UserEntity.builder()
					.name("rocio")
					.dni("43014305")
					.email("rocio@gmail.com")
					.phone("1149064966")
					.password(passwordEncoder.encode("1234"))
					.createdAt(LocalDateTime.now())
					.isEnabled(true)
					.accountNoLocked(true)
					.accountNoExpired(true)
					.credentialNoExpired(true)
					.role(roleTrainer)
					.build();

			UserEntity user3 = UserEntity.builder()
					.name("gladys")
					.dni("17010843")
					.email("gladys@gmail.com")
					.phone("1150227388")
					.password(passwordEncoder.encode("1234"))
					.createdAt(LocalDateTime.now())
					.isEnabled(true)
					.accountNoLocked(true)
					.accountNoExpired(true)
					.credentialNoExpired(true)
					.role(roleUser)
					.build();

			userRepository.saveAll(List.of(user1, user2, user3));
		};
	}
}