package mk.ukim.finki.service.impl;

import mk.ukim.finki.model.Author;
import mk.ukim.finki.model.Role;
import mk.ukim.finki.model.User;
import mk.ukim.finki.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.model.exceptions.InvalidUsernameException;
import mk.ukim.finki.repository.AuthorRepository;
import mk.ukim.finki.repository.UserRepository;
import mk.ukim.finki.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(UserRepository userRepository, AuthorRepository authorRepository) {
        this.userRepository = userRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Author register(String username, String name, String surname, String photo) {

        if (name == null || name.isEmpty() || surname == null || surname.isEmpty()){
            throw new InvalidArgumentsException();
        }
        Author author = new Author(name, surname, photo);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new InvalidUsernameException());

        user.setRole(Role.ROLE_AUTHOR);
        user.setAuthor(author);
        userRepository.save(user);

        return authorRepository.save(author);
    }

    @Override
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseGet(Author::new);
    }

}
