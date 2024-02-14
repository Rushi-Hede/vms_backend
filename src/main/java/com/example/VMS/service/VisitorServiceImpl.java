package com.example.VMS.service;


import com.example.VMS.Exception.VisitorNotFoundException;
import com.example.VMS.Exception.DuplicateVisitorException;
import com.example.VMS.model.Visitor;
import com.example.VMS.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    public VisitorServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository= visitorRepository;
    }


    @Override
    public String createNewVisitor(Visitor visitor) {

        if (visitorRepository.existsByEmail(visitor.getEmail())) {
            throw new DuplicateVisitorException("Visitor with the same email already exists");
        }
        visitorRepository.save(visitor);
        return "Visitor created in the database";
    }

    @Override
    public ResponseEntity<List<Visitor>> getAllVisitors() {
        List<Visitor> visitorList = new ArrayList<>();
        visitorRepository.findAll().forEach(visitorList::add);
        return new ResponseEntity<>(visitorList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Visitor> getVisitorById(long visitorId) {
        Optional<Visitor> visitor = visitorRepository.findById(visitorId);

        if (visitor.isEmpty()) {
            throw new VisitorNotFoundException("Requested visitor is not found");
        }
        return new ResponseEntity<>(visitor.get(), HttpStatus.OK);
    }

    @Override
    public String updateVisitorById(long visitorId, Visitor visitor) {
        Optional<Visitor> existingVisitor = visitorRepository.findById(visitorId);
        if (existingVisitor.isPresent()) {
            Visitor existVisitor = existingVisitor.get();
            existVisitor.setName(visitor.getName());
            existVisitor.setEmail(visitor.getEmail());
            existVisitor.setGender(visitor.getGender());
            existVisitor.setOccupation(visitor.getOccupation());
            visitorRepository.save(existVisitor);
            return "Visitor details against Id " + visitorId + " updated";
        } else {
            return "Visitor details do not exist for visitorId " + visitorId;
        }
    }

    @Override
    public String deleteVisitorById(long visitorId) {
        visitorRepository.deleteById(visitorId);
        return "Visitor deleted successfully";
    }

    @Override
    public String deleteAllVisitors() {
        visitorRepository.deleteAll();
        return "All visitors are deleted";
    }
}
