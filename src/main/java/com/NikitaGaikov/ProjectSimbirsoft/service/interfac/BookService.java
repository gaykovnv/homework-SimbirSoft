package com.NikitaGaikov.ProjectSimbirsoft.service.interfac;

import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Book;
import com.NikitaGaikov.ProjectSimbirsoft.dto.AddBookDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

    Book add(AddBookDto addBookDto);

    ResponseEntity<String> deleteById(String id);

    AddBookDto update(String id,AddBookDto addBookDto);

    List<String> getListBookByAuthor(AddBookDto addBookDto);

    List<Book> getListBookByGenre(AddBookDto genre);
}
