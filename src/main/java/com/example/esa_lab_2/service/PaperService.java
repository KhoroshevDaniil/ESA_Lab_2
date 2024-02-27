package com.example.esa_lab_2.service;


import com.example.esa_lab_2.dto.PaperCreationDTO;
import com.example.esa_lab_2.model.Paper;
import com.example.esa_lab_2.model.Participant;
import com.example.esa_lab_2.repository.PaperRepository;
import com.example.esa_lab_2.repository.ParticipantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaperService {
    private final PaperRepository paperRepository;
    private final ParticipantRepository participantRepository;

    public List<Paper> getAll() {
        return paperRepository.findAll();
    }

    public List<Paper> getByParticipantName(String name) {
        Participant author = participantRepository.findByName(name);
        return paperRepository.findByAuthor(author);
    }

    public Optional<Paper> getById(Long id) {
        return paperRepository.findById(id);
    }

    public void create(Paper newPaper) {
        paperRepository.save(newPaper);
    }

    public void create(PaperCreationDTO dto) {
        Participant author = participantRepository.findByName(dto.getAuthor());

        if (author != null) {
            Paper paper = Paper.builder()
                    .title(dto.getTitle())
                    .year(dto.getYear())
                    .author(author)
                    .build();
            paperRepository.save(paper);
        }
    }

    public void deleteById(Long id) {
        Optional<Paper> paper = paperRepository.findById(id);
        if (paper.isPresent()) {
            paperRepository.deleteById(id);
        }
        else {
            throw new EntityNotFoundException();
        }
    }
}
