package com.letsplay.api.service;

import com.letsplay.api.model.User;
import com.letsplay.api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    
    public DatabaseSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setId("69b8929600065b3fa068");
            admin.setName("Super Admin");
            admin.setEmail("admin@letsplay.com");
            // On hashe le mot de passe manuellement pour l'instant
            admin.setPassword(new BCryptPasswordEncoder().encode("Admin123!"));
            admin.setRole("ROLE_ADMIN");
            
            userRepository.save(admin);
            System.out.println("✅ Administrateur par défaut créé !");
        }
    }
}