package com.NikitaGaikov.ProjectSimbirsoft.сontroller;

import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Author;
import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Book;
import com.NikitaGaikov.ProjectSimbirsoft.dto.AddBookDto;
import com.NikitaGaikov.ProjectSimbirsoft.dto.BookDto;
import com.NikitaGaikov.ProjectSimbirsoft.service.implemention.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        if(!service.deleteById(id)){
            return ResponseEntity.ok("mistake");
        }
        return ResponseEntity.ok("ok");
    }

    @PutMapping("{id}")
    public AddBookDto update(@PathVariable String id, @RequestBody AddBookDto addBookDto){
        return service.update(id,addBookDto);
    }

    @GetMapping("/listBookByGenre")
    public List<Book> listBookByGenre(@RequestBody AddBookDto genreName){
        return service.getListBookByGenre(genreName);
    }

    @PostMapping("{id}")
    public Optional<Author> listBookByAuthor(@PathVariable String id){
        return service.getListBookByAuthor(id);
    }

}
