package com.example.demo.Entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

import org.neo4j.ogm.annotation.Property;
import org.springframework.data.neo4j.core.schema.Node;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Node
@Data
@AllArgsConstructor
@NoArgsConstructor

public class OwlNode {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "name")
    private String name;

    // getters and setters
}

