package com.example.esa_lab_2.controller.v1;


import com.example.esa_lab_2.model.Paper;
import com.example.esa_lab_2.model.Participant;
import com.example.esa_lab_2.service.PaperService;
import com.example.esa_lab_2.service.ParticipantService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/papers")
public class PaperController {
    private final PaperService paperService;
    private final ParticipantService participantService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("papers", paperService.getAll());
        model.addAttribute("paper", new Paper());
        model.addAttribute("participants", participantService.getAll());
        return "papers";
    }

    @PostMapping("/create")
    public String createPaper(@ModelAttribute Paper paper) {
        paperService.create(paper);
        return "redirect:/papers";
    }

    @PostMapping("/delete/{id}")
    public String deletePaper(@PathVariable("id") Long id) {
        paperService.deleteById(id);
        return "redirect:/papers";
    }

    @GetMapping("/search")
    @ResponseBody
    public String searchPapers(@RequestParam String author) {
        List<Paper> relatedPapers = paperService.getByParticipantName(author);
        return relatedPapers.toString();
    }
}
