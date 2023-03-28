package com.example.demo.Service;


import java.io.IOException;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.OwlNode;
import com.example.demo.Repo.OwlNodeRepository;


import org.springframework.core.io.Resource;


@Service
public class OwlService {

    @Autowired
    private OwlNodeRepository owlNodeRepository;

    public void saveOwlData(Resource resource) throws IOException, OWLOntologyCreationException {
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = null;
        try {
            ontology = manager.loadOntologyFromOntologyDocument(resource.getInputStream());
        } catch (OWLOntologyCreationException e) {
            throw new OWLOntologyCreationException("Error loading ontology from file: " + e.getMessage());
        }

        for (OWLClass owlClass : ontology.getClassesInSignature()) {
            OwlNode owlNode = new OwlNode();
            owlNode.setName(owlClass.getIRI().getShortForm());
            owlNodeRepository.save(owlNode);
        }
    }
}




