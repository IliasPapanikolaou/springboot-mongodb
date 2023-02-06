package com.ipap.springbootmongodb.service;

import com.ipap.springbootmongodb.dto.PersonDto;
import com.ipap.springbootmongodb.entity.Person;
import com.ipap.springbootmongodb.repository.PersonRepository;
import com.ipap.springbootmongodb.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final MongoTemplate mongoTemplate;

    public PersonServiceImpl(PersonRepository personRepository, MongoTemplate mongoTemplate) {
        this.personRepository = personRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public PersonDto savePerson(PersonDto personDto) {
        Person person = Mapper.toEntity(personDto);
        return Mapper.toDto(personRepository.save(person));
    }

    @Override
    public List<PersonDto> findByFirstNameStartsWith(String name) {
        return personRepository.findByFirstNameStartsWith(name).stream()
                .map(Mapper::toDto)
                .toList();
    }

    @Override
    public List<PersonDto> getAll() {
        return personRepository.findAll().stream()
                .map(Mapper::toDto)
                .toList();
    }

    @Override
    public Optional<PersonDto> getPersonById(String personId) {
        return personRepository.findByPersonId(personId).map(Mapper::toDto);
    }

    @Override
    public void deletePersonByPersonId(String personId) {
        personRepository.deleteById(personId);
    }

    @Override
    public List<PersonDto> getPersonsByAge(Integer min, Integer max) {
        return personRepository.findByAgeBetween(min, max).stream()
                .map(Mapper::toDto)
                .toList();
    }

    @Override
    public Page<PersonDto> search(String name, Integer minAge, Integer maxAge, String city, Pageable pageable) {

        Query query = new Query().with(pageable);
        List<Criteria> criteria = new ArrayList<>();

        // Name criteria
        if (name != null && !name.isEmpty()) {
            criteria.add(Criteria.where("firstName").regex(name, "i")); // case insensitivity
        }

        // Age criteria
        if (minAge != null && maxAge != null) {
            criteria.add(Criteria.where("age").gte(minAge).lte(maxAge));
        }

        // City criteria
        if (city != null && !city.isEmpty()) {
            criteria.add(Criteria.where("addresses.city").is(city));
        }

        // Criteria
        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
        }

        // Return Page<PersonDto>
        return PageableExecutionUtils.getPage(
                mongoTemplate.find(query, Person.class),
                pageable,
                () -> mongoTemplate.count(query.skip(0).limit(0), Person.class))
                .map(Mapper::toDto);
    }
}
