package mk.ukim.finki.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.awt.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private String description;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    private Author author;

    @Column(columnDefinition = "text")
    private String image;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Material material;

    public Product(String name, Double price, Category category, Material material, String description, String image, Author author) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.material = material;
        this.description = description;
        this.image = image;
        this.author = author;
    }




    public Product(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;

    }


    public Product() {

    }



}
