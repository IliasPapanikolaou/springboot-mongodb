package com.ipap.springbootmongodb;

import com.ipap.springbootmongodb.entity.Address;
import com.ipap.springbootmongodb.entity.Person;
import com.ipap.springbootmongodb.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootApplication
@Slf4j
public class SpringbootMongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMongodbApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(PersonRepository personRepository, MongoTemplate mongoTemplate) {
        return args -> {
            String email = "maria@mail.com";
            Person person = Person.builder()
                    .firstName("Maria")
                    .lastName("Spendings")
                    .email(email)
                    .age(32)
                    .hobbies(List.of("Shopping", "Buying stuff"))
                    .addresses(
                            List.of(Address.builder().address("Ermou 52").city("Athens").postalCode("10555").build())
                    )
                    .build();
            // Query with MongoTemplate and create unique email identifier (First Option)
            Query query = new Query();
            query.addCriteria(Criteria.where("email").is(email));
            List<Person> people = mongoTemplate.find(query, Person.class);
            if (people.size() > 1) {
                throw new IllegalStateException("Found many students with email: " + email);
            }
            if (people.isEmpty()) {
                personRepository.insert(person);
                log.info("Inserted person: {}", person);
            } else {
                log.info("Student with email: {} already exists", email);
            }

            // Second option:
//            personRepository.findPersonByEmail(email).ifPresentOrElse(
//                    s -> log.info("Student with email: {} already exists", email),
//                    () -> {
//                        personRepository.insert(person);
//                        log.info("Inserted person: {}", person);
//                    }
//            );
        };
    }

}
