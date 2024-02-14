package com.example.VMS.service;

import com.example.VMS.Exception.DuplicateVisitorException;
import com.example.VMS.Exception.VisitorNotFoundException;
import com.example.VMS.model.Visitor;
import com.example.VMS.repository.VisitorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VisitorServiceImplTest {


    @Mock
    private VisitorRepository visitorRepository;
    private VisitorService visitorService;
    AutoCloseable autoCloseable;
    Visitor visitor;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        visitorService = new VisitorServiceImpl(visitorRepository);
        visitor = new Visitor(1L, "Sarthak More", "SarthakM@example.com", "Male", "Engineer");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void createNewVisitor() {
        mock(Visitor.class);
        mock(VisitorRepository.class);

        when(visitorRepository.save(visitor)).thenReturn(visitor);
        assertThat(visitorService.createNewVisitor(visitor)).isEqualTo("Visitor created in the database");

    }

    @Test
    void testCreateNewVisitorDuplicateVisitor() {
        // Arrange
        when(visitorRepository.existsByEmail(visitor.getEmail())).thenReturn(true);

        // Act and Assert
        try {
            visitorService.createNewVisitor(visitor);
            fail("Expected DuplicateVisitorException, but no exception was thrown.");
        } catch (DuplicateVisitorException exception) {
            assertThat(exception.getMessage()).isEqualTo("Visitor with the same email already exists");
        }
    }

    @Test
    void testGetAllVisitors() {
        mock(Visitor.class);
        mock(VisitorRepository.class);

        when(visitorRepository.findAll()).thenReturn(new ArrayList<Visitor>(Collections.singleton(visitor)));

        assertThat(visitorService.getAllVisitors().getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(visitorService.getAllVisitors().getBody()).containsExactly(visitor);

    }

    @Test
    void testGetVisitorByIdFound() {

        mock(Visitor.class);
        mock(VisitorRepository.class);

        // Case 1: If Found
        when(visitorRepository.findById(1l)).thenReturn(Optional.of(visitor));
        Visitor result = visitorService.getVisitorById(1l).getBody();
        assertThat(result).isEqualTo(visitor);
    }

    @Test
    void testGetVisitorByIdNotFound() {
        // Case 2: Employee not found
        mock(Visitor.class);
        mock(VisitorRepository.class);

        when(visitorRepository.findById(2L)).thenReturn(Optional.empty());

        try {
            visitorService.getVisitorById(2l);
            fail("Expected EmpNotFoundException, but no exception was thrown.");
        } catch (VisitorNotFoundException exception) {
            assertThat(exception.getMessage()).isEqualTo("Requested visitor is not found");
        }


    }

    @Test
    void updateVisitorById() {

        mock(Visitor.class);
        mock(VisitorRepository.class);

        when(visitorRepository.findById(1L)).thenReturn(Optional.ofNullable(visitor));

        String result = visitorService.updateVisitorById(1L, visitor);

        assertThat(result).isEqualTo("Visitor details against Id 1 updated");

        verify(visitorRepository).save(visitor);
    }


    @Test
    void testDeleteVisitorById() {
        mock(Visitor.class);
        mock(VisitorRepository.class);
        // Mocking the behavior of employeeRepository
        when(visitorRepository.existsById(1L)).thenReturn(true);

        // Calling the method under test
        String result = visitorService.deleteVisitorById(1l);

        // Asserting the result
        assertEquals("Visitor deleted successfully", result);

        // Verifying that deleteById method was called with the correct argument
        verify(visitorRepository).deleteById(1L);
    }


    @Test
    void deleteAllVisitors() {

        mock(Visitor.class);
        mock(VisitorRepository.class);
        doNothing().when(visitorRepository).deleteAll();

        String result = visitorService.deleteAllVisitors();

        assertEquals("All visitors are deleted", result);

        verify(visitorRepository).deleteAll();
    }
}
