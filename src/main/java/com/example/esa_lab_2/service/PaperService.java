package com.example.esa_lab_2.service;


import com.example.esa_lab_2.model.Paper;
import com.example.esa_lab_2.model.Participant;
import com.example.esa_lab_2.repository.PaperRepository;
import com.example.esa_lab_2.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void create(Paper newPaper) {
        paperRepository.save(newPaper);
    }

    public void deleteById(Long id) {
        paperRepository.deleteById(id);
    }
}
