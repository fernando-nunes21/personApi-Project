package com.project.personApi.controller;

import com.project.personApi.dto.MessageResponseDTO;
import com.project.personApi.entity.Person;
import com.project.personApi.repository.PersonRepository;
import com.project.personApi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping
    public String getPerson(){
        return "TESTANDO API";
    }

    @PostMapping
    ResponseEntity<MessageResponseDTO> createPerson(@RequestBody  Person person){
        return new ResponseEntity<MessageResponseDTO>(personService.createPerson(person), HttpStatus.CREATED);
    }

}
