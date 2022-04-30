package mk.ukim.finki.service.impl;

import mk.ukim.finki.model.User;
import mk.ukim.finki.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.repository.UserRepository;
import mk.ukim.finki.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }


    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentsException();

        return userRepository.findByUsername(username).orElseThrow(InvalidUsernameOrPasswordException::new);
    }
}
