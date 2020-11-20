package com.NikitaGaikov.ProjectSimbirsoft.service.implemention;

import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Author;
import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.AuthorWithTimeZoned;
import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Book;
import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Genre;
import com.NikitaGaikov.ProjectSimbirsoft.dao.repository.AuthorRepository;
import com.NikitaGaikov.ProjectSimbirsoft.dto.AddBookDto;
import com.NikitaGaikov.ProjectSimbirsoft.service.connectToDB.DBWork;
import com.NikitaGaikov.ProjectSimbirsoft.service.interfac.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepo;

    @Override
    public List<Author> findAll() {
        DBWork dbWork = new DBWork();
        List<Author> authors = new ArrayList<>();
        String query = "select * from author;";
        try {
            PreparedStatement preparedStatement = dbWork.getConn().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Author author = new Author();
                author.setFname(resultSet.getString("first_name"));
                author.setLname(resultSet.getString("last_name"));
                author.setPatronymic(resultSet.getString("middle_name"));
                authors.add(author);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return authors;
    }

    @Override
    public List<Book> findAllBookOfAuthor(AddBookDto addBookDto) {
        DBWork dbWork = new DBWork();
        List<Book> books = new ArrayList<>();
        String query = "select b.name, g.genre, a.first_name, a.last_name,a.middle_name " +
                "from genre g inner join book b inner join book_genre bg on " +
                "bg.book_id = b.id and bg.genre_id = g.id inner join author a " +
                "where a.first_name = ? and a.last_name = ? and a.middle_name = ?;";
        try {
            PreparedStatement preparedStatement = dbWork.getConn().prepareStatement(query);
            preparedStatement.setString(1,addBookDto.getAuthor_fname());
            preparedStatement.setString(2,addBookDto.getAuthor_lname());
            preparedStatement.setString(3,addBookDto.getAuthor_middlename());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Book book = new Book();
                book.setName(resultSet.getString("name"));
                Author author = new Author();
                author.setFname(resultSet.getString("first_name"));
                author.setLname(resultSet.getString("last_name"));
                author.setPatronymic(resultSet.getString("middle_name"));
                book.setAuthor(author);
                Genre genre = new Genre();
                genre.setGenre(resultSet.getString("genre"));
                Set<Genre> genreSet = new HashSet<>();
                genreSet.add(genre);
                book.setGenres(genreSet);
                books.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;
    }

    @Override
    public Author add(AddBookDto addBookDto) {
        AuthorWithTimeZoned author = new AuthorWithTimeZoned();
        author.setFname(addBookDto.getAuthor_fname());
        author.setLname(addBookDto.getAuthor_lname());
        author.setPatronymic(addBookDto.getAuthor_middlename());
        author.setDate(ZonedDateTime.now());
        authorRepo.save(author);
        return author;
    }

    @Override
    public ResponseEntity<String> delete(String id) {
        Optional<Author> author = authorRepo.findById(Long.valueOf(id));
        if(author.get().getBooks().size() == 0){
            authorRepo.deleteById(Long.valueOf(id));
            return ResponseEntity.ok("Ok");
        }else{
            return ResponseEntity.ok("Ошибка у этого автора есть книги");
        }
        }
}
