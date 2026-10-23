package com.manish.electronic.store;

import com.manish.electronic.store.entities.Role;
import com.manish.electronic.store.entities.User;
import com.manish.electronic.store.repositories.RoleRepository;
import com.manish.electronic.store.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
@EnableWebMvc
public class ElectronicStoreApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ElectronicStoreApplication.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository repository;
    @Value("${normal.role.id}")
    private String role_normal_id;
    @Value("${admin.role.id}")
    private String role_admin_id;

    @Autowired
    private UserRepository userRepository;

    @Value("${app.demo.admin.email}")
    private String demoAdminEmail;
    @Value("${app.demo.admin.password}")
    private String demoAdminPassword;
    @Value("${app.demo.user.email}")
    private String demoUserEmail;
    @Value("${app.demo.user.password}")
    private String demoUserPassword;



    @Override
    public void run(String... args) throws Exception {

        try {

            Role role_admin = Role.builder().roleId(role_admin_id).roleName("ROLE_ADMIN").build();
            Role role_normal = Role.builder().roleId(role_normal_id).roleName("ROLE_NORMAL").build();


            User adminUser = User.builder()
                    .name("admin")
                    .email(demoAdminEmail)
                    .password(passwordEncoder.encode(demoAdminPassword))
                    .gender("Male")
                    .imageName("default.png")
                    .roles(Set.of(role_admin, role_normal))
                    .userId(UUID.randomUUID().toString())
                    .about("I am admin User")
                    .build();

            User normalUser = User.builder()
                    .name("demo user")
                    .email(demoUserEmail)
                    .password(passwordEncoder.encode(demoUserPassword))
                    .gender("Male")
                    .imageName("default.png")
                    .roles(Set.of(role_normal))
                    .userId(UUID.randomUUID().toString())
                    .about("I am Normal User")
                    .build();

            repository.save(role_admin);
            repository.save(role_normal);


            // seed demo accounts only once
            if (userRepository.findByEmail(demoAdminEmail).isEmpty()) {
                userRepository.save(adminUser);
            }
            if (userRepository.findByEmail(demoUserEmail).isEmpty()) {
                userRepository.save(normalUser);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
