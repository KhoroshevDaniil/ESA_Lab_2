package com.example.esa_lab_2.service;

import com.example.esa_lab_2.model.Participant;
import com.example.esa_lab_2.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    public List<Participant> getAll() {
        return participantRepository.findAll();
    }

    public Optional<Participant> getById(Long id) {
        return participantRepository.findById(id);
    }

    public Participant getByName(String name) {
        return participantRepository.findByName(name);
    }

    public void create(Participant newParticipant) {
        participantRepository.save(newParticipant);
    }

    public void deleteById(Long id) {
        participantRepository.deleteById(id);
    }
}
