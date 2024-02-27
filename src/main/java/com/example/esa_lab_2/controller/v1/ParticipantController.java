package com.example.esa_lab_2.controller.v1;

import com.example.esa_lab_2.model.Participant;
import com.example.esa_lab_2.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("participants", participantService.getAll());
        model.addAttribute("participant", new Participant());
        return "participants";
    }

    @PostMapping("/create")
    public String createParticipant(@ModelAttribute Participant participant) {
        participantService.create(participant);
        return "redirect:/participants";
    }

    @PostMapping("/delete/{id}")
    public String deleteParticipant(@PathVariable("id") Long id) {
        participantService.deleteById(id);
        return "redirect:/participants";
    }
}
