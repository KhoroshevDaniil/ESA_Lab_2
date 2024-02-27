package com.example.esa_lab_2.dto;

import com.example.esa_lab_2.model.Paper;
import com.example.esa_lab_2.model.Participant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaperDTO {
    private Long id;
    private String title;
    private int year;
    private String authorName;
    private Long authorId;

    public static PaperDTO entityToDTO(Paper paperEntity) {
        Participant authorEntity = paperEntity.getAuthor();
        return PaperDTO.builder()
                .id(paperEntity.getId())
                .title(paperEntity.getTitle())
                .year(paperEntity.getYear())
                .authorName(authorEntity.getName())
                .authorId(authorEntity.getId())
                .build();
    }
}
