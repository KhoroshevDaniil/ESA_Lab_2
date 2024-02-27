package com.example.esa_lab_2.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "papers", schema = "public")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private int year;

    @ManyToOne(targetEntity = Participant.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "participant_id")
    private Participant author;

    @Override
    public String toString() {
        return String.format("Id: %d, year: %d, title: %s", id, year, title);
    }
}
