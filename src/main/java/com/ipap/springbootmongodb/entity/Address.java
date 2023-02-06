package com.ipap.springbootmongodb.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

    private String address;
    private String city;
    private String postalCode;
}
