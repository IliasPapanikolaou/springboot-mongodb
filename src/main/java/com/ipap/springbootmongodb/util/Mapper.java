package com.ipap.springbootmongodb.util;

import com.ipap.springbootmongodb.dto.PersonDto;
import com.ipap.springbootmongodb.entity.Person;

public class Mapper {

    public static PersonDto toDto(Person person) {
        return PersonDto.builder()
                .personId(person.getPersonId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .age(person.getAge())
                .hobbies(person.getHobbies())
                .addresses(person.getAddresses())
                .build();
    }
    
    public static Person toEntity(PersonDto personDto) {
        return Person.builder()
                .personId(personDto.personId())
                .firstName(personDto.firstName())
                .lastName(personDto.lastName())
                .age(personDto.age())
                .hobbies(personDto.hobbies())
                .addresses(personDto.addresses())
                .build();
    }
}
