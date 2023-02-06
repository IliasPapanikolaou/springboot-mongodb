package com.ipap.springbootmongodb.dto;

import com.ipap.springbootmongodb.entity.Address;
import lombok.Builder;

import java.util.List;

@Builder
public record PersonDto(
        String personId,
        String firstName,
        String lastName,
        Integer age,
        List<String>hobbies,
        List<Address> addresses
) {
}
