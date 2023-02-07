package com.ipap.springbootmongodb.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "photograph")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Photograph {

    @Id
    @MongoId
    private String id;
    private String title;
    private Binary photograph;
}
