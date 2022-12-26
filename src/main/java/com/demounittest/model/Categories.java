package com.demounittest.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String categoryId;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Outcomes outComes;
}
