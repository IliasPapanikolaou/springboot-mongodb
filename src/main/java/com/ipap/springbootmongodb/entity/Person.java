package com.ipap.springbootmongodb.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@Builder
@ToString
@Document(collection = "person")
// Include (Serialize) only NON_NULL fields
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {
    @Id
    @MongoId
    private String personId;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private Integer age;
    private List<String> hobbies;
    private List<Address> addresses;
}
