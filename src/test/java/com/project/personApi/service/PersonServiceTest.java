package com.project.personApi.service;

import com.project.personApi.dto.request.PersonDTO;
import com.project.personApi.dto.response.MessageResponseDTO;
import com.project.personApi.entity.Person;
import com.project.personApi.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.project.personApi.utils.PersonUtils.createFakeDTO;
import static com.project.personApi.utils.PersonUtils.createFakeEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDTO expectedSuccessMessage = buildMessageResponseDTO(
                expectedSavedPerson.getId(), "Created person with id = ");
        MessageResponseDTO successMessage = personService.createPerson(personDTO);

        Assertions.assertEquals(expectedSuccessMessage, successMessage);
    }

    private MessageResponseDTO buildMessageResponseDTO(Integer id, String message) {
        return MessageResponseDTO.builder()
                .message(message + id)
                .build();
    }
}

