package com.NikitaGaikov.ProjectSimbirsoft.service;

import com.NikitaGaikov.ProjectSimbirsoft.dto.Person;
import com.NikitaGaikov.ProjectSimbirsoft.exception.NotFoundException;
import com.sun.org.apache.xerces.internal.impl.validation.EntityState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class PersonService {

    private int counter = 4;

    private Person personOne =
            new Person(1,"Максим","Павлов","Олегович", LocalDate.parse("2001-12-15"));
    private Person personTwo =
            new Person(2,"Олег","Михайлович","Денисович", LocalDate.parse("2000-01-05"));
    private Person personThird =
            new Person(3,"Павел","Павлов","Павлович", LocalDate.parse("1999-04-09"));

    private List<Map<String,String>> personList = new ArrayList<Map<String, String>>(){{
        add( new HashMap<String, String>() {{
            put("id",String.valueOf(personOne.getId()));
            put("fname", personOne.getFname());
            put("lname", personOne.getLname());
            put("patronymic", personOne.getPatronymic());
            put("birthday", personOne.getBirthday().format(DateTimeFormatter.ofPattern("dd-MM-yyyy", new Locale("ru"))));
        }});
        add( new HashMap<String, String>() {{
            put("id",String.valueOf(personTwo.getId()));
            put("fname", personTwo.getFname());
            put("lname", personTwo.getLname());
            put("patronymic", personTwo.getPatronymic());
            put("birthday", personTwo.getBirthday().format(DateTimeFormatter.ofPattern("dd-MM-yyyy",new Locale("ru"))));
        }});
        add( new HashMap<String, String>() {{
            put("id",String.valueOf(personThird.getId()));
            put("fname", personThird.getFname());
            put("lname", personThird.getLname());
            put("patronymic", personThird.getPatronymic());
            put("birthday", personThird.getBirthday().format(DateTimeFormatter.ofPattern("dd-MM-yyyy",new Locale("ru"))));
        }});
    }};

    public List<Map<String,String>> listPerson(){
        return personList;
    }

    public Map<String,String> getPersonById(String id){
        return personList.stream()
                .filter(person -> person.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public Map<String,String> getPersonByFName(String fname){
        return personList.stream()
                .filter(person -> person.get("fname").equals(fname))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public Map<String,String> save(Map<String,String> person){
        person.put("id",String.valueOf(counter++));

        personList.add(person);
        return person;
    }

    public Map<String,String> updatePerson(String id, Map<String,String> person){
        Map<String,String> personFromDb = getPersonById(id);

        personFromDb.putAll(person);
        personFromDb.put("id",id);
        return personFromDb;
    }

    public ResponseEntity<String> deletePersonById(String id){
        Map<String,String> person = getPersonById(id);
        personList.remove(person);
        return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.OK).body("Ok");
    }
}
