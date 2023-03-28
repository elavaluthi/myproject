package com.example.demo.Repo;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.OwlNode;

@Repository
public interface OwlNodeRepository extends Neo4jRepository<OwlNode, Long> {
}