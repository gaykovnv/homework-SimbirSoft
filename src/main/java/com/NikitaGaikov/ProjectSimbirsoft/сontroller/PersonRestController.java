package com.NikitaGaikov.ProjectSimbirsoft.сontroller;

import com.NikitaGaikov.ProjectSimbirsoft.dto.Person;
import com.NikitaGaikov.ProjectSimbirsoft.exception.NotFoundException;
import com.NikitaGaikov.ProjectSimbirsoft.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/persons")
public class PersonRestController {

    @Autowired
    private PersonService service;

    @GetMapping("/list")
    public List<Map<String,String>> jsonStart(){
        return service.listPerson();
    }

    @GetMapping("{id}")
    public Map<String,String> getOne(@PathVariable String id){
        return service.getPersonById(id);
    }

    @GetMapping
    public Map<String,String> getPersonByName(@RequestParam(value = "fname") String fname){
        return service.getPersonByFName(fname);
    }

    @PostMapping
    public Map<String,String> create(@RequestBody Map<String,String> person){
        return service.save(person);
    }

    @PutMapping("{id}")
    public Map<String,String> update(@PathVariable String id , @RequestBody Map<String,String> person){
        return service.updatePerson(id,person);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id){
        return service.deletePersonById(id);
    }
}
