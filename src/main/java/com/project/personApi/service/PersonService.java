package com.project.personApi.service;

import com.project.personApi.dto.MessageResponseDTO;
import com.project.personApi.entity.Person;
import com.project.personApi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person){
        Person savedPerson = personRepository.save(person);
        MessageResponseDTO response = MessageResponseDTO.builder()
                .message("Created person with id = " + savedPerson.getId())
                .build();
        return response;
    }
}
