package com.example.esa_lab_2.controller.v2;

import com.example.esa_lab_2.dto.ParticipantCreationDTO;
import com.example.esa_lab_2.dto.ParticipantDTO;
import com.example.esa_lab_2.model.Participant;
import com.example.esa_lab_2.service.ParticipantService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.TEXT_HTML;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/participants")
public class ParticipantRestController {

    private final ParticipantService participantService;
    private final XmlMapper xmlMapper;

    @GetMapping()
    public ResponseEntity<?> getAllParticipants(@RequestHeader("Accept") String header) {
        var participants = participantService.getAll()
                .stream()
                .map(ParticipantDTO::entityToDTO)
                .collect(Collectors.toList());

        if (header.contains(MediaType.TEXT_HTML_VALUE)) {
            try {
                Transformer transformer = TransformerFactory
                        .newInstance()
                        .newTemplates(new StreamSource("src/main/resources/xslt/participants.xslt"))
                        .newTransformer();

                StringWriter writer = new StringWriter();

                transformer.transform(
                        new StreamSource(new ByteArrayInputStream(xmlMapper.writeValueAsBytes(participants))),
                        new StreamResult(writer)
                );

                return ResponseEntity.ok().contentType(TEXT_HTML).body(writer.toString());
            } catch (TransformerException  | JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        } else if (header.contains(MediaType.APPLICATION_XML_VALUE) ||
                header.contains(MediaType.APPLICATION_JSON_VALUE) ||
                header.contains(MediaType.ALL_VALUE))
            return ResponseEntity.ok(participants);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id,
                                     @RequestHeader("Accept") String header) {
        Optional<Participant> participantEntity = participantService.getById(id);
        if (participantEntity.isPresent()) {
            var participant = ParticipantDTO.entityToDTO(participantEntity.get());

            if (header.contains(MediaType.TEXT_HTML_VALUE)) {
                try {
                    Transformer transformer = TransformerFactory
                            .newInstance()
                            .newTemplates(new StreamSource("src/main/resources/xslt/participant.xslt"))
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
    public ResponseEntity<?> postCreate(@RequestBody ParticipantCreationDTO dto) {
        participantService.create(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            participantService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
