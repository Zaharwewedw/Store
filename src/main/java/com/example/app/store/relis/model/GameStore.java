package com.example.app.store.relis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "store",schema = "users")
public class GameStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    private String name;
    @Column
    private int price;
    @Column
    private String genre;

    public GameStore () {}
    public GameStore (String name, int price, String genre) {
        this.name = name;
        this.price = price;
        this.genre = genre;
    }
}
