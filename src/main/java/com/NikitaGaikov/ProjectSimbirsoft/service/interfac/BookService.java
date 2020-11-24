package com.NikitaGaikov.ProjectSimbirsoft.service.interfac;

import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Author;
import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Book;
import com.NikitaGaikov.ProjectSimbirsoft.dto.AddBookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book add(AddBookDto addBookDto);

    boolean deleteById(String id);

    AddBookDto update(String id,AddBookDto addBookDto);

    Optional<Author> getListBookByAuthor(String id);

    List<Book> getListBookByGenre(AddBookDto genre);
}
