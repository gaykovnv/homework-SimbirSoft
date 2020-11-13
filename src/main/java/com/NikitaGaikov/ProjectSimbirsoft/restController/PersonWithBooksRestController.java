package com.NikitaGaikov.ProjectSimbirsoft.restController;

import com.NikitaGaikov.ProjectSimbirsoft.dto.Book;
import com.NikitaGaikov.ProjectSimbirsoft.dto.Person;
import com.NikitaGaikov.ProjectSimbirsoft.dto.PersonWithBooks;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/takeBook")
public class PersonWithBooksRestController {


    private int counter =2;
    private Date date = new Date();
    private Person personOne =
            new Person(1,"Максим","Павлов","Олегович", LocalDate.parse("2000-01-05"));
    private Book bookOne = new Book(1,"Мастер и Маргарита","M.Булгаков","классика");
    private Book bookTwo = new Book(2,"Молодая гвардия","M.Булгаков","классика");
    private List<Book> books = new ArrayList<Book>(){{
        add(bookOne);
        add(bookTwo);
    }};

    private PersonWithBooks personWithBooks =
            new PersonWithBooks(1,personOne,books,date);
    private List<Map<String,String>> personWithBooksList = new ArrayList<Map<String, String>>(){{
        add(new HashMap<String,String>(){{
            put("id",String.valueOf(personWithBooks.getId()));
            put("person",personWithBooks.getPerson().toString());
            put("books",personWithBooks.toString());
            put("date",String.valueOf(personWithBooks.getDate()));
        }});
    }};

    @GetMapping
    public List<Map<String,String>> list(){
        return personWithBooksList;
    }
    @PostMapping
    public List<Map<String,String>> takeBooksList(@RequestBody Map<String,String> tookBook){
        tookBook.put("id",String.valueOf(counter++));
        Date myDate = new Date();
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.setTime(myDate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSXXX");
        tookBook.put("date",simpleDateFormat.format(myCalendar.getTime()));
        personWithBooksList.add(tookBook);

        return personWithBooksList;
    }
}
