package com.ipap.springbootmongodb.repository;

import com.ipap.springbootmongodb.entity.Photograph;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotographRepository extends MongoRepository<Photograph, String> {
}
