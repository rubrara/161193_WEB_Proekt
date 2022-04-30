package mk.ukim.finki.service;

import mk.ukim.finki.model.Author;
import mk.ukim.finki.model.User;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author register(String username, String name, String surname, String photo);

    List<Author> listAuthors();

    Author findById(Long id);
}
