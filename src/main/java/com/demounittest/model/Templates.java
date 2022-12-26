package com.demounittest.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "templates")
public class Templates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Programs programs;
}
