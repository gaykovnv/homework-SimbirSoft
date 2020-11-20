package com.NikitaGaikov.ProjectSimbirsoft.service.implemention;

import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Author;
import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Book;
import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.BookWithTimeZoned;
import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Genre;
import com.NikitaGaikov.ProjectSimbirsoft.dao.repository.AuthorRepository;
import com.NikitaGaikov.ProjectSimbirsoft.dao.repository.BookRepository;
import com.NikitaGaikov.ProjectSimbirsoft.dao.repository.GenreRepository;
import com.NikitaGaikov.ProjectSimbirsoft.dto.AddBookDto;
import com.NikitaGaikov.ProjectSimbirsoft.service.connectToDB.DBWork;
import com.NikitaGaikov.ProjectSimbirsoft.service.interfac.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private GenreRepository genreRepo;

    @Override
    public Book add(AddBookDto addBookDto) {
        BookWithTimeZoned book = new BookWithTimeZoned();
        book.setName(addBookDto.getName());
        book.setDate(ZonedDateTime.now());
        Author author = new Author();
        author.setFname(addBookDto.getAuthor_fname());
        author.setLname(addBookDto.getAuthor_lname());
        author.setPatronymic(addBookDto.getAuthor_middlename());
        book.setAuthor(author);
        authorRepo.save(author);
        bookRepo.save(book);
        return book;
    }

    @Override
    public ResponseEntity<String> deleteById(String id) {
        Optional<Book> book = bookRepo.findById(Long.valueOf(id));

        if(book.get().getPersons().size() == 0) {
            bookRepo.deleteById(Long.valueOf(id));
            return ResponseEntity.ok("OK");
        }else {
            return ResponseEntity.ok("Mistake");
        }
    }



    @Override
    public AddBookDto update(String id,AddBookDto addBookDto) {
        Optional<Book> book = bookRepo.findById(Long.valueOf(id));

        Set<Genre> genres = new HashSet<>();
        Genre genre = new Genre();
        genre.setGenre(addBookDto.getGenre_name());
        genres.add(genre);
        book.get().setGenres(genres);
        genreRepo.save(genre);
        bookRepo.save(book.get());
        addBookDto.setName(book.get().getName());
        addBookDto.setAuthor_fname(book.get().getAuthor().getFname());
        addBookDto.setAuthor_lname(book.get().getAuthor().getLname());
        addBookDto.setAuthor_middlename(book.get().getAuthor().getPatronymic());
        return addBookDto;
    }

    @Override
    public List<String> getListBookByAuthor(AddBookDto addBookDto) {
        DBWork dbWork = new DBWork();
        List<String> books = new ArrayList<>();
        String query = "select name from book where author_id = " +
                "(select id from author where first_name = ? " +
                "and last_name = ? and middle_name = ?);";
        try {
            PreparedStatement preparedStatement = dbWork.getConn().prepareStatement(query);
            preparedStatement.setString(1,addBookDto.getAuthor_fname());
            preparedStatement.setString(2,addBookDto.getAuthor_lname());
            preparedStatement.setString(3,addBookDto.getAuthor_middlename());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                books.add(resultSet.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> getListBookByGenre(AddBookDto genre) {
        DBWork dbWork = new DBWork();
        List<Book> books = new ArrayList<>();
        String query = " select b.name, g.genre, a.first_name, a.last_name,a.middle_name" +
                " from genre g inner join book b inner join book_genre bg on " +
                "bg.book_id = b.id and bg.genre_id = g.id inner join author a " +
                "where g.genre = ?";
        try {
            PreparedStatement preparedStatement = dbWork.getConn().prepareStatement(query);
            preparedStatement.setString(1,genre.getGenre_name());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Book book = new Book();
                book.setName(resultSet.getString("name"));
                Author author = new Author();
                author.setFname(resultSet.getString("first_name"));
                author.setLname(resultSet.getString("last_name"));
                author.setPatronymic(resultSet.getString("middle_name"));
                book.setAuthor(author);
                Genre genre1 = new Genre();
                genre1.setGenre(resultSet.getString("genre"));
                Set<Genre> genreSet = new HashSet<>();
                genreSet.add(genre1);
                book.setGenres(genreSet);
                books.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return books;
    }
}
