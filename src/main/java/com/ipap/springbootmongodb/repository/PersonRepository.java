package com.ipap.springbootmongodb.repository;

import com.ipap.springbootmongodb.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends MongoRepository<Person, String> {

    Optional<Person> findByPersonId(String personId);

    List<Person> findByFirstNameStartsWith(String name);

    // field addresses is excluded from the result
    @Query(value = "{ 'age': { '$gte' : ?0, '$lte': ?1 } }", fields = "{ 'addresses': 0 }")
    List<Person> findByAgeBetween(Integer min, Integer max);
}
