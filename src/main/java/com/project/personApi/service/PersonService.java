package com.project.personApi.service;

import com.project.personApi.dto.request.PersonDTO;
import com.project.personApi.dto.response.MessageResponseDTO;
import com.project.personApi.entity.Person;
import com.project.personApi.exceptions.PersonNotFound;
import com.project.personApi.mapper.PersonMapper;
import com.project.personApi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return buildMessageResponseDTO(savedPerson.getId(), "Created person with id = ");
    }

    public List<PersonDTO> getAll(){
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO getPersonById(Integer id) throws PersonNotFound{
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);

    }

    public MessageResponseDTO updatePersonById(Integer id, PersonDTO personDTO) throws PersonNotFound{
        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);
        personRepository.save(personToUpdate);
        return buildMessageResponseDTO(id, "Updated person with id = ");

    }

    public MessageResponseDTO deletePersonById(Integer id) throws PersonNotFound{
        verifyIfExists(id);
        personRepository.deleteById(id);
        return buildMessageResponseDTO(id, "Delete person with id = ");
    }

    private MessageResponseDTO buildMessageResponseDTO(Integer id, String message){
        return MessageResponseDTO.builder()
                .message(message + id)
                .build();
    }

    private Person verifyIfExists(Integer id) throws PersonNotFound {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFound("Person not found by id = "+id));
    }
}
