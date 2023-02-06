package com.ipap.springbootmongodb.service;

import com.ipap.springbootmongodb.dto.PersonDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    PersonDto savePerson(PersonDto personDto);

    List<PersonDto> findByFirstNameStartsWith(String name);

    List<PersonDto> getAll();

    Optional<PersonDto> getPersonById(String personId);

    void deletePersonByPersonId(String personId);

    List<PersonDto> getPersonsByAge(Integer min, Integer max);

    Page<PersonDto> search(String name, Integer minAge, Integer maxAge, String city, Pageable pageable);
}
