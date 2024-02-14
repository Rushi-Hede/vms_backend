package com.example.VMS.controller;

import com.example.VMS.model.Visitor;
import com.example.VMS.service.VisitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/visitors")
public class VisitorController {

    private static final Logger logger = LoggerFactory.getLogger(VisitorController.class);

    @Autowired
    private VisitorService visitorService;

    @PostMapping
    public ResponseEntity<String> createNewVisitor(@RequestBody Visitor visitor) {
        logger.info("Received request to create a new visitor: {}", visitor);
        String result = visitorService.createNewVisitor(visitor);
        logger.info("creating a new visitor: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Visitor>> getAllVisitors() {
        logger.info("Received request to retrieve all visitors.");
        ResponseEntity<List<Visitor>> responseEntity = visitorService.getAllVisitors();
        logger.info("Returning all visitors: {}", responseEntity.getBody());
        return responseEntity;
    }

    @GetMapping("/{visitorId}")
    public ResponseEntity<Visitor> getVisitorById(@PathVariable long visitorId) {
        logger.info("Received request to retrieve visitor by ID: {}", visitorId);
        ResponseEntity<Visitor> responseEntity = visitorService.getVisitorById(visitorId);
        logger.info("Returning visitor: {}", responseEntity.getBody());
        return responseEntity;
    }

    @PutMapping("/{visitorId}")
    public ResponseEntity<String> updateVisitorById(@PathVariable long visitorId, @Valid @RequestBody Visitor visitor) {
        logger.info("Received request to update visitor by ID: {}, new details: {}", visitorId, visitor);
        String result = visitorService.updateVisitorById(visitorId, visitor);
        logger.info("updating visitor: {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{visitorId}")
    public ResponseEntity<String> deleteVisitorById(@PathVariable long visitorId) {
        logger.info("Received request to delete visitor by ID: {}", visitorId);
        String result = visitorService.deleteVisitorById(visitorId);
        logger.info("deleting visitor: {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllVisitors() {
        logger.info("Received request to delete all visitors.");
        String result = visitorService.deleteAllVisitors();
        logger.info("deleting all visitors: {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
