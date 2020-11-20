package com.NikitaGaikov.ProjectSimbirsoft.сontroller;

import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Book;
import com.NikitaGaikov.ProjectSimbirsoft.dto.AddBookDto;
import com.NikitaGaikov.ProjectSimbirsoft.dto.BookDto;
import com.NikitaGaikov.ProjectSimbirsoft.service.implemention.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookRestController {

    @Autowired
    private BookServiceImpl service;

    @PostMapping
    public Book createBook(@RequestBody AddBookDto addBookDto){
        return service.add(addBookDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id){
        return service.deleteById(id);
    }

    @PutMapping("{id}")
    public AddBookDto update(@PathVariable String id, @RequestBody AddBookDto addBookDto){
        return service.update(id,addBookDto);
    }

    @PostMapping("/listBookByGenre")
    public List<Book> listBookByGenre(@RequestBody AddBookDto genreName){
        return service.getListBookByGenre(genreName);
    }

    @PostMapping("/listBookByAuthor")
    public List<String> listBookByAuthor(@RequestBody AddBookDto addBookDto){
        return service.getListBookByAuthor(addBookDto);
    }
/*
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id){
        return service.deleteBookById(id);
    }*/
}
