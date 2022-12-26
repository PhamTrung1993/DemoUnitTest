package com.demounittest.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "outcomes")
public class Outcomes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    private Templates templates;
}
