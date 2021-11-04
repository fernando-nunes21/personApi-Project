package com.project.personApi.service;

import com.project.personApi.dto.request.PersonDTO;
import com.project.personApi.dto.response.MessageResponseDTO;
import com.project.personApi.entity.Person;
import com.project.personApi.mapper.PersonMapper;
import com.project.personApi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person convertedPerson = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(convertedPerson);
        MessageResponseDTO response = MessageResponseDTO.builder()
                .message("Created person with id = " + savedPerson.getId())
                .build();
        return response;
    }
}
