package com.example.VMS.repository;

import com.example.VMS.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    boolean existsByEmail(String email);

 }

