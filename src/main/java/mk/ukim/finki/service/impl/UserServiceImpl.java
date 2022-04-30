package mk.ukim.finki.service.impl;

import mk.ukim.finki.model.Role;
import mk.ukim.finki.model.User;
import mk.ukim.finki.model.exceptions.*;
import mk.ukim.finki.repository.UserRepository;
import mk.ukim.finki.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


//    public UserDetails loginUserByUsername(String username) throws UsernameNotFoundException{
//        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username is false."));
//    }


    @Override
    public User register(String username, String password, String repeatPassword, String email, Role role) {

        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new InvalidMatchingPasswordException();
        if (userRepository.findByUsername(username).isPresent())
            throw new UsernameExistsException();
        if (password.length() < 5)
            throw new PasswordIsTooShortException();



        User user = new User(username, passwordEncoder.encode(password), email, role);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByUsername(username).orElseThrow(InvalidUsernameException::new);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Stream.of(new SimpleGrantedAuthority(user.getRole().toString())).collect(Collectors.toList()));

        return userDetails;
    }
}
