package com.example.VMS.service;

import com.example.VMS.model.Visitor;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VisitorService {
    String createNewVisitor(Visitor visitor);

    ResponseEntity<List<Visitor>> getAllVisitors();

    ResponseEntity<Visitor> getVisitorById(long visitorId);

    String updateVisitorById(long visitorId, Visitor visitor);

    String deleteVisitorById(long visitorId);

    String deleteAllVisitors();
}
