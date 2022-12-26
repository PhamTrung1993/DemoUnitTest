package com.demounittest.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "classes")
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne
    private Coach coach;

    @ManyToOne
    private Programs programs;
}
