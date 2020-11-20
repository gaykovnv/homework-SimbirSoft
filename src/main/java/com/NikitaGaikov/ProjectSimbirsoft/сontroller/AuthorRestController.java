package com.NikitaGaikov.ProjectSimbirsoft.сontroller;

import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Author;
import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Book;
import com.NikitaGaikov.ProjectSimbirsoft.dto.AddBookDto;
import com.NikitaGaikov.ProjectSimbirsoft.service.implemention.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorRestController {

    @Autowired
    private AuthorServiceImpl service;

    @GetMapping("/list")
    public List<Author> list(){
        return service.findAll();
    }
    @PostMapping("/listBooksOfAuthor")
    public List<Book> listBookOfAuthor(@RequestBody AddBookDto addBookDto){
        return service.findAllBookOfAuthor(addBookDto);
    }

    @PostMapping("/add")
    public Author add(@RequestBody AddBookDto addBookDto){
        return service.add(addBookDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id){
        return service.delete(id);
    }
}
