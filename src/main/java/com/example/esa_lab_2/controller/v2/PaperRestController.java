package com.example.esa_lab_2.controller.v2;

import com.example.esa_lab_2.dto.PaperCreationDTO;
import com.example.esa_lab_2.dto.PaperDTO;
import com.example.esa_lab_2.model.Paper;
import com.example.esa_lab_2.service.PaperService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.TEXT_HTML;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/papers")
public class PaperRestController {
    private final PaperService paperService;
    private final XmlMapper xmlMapper;

    @GetMapping()
    public ResponseEntity<?> getAllPapers(@RequestHeader("Accept") String header) {
        var papers = paperService.getAll()
                .stream()
                .map(PaperDTO::entityToDTO)
                .collect(Collectors.toList());

        if (header.contains(MediaType.TEXT_HTML_VALUE)) {
            try {
                Transformer transformer = TransformerFactory
                        .newInstance()
                        .newTemplates(new StreamSource("src/main/resources/xslt/papers.xslt"))
                        .newTransformer();

                StringWriter writer = new StringWriter();

                transformer.transform(
                        new StreamSource(new ByteArrayInputStream(xmlMapper.writeValueAsBytes(papers))),
                        new StreamResult(writer)
                );

                return ResponseEntity.ok().contentType(TEXT_HTML).body(writer.toString());
            } catch (TransformerException | JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        } else if (header.contains(MediaType.APPLICATION_XML_VALUE) ||
                header.contains(MediaType.APPLICATION_JSON_VALUE) ||
                header.contains(MediaType.ALL_VALUE))
            return ResponseEntity.ok(papers);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id,
                                     @RequestHeader("Accept") String header) {
        Optional<Paper> paperEntity = paperService.getById(id);
        if (paperEntity.isPresent()) {
            var participant = PaperDTO.entityToDTO(paperEntity.get());

            if (header.contains(MediaType.TEXT_HTML_VALUE)) {
                try {
                    Transformer transformer = TransformerFactory
                            .newInstance()
                            .newTemplates(new StreamSource("src/main/resources/xslt/paper.xslt"))
                            .newTransformer();
                    StringWriter writer = new StringWriter();
                    transformer.transform(new StreamSource(
                                    new ByteArrayInputStream(xmlMapper.writeValueAsBytes(participant))),
                            new StreamResult(writer));

                    return ResponseEntity.ok().contentType(TEXT_HTML).body(writer.toString());
                } catch (TransformerException | JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }

            else if (header.contains(MediaType.APPLICATION_XML_VALUE) ||
                    header.contains(MediaType.APPLICATION_JSON_VALUE) ||
                    header.contains(MediaType.ALL_VALUE))
                return ResponseEntity.ok(participant);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping(path = "/create",
            consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> postCreate(@RequestBody PaperCreationDTO dto) {
        paperService.create(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            paperService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
