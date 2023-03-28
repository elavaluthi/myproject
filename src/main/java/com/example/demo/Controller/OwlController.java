package com.example.demo.Controller;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.OwlService;

import java.io.IOException;
import java.util.Objects;

@Controller
public class OwlController {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private OwlService owlService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadOwlFile(@RequestParam("file") MultipartFile file) {
        if (Objects.requireNonNull(file.getOriginalFilename()).endsWith(".owl")) {
            try {
                Resource resource = resourceLoader.getResource("file:" + file.getResource().getFile().getAbsolutePath());
                owlService.saveOwlData(resource);
                return ResponseEntity.ok("Ontology uploaded successfully");
            } catch (IOException | OWLOntologyCreationException e) {
                return ResponseEntity.badRequest().body("Error uploading ontology: " + e.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("Invalid file type. Please upload an OWL file");
        }
    }
}
