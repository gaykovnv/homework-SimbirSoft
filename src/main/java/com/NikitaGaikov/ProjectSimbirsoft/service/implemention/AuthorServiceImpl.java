package com.NikitaGaikov.ProjectSimbirsoft.service.implemention;

import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Author;
import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Book;
import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Genre;
import com.NikitaGaikov.ProjectSimbirsoft.dao.repository.AuthorRepository;
import com.NikitaGaikov.ProjectSimbirsoft.dto.AddBookDto;
import com.NikitaGaikov.ProjectSimbirsoft.service.connectToDB.DBWork;
import com.NikitaGaikov.ProjectSimbirsoft.service.interfac.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private final AuthorRepository authorRepo;

    private Connection connection ;

    public AuthorServiceImpl(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public List<Author> findAll() {
        return (List<Author>) authorRepo.findAll();
    }

    @Override
    public List<Book> findAllBookOfAuthor(AddBookDto addBookDto) {
        DBWork dbWork = new DBWork();
        List<Book> books = new ArrayList<>();
        String query = "select b.name, g.genre, a.first_name, a.last_name,a.middle_name " +
                "from genre g inner join book b inner join book_genre bg on " +
                "bg.book_id = b.id and bg.genre_id = g.id inner join author a on a.id = b.author_id " +
                "where a.first_name = ? or a.last_name = ? or a.middle_name = ?;";
        try {
            PreparedStatement preparedStatement = dbWork.getConn().prepareStatement(query);
            preparedStatement.setString(1,addBookDto.getAuthorFname());
            preparedStatement.setString(2,addBookDto.getAuthorLname());
            preparedStatement.setString(3,addBookDto.getAuthorMiddlename());
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
        Author author = new Author();
        author.setFname(addBookDto.getAuthorFname());
        author.setLname(addBookDto.getAuthorLname());
        author.setPatronymic(addBookDto.getAuthorMiddlename());
        author.setDateTime(ZonedDateTime.now());
        authorRepo.save(author);
        return author;
    }

    @Override
    public boolean delete(String id) {
        Optional<Author> author = authorRepo.findById(Long.valueOf(id));
        if (!author.isPresent()){
            return false;
        }
        authorRepo.delete(author.get());
        return true;
    }
}
