package com.example.esa_lab_2.repository;

import com.example.esa_lab_2.model.Paper;
import com.example.esa_lab_2.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaperRepository extends JpaRepository<Paper, Long> {
    List<Paper> findByAuthor(Participant author);
}
