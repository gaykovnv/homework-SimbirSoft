package com.NikitaGaikov.ProjectSimbirsoft.service.implemention;

import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Book;
import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Person;
import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.PersonWithTimeZoned;
import com.NikitaGaikov.ProjectSimbirsoft.dao.repository.BookRepository;
import com.NikitaGaikov.ProjectSimbirsoft.dao.repository.PersonRepository;
import com.NikitaGaikov.ProjectSimbirsoft.dto.*;
import com.NikitaGaikov.ProjectSimbirsoft.service.connectToDB.DBWork;
import com.NikitaGaikov.ProjectSimbirsoft.service.interfac.PersonService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private BookRepository bookRepo;

    @Override
    public PersonWithTimeZoned add(@NonNull PersonWithTimeZoned person) {
        personRepo.save(person);
        return person;
    }

    @Override
    public PersonDto update(PersonDto personDto) {
        Optional<Person> person = personRepo.findById(Long.valueOf(personDto.getId()));
        person.get().setFname(personDto.getFname());
        person.get().setLname(personDto.getLname());
        person.get().setPatronymic(personDto.getPatronymic());
        person.get().setDate(personDto.getBirthday());
        personRepo.save(person.get());
        return personDto;
    }

    @Override
    public ResponseEntity<String> deleteById(String id) {
        personRepo.deleteById(Long.valueOf(id));
        return ResponseEntity.ok("Ok");
    }

    @Override
    public ResponseEntity<String> deleteByFIO(PersonDto personDto) {
        DBWork dbWork = new DBWork();
        String query = " delete from person p where p.first_name like concat('%', ?,'%')" +
                "or p.last_name like concat('%',?,'%')" +
                "or p.patronymic like concat('%',?,'%')";
        try {
            PreparedStatement preparedStatement = dbWork.getConn().prepareStatement(query);
            preparedStatement.setString(1,personDto.getFname());
            preparedStatement.setString(2,personDto.getLname());
            preparedStatement.setString(3,personDto.getPatronymic());
            preparedStatement.executeUpdate();
            return ResponseEntity.ok("Ok");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return ResponseEntity.ok("Ошибка");
        }
    }

    @Override
    public List<BookDto> listTookBooksThePersonById(String id) {
        List<BookDto> listBook = new ArrayList<>();
        DBWork dbWork = new DBWork();
        try {

            String query = "select a.first_name, a.last_name, a.middle_name, b.name, g.genre " +
                    "from author a join book b on b.author_id = a.id join genre g " +
                    "join book_genre gb on b.id = gb.book_id join library_card l " +
                    "on b.id = l.book_id where l.person_id = ? and g.id = b.id;";

            PreparedStatement preparedStatement = dbWork.getConn().prepareStatement(query);
            preparedStatement.setString(1,id);
            System.out.println(preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                BookDto bookDto = new BookDto();
                bookDto.setName(resultSet.getString("name"));
                AuthorDto authorDto = new AuthorDto();
                authorDto.setFname(resultSet.getString("first_name"));
                authorDto.setLname(resultSet.getString("last_name"));
                authorDto.setPatronymic(resultSet.getString("middle_name"));
                bookDto.setAuthorDto(authorDto);
                GenreDto genreDto = new GenreDto();
                genreDto.setGenre(resultSet.getString("genre"));
                bookDto.setGenreDto(genreDto);
                listBook.add(bookDto);
            }
            dbWork.getConn().close();
        } catch (SQLException throwables) {
            System.out.println("mistake method: "+throwables.getMessage());
        }

        return listBook;
    }

    @Override
    public ResponseEntity<String> addBookTookThePerson(AddBookDto addBookDto) {
        DBWork dbWork = new DBWork();

        Optional<Person> person = personRepo.findById(Long.parseLong(addBookDto.getPerson_id()));
        Optional<Book> book = bookRepo.findById(Long.valueOf(addBookDto.getBook_id()));

        if(person.isPresent() && book.isPresent()) {
            String query = "insert into library_card values(?,?)";
            try {
                PreparedStatement preparedStatement = dbWork.getConn().prepareStatement(query);
                preparedStatement.setString(1, addBookDto.getBook_id());
                preparedStatement.setString(2, addBookDto.getPerson_id());
                preparedStatement.executeUpdate();
                return ResponseEntity.ok("OK");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return ResponseEntity.ok("Ошибка");
            }
        }else{
            return ResponseEntity.ok("Не найдена книга или человек");
        }
    }


    @Override
    public ResponseEntity<String> backBook(AddBookDto addBookDto) {
        DBWork dbWork = new DBWork();
        String query = "delete from library_card where person_id = ? and book_id = ?;";
        try {
            PreparedStatement preparedStatement = dbWork.getConn().prepareStatement(query);
            preparedStatement.setString(1,addBookDto.getPerson_id());
            preparedStatement.setString(2,addBookDto.getBook_id());
            preparedStatement.executeUpdate();
            return ResponseEntity.ok("ok");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return ResponseEntity.ok("mistake");
        }

    }

}
