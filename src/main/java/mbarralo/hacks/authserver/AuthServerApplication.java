package mbarralo.hacks.authserver;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static java.util.Arrays.asList;


@SpringBootApplication
public class AuthServerApplication {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServerApplication(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }


    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            userRepository.save(new User("user", passwordEncoder.encode("password"), Collections.singletonList("USER")));
            userRepository.save(new User("admin", passwordEncoder.encode("password"), asList("USER", "ADMIN")));
        };
    }

    @Bean
    PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
