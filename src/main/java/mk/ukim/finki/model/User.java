package mk.ukim.finki.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table(name = "shop_users")
public class User {

    @Id
    private String username;

    private String password;

    private String email;

    @Enumerated()
    private Role role;


    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_author",
            joinColumns =
                    { @JoinColumn(name = "shop_users_id", referencedColumnName = "username") },
            inverseJoinColumns =
                    { @JoinColumn(name = "author_id", referencedColumnName = "id") })
    private Author author;


    public User() {
    }


    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
