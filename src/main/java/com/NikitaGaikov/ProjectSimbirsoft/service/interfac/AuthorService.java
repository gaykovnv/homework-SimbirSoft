package com.NikitaGaikov.ProjectSimbirsoft.service.interfac;

import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Author;
import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Book;
import com.NikitaGaikov.ProjectSimbirsoft.dto.AddBookDto;
import com.NikitaGaikov.ProjectSimbirsoft.dto.BookDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorService {

    Iterable<Author> findAll();

    List<Book> findAllBookOfAuthor(AddBookDto addBookDto);

    Author add(AddBookDto addBookDto);

    ResponseEntity<String> delete(String id);
}
