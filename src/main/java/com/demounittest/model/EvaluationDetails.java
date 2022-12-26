package com.demounittest.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class EvaluationDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Evaluations evaluations;

    @ManyToOne
    private Skills skills;

    @Column(nullable = false)
    private String evaluation;
}
