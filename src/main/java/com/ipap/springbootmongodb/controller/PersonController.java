package com.ipap.springbootmongodb.controller;

import com.ipap.springbootmongodb.dto.PersonDto;
import com.ipap.springbootmongodb.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // Create
    @PostMapping
    public ResponseEntity<PersonDto> save(@RequestBody PersonDto personDto) {
        return ResponseEntity.ok(personService.savePerson(personDto));
    }

    // Read
    @GetMapping
    public ResponseEntity<List<PersonDto>> getPersonStartsWith(@RequestParam String name) {
        return ResponseEntity.ok(personService.findByFirstNameStartsWith(name));
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonDto> getPersonById(@PathVariable String personId) {
        return ResponseEntity.of(personService.getPersonById(personId));
    }

    @GetMapping("/age")
    public ResponseEntity<List<PersonDto>> getPersonsByAge(@RequestParam Integer min, @RequestParam Integer max) {
        return ResponseEntity.ok(personService.getPersonsByAge(min, max));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PersonDto>> getAllPersons() {
        return ResponseEntity.ok(personService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PersonDto>> searchPerson(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) String city,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5")Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(personService.search(name, minAge, maxAge, city, pageable));
    }

    // Put

    // Delete
    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> deletePersonByPersonId(@PathVariable String personId) {
        personService.deletePersonByPersonId(personId);
        return ResponseEntity.ok().build();
    }
}
