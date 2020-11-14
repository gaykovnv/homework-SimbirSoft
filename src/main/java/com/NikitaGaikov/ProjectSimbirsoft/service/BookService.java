package com.NikitaGaikov.ProjectSimbirsoft.service;

import com.NikitaGaikov.ProjectSimbirsoft.dto.BookDto;
import com.NikitaGaikov.ProjectSimbirsoft.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private int counter = 4;
    private BookDto bookOne = new BookDto(1,"Мастер и Маргарита","M.Булгаков","классика");
    private BookDto bookTwo = new BookDto(2,"Преступление и наказание","Ф.Достоевский","классика");
    private BookDto bookThird = new BookDto(3,"отцы и дети","Е.Тургеев","классика");

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

    public List<Map<String,String>> bookList(){
        return bookList;
    }

    public Map<String,String> getBookById(String id){
        return bookList.stream()
                .filter(book -> book.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public Map<String,String> getBookByAuthor(String author){
        return bookList.stream()
                .filter(book -> book.get("author").equals(author))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public Map<String,String> save(Map<String,String> newBook){
        newBook.put("id",String.valueOf(counter++));

        bookList.add(newBook);
        return newBook;
    }

    public Map<String,String> updateBook(String id, Map<String,String> newBook){
        Map<String,String> bookFromDb = getBookById(id);

        bookFromDb.putAll(newBook);
        bookFromDb.put("id",id);
        return bookFromDb;
    }

    public ResponseEntity<String> deleteBookById(String id){
        Map<String,String> book = getBookById(id);
        bookList.remove(book);
        return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.OK).body("Ok");
    }
}
