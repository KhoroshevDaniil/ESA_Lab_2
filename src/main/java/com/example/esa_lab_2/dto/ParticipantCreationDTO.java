package com.example.esa_lab_2.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantCreationDTO {
    private String name;
    private int age;
    private String academicDegree;
}
