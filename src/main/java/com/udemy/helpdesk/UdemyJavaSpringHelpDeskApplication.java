package com.udemy.helpdesk;

import com.udemy.helpdesk.api.entities.User;
import com.udemy.helpdesk.api.enums.ProfileEnum;
import com.udemy.helpdesk.api.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UdemyJavaSpringHelpDeskApplication {

    public static void main(String[] args) {
        SpringApplication.run(UdemyJavaSpringHelpDeskApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            initUsers(userRepository, passwordEncoder);
        };
    }

    private void initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder){
        User admin = new User();
        admin.setEmail("admin@helpdesk.com");
        admin.setPassword(passwordEncoder.encode("senha"));
        admin.setProfile(ProfileEnum.ROLE_ADMIN);

        User find = userRepository.findByEmail("admin@helpdesk.com");
        if(find == null){
            userRepository.save(admin);
        }
    }
}
