package service;

import java.util.Optional;

import entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import repository.UserRepository;

@ApplicationScoped
public class UserService {
    private final UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUser(long id) {
        return Optional.ofNullable(userRepository.findById(id));
    }

    @Transactional
    public User createUser(User user) {
        userRepository.persist(user);
        return user;
    }
}
