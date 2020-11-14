package com.NikitaGaikov.ProjectSimbirsoft.сontroller;

import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/takeBook")
public class PersonWithBooksRestController {

    private int counter =1;
    private List<Map<String,String>> personWithBooksList = new ArrayList<Map<String, String>>(){{
    }};

    @GetMapping
    public List<Map<String,String>> list(){
        return personWithBooksList;
    }

    @PostMapping
    public List<Map<String,String>> takeBooksList(@RequestBody Map<String,String> tookBook){
        tookBook.put("id",String.valueOf(counter++));

        ZonedDateTime date = ZonedDateTime.now();
        tookBook.put("date",date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSXXX")));
        personWithBooksList.add(tookBook);

        return personWithBooksList;
    }
}
