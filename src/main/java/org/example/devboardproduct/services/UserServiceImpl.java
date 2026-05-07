package org.example.devboardproduct.services;

import org.example.devboardproduct.entities.User;
import org.example.devboardproduct.entities.userRole;
import org.example.devboardproduct.repository.UserRepository;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {
    private final UserRepository UserRepo;

    public UserServiceImpl(UserRepository userRepo) {
        UserRepo = userRepo;
    }

    @Override
    public User createUser(String name, String email, userRole role) {
        if(UserRepo.existsByEmail(email)) {
            throw new RuntimeException("Email already exists in the system");
        }

        role = (role != null) ? role : userRole.Developer;

        User newUser = User.builder()
                        .name(name)
                        .email(email)
                        .Role(role)
                        .build();

        return UserRepo.save(newUser);
    }
}
