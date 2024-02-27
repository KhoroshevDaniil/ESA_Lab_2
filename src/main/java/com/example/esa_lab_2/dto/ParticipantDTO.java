package com.example.esa_lab_2.dto;

import com.example.esa_lab_2.model.Participant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ParticipantDTO {
    private Long id;
    private String name;
    private int age;
    private String academicDegree;

    public static ParticipantDTO entityToDTO(Participant participantEntity) {
        return ParticipantDTO.builder()
                .id(participantEntity.getId())
                .name(participantEntity.getName())
                .age(participantEntity.getAge())
                .academicDegree(participantEntity.getAcademicDegree())
                .build();
    }
}
