package com.NikitaGaikov.ProjectSimbirsoft.сontroller;

import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Person;
import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.PersonWithTimeZoned;
import com.NikitaGaikov.ProjectSimbirsoft.dto.AddBookDto;
import com.NikitaGaikov.ProjectSimbirsoft.dto.BookDto;
import com.NikitaGaikov.ProjectSimbirsoft.dto.PersonDto;
import com.NikitaGaikov.ProjectSimbirsoft.dto.PersonWithBookDto;
import com.NikitaGaikov.ProjectSimbirsoft.service.implemention.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/persons")
public class PersonRestController {

    @Autowired
    private PersonServiceImpl service;

    @PostMapping("/backBook")
    public ResponseEntity<String> backBook(@RequestBody AddBookDto addBookDto){
        service.backBook(addBookDto);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/deleteByFIO")
    public ResponseEntity<String> deleteByFIO(@RequestBody PersonDto personDto){
        return service.deleteByFIO(personDto);
    }

    @PostMapping("/add")
    public PersonWithTimeZoned add(@RequestBody PersonWithTimeZoned person){
        return service.add(person);
    }

    @GetMapping("/tookBooksPerson/{id}")
    public List<BookDto> listBook(@PathVariable String id){
        return service.listTookBooksThePersonById(id);
    }

    @PostMapping
    public ResponseEntity<String> addBookInListTookBook(@RequestBody AddBookDto addBookDto){
        return service.addBookTookThePerson(addBookDto);
    }

    @PutMapping("{id}")
    public PersonDto update(@PathVariable String id , @RequestBody PersonDto person){
        person.setId(Integer.parseInt(id));
        return service.update(person);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id){
        service.deleteById(id);
        return ResponseEntity.ok("Ok");
    }
}
