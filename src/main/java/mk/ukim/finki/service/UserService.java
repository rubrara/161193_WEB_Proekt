package mk.ukim.finki.service;

import mk.ukim.finki.model.Role;
import mk.ukim.finki.model.User;

public interface UserService {

    User register(String username, String password, String repeatPassword, String email, Role role);

}
