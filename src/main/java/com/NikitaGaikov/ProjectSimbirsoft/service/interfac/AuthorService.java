package com.NikitaGaikov.ProjectSimbirsoft.service.interfac;

import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Author;
import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Book;
import com.NikitaGaikov.ProjectSimbirsoft.dto.AddBookDto;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    List<Book> findAllBookOfAuthor(AddBookDto addBookDto);

    Author add(AddBookDto addBookDto);

    boolean delete(String id);
}
