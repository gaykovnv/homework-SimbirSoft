package com.NikitaGaikov.ProjectSimbirsoft.restController;

import com.NikitaGaikov.ProjectSimbirsoft.dto.Book;
import com.NikitaGaikov.ProjectSimbirsoft.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookRestController {

    private int counter = 4;
    private Book bookOne = new Book(1,"Мастер и Маргарита","M.Булгаков","классика");
    private Book bookTwo = new Book(2,"Преступление и наказание","Ф.Достоевский","классика");
    private Book bookThird = new Book(3,"отцы и дети","Е.Тургеев","классика");

    private List<Map<String,String>> bookList = new ArrayList<Map<String, String>>(){{
        add(new HashMap<String,String>(){{
            put("id",String.valueOf(bookOne.getId()));
            put("name",bookOne.getName());
            put("author",bookOne.getAuthor());
            put("genre",bookOne.getGenre());
        }});
        add(new HashMap<String,String>(){{
            put("id",String.valueOf(bookTwo.getId()));
            put("name",bookTwo.getName());
            put("author",bookTwo.getAuthor());
            put("genre",bookTwo.getGenre());
        }});
        add(new HashMap<String,String>(){{
            put("id",String.valueOf(bookThird.getId()));
            put("name",bookThird.getName());
            put("author",bookThird.getAuthor());
            put("genre",bookThird.getGenre());
        }});
    }};

    @GetMapping("list")
    public List<Map<String,String>> listBook(){
        return bookList;
    }

    @GetMapping("{id}")
    public Map<String,String> getOne(@PathVariable String id){
        return getBookById(id);
    }

    private Map<String,String> getBookById(@PathVariable String id){
        return bookList.stream()
                .filter(book -> book.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @GetMapping
    public Map<String,String> getBookByAuthor(@RequestParam(value = "author") String author){
        return bookList.stream()
                .filter(book -> book.get("author").equals(author))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String,String> createBook(@RequestBody Map<String,String> newBook){
        newBook.put("id",String.valueOf(counter++));

        bookList.add(newBook);
        return newBook;
    }

    @PutMapping("{id}")
    public Map<String,String> update(@PathVariable String id, @RequestBody Map<String,String> newBook){
        Map<String,String> bookFromDb = getBookById(id);

        bookFromDb.putAll(newBook);
        bookFromDb.put("id",id);
        return bookFromDb;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id){
        Map<String,String> book = getBookById(id);
        bookList.remove(book);
        return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.OK).body("Ok");
    }
}
