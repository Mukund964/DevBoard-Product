package org.example.devboardproduct.services;

import org.example.devboardproduct.entities.User;
import org.example.devboardproduct.entities.userRole;
import org.example.devboardproduct.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


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

        role = (role != null) ? role : userRole.DEVELOPER;

        User newUser = User.builder()
                        .name(name)
                        .email(email)
                        .Role(role)
                        .build();

        return UserRepo.save(newUser);
    }

    @Override
    public User getUser(int id) {
        Optional<User> userWithGivenId = UserRepo.findById(id);
        if(userWithGivenId.isEmpty()) throw new RuntimeException("User with given Id not found");
        else return userWithGivenId.get();
    }


}
