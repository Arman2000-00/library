package am.tt.library;

import am.tt.library.model.UserType;
import am.tt.library.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import am.tt.library.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("admin@mail.com").isEmpty()) {
                userRepository.save(User.builder()
                        .name("admin")
                        .surname("admin")
                        .email("admin@mail.com")
                        .password(passwordEncoder.encode("admin"))
                        .userType(UserType.ADMIN)
                        .build());
            }
        };
    }

}
