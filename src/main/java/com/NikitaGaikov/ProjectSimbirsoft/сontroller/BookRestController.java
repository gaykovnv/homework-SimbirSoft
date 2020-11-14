package com.NikitaGaikov.ProjectSimbirsoft.сontroller;

import com.NikitaGaikov.ProjectSimbirsoft.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookRestController {

    @Autowired
    private BookService service;

    @GetMapping("list")
    public List<Map<String,String>> listBook(){
        return service.bookList();
    }

    @GetMapping("{id}")
    public Map<String,String> getOne(@PathVariable String id){
        return service.getBookById(id);
    }

    @GetMapping
    public Map<String,String> getBookByAuthor(@RequestParam(value = "author") String author){
        return service.getBookByAuthor(author);
    }

    @PostMapping
    public Map<String,String> createBook(@RequestBody Map<String,String> newBook){
        return service.save(newBook);
    }

    @PutMapping("{id}")
    public Map<String,String> update(@PathVariable String id, @RequestBody Map<String,String> newBook){
        return service.updateBook(id,newBook);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id){
        return service.deleteBookById(id);
    }
}
