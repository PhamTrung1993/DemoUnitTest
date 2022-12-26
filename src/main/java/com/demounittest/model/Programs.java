package com.demounittest.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Programs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;
}
