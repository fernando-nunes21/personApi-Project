package com.project.personApi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.personApi.dto.request.PersonDTO;
import com.project.personApi.dto.response.MessageResponseDTO;
import com.project.personApi.entity.Person;
import com.project.personApi.exceptions.PersonNotFound;
import com.project.personApi.service.PersonService;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPersonById(@PathVariable Integer id) throws PersonNotFound {
        try {
            return new ResponseEntity<>(personService.getPersonById(id), HttpStatus.OK);
        } catch (PersonNotFound exception) {
            MessageResponseDTO response = MessageResponseDTO.builder()
                    .message("Person ID -> " + exception.getMessage() + " not found")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<MessageResponseDTO> createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return new ResponseEntity<>(personService.createPerson(personDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePersonById(@PathVariable Integer id, @RequestBody PersonDTO personDTO) {
        try {
            return new ResponseEntity<>(personService.updatePersonById(id, personDTO), HttpStatus.OK);
        } catch (PersonNotFound exception) {
            MessageResponseDTO response = MessageResponseDTO.builder()
                    .message("Person ID -> " + exception.getMessage() + " not found")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePersonById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(personService.deletePersonById(id), HttpStatus.OK);
        } catch (PersonNotFound exception) {
            MessageResponseDTO response = MessageResponseDTO.builder()
                    .message("Person ID -> " + exception.getMessage() + " not found")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
