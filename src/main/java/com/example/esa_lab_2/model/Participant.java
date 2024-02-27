package com.example.esa_lab_2.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "participants", schema = "public")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private int age;

    @Column(name = "academic_degree")
    private String academicDegree;
}
