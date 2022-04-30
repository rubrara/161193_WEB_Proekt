package mk.ukim.finki.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    @Column(nullable = true, length = 64)
    private String photo;

    @OneToOne(mappedBy = "author")
    private User user;

    public Author(String name, String surname, String photo) {
        this.name = name;
        this.surname = surname;
        this.photo = photo;
    }

    public Author() {
    }
}
