package com.example.bookshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@IdClass(BookId.class)
public class Book {
    @Id
    private int id;
    @Id
    private String isbn;
    private String title;
    private String description;
    private double price;
    private int stock;
    private String imgUrl;


    public Book(int id, String isbn, String title, String description, double price, int stock,String imgUrl) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.imgUrl= imgUrl;
    }

    @ManyToOne
    private Author author;

    @ManyToOne
    private Publisher publisher;

    @ManyToMany
    private List<Genre> genres =
            new ArrayList<>();

    public void addGenre(Genre genre){
        genre.getBooks().add(this);
        genres.add(genre);
    }

    @ManyToOne
    private OrderItem orderItem;

}
