package com.example.esa_lab_2.service;

import com.example.esa_lab_2.dto.ParticipantCreationDTO;
import com.example.esa_lab_2.model.Participant;
import com.example.esa_lab_2.repository.ParticipantRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public void create(ParticipantCreationDTO dto) {
        Participant participant = Participant.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .academicDegree(dto.getAcademicDegree())
                .build();
        participantRepository.save(participant);
    }

    public void deleteById(Long id) {
        Optional<Participant> participant = participantRepository.findById(id);
        if (participant.isPresent())
            participantRepository.deleteById(id);
        else
            throw new EntityNotFoundException();
    }
}
