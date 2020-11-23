package com.NikitaGaikov.ProjectSimbirsoft.service.interfac;

import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Person;
import com.NikitaGaikov.ProjectSimbirsoft.dto.AddBookDto;
import com.NikitaGaikov.ProjectSimbirsoft.dto.BookDto;
import com.NikitaGaikov.ProjectSimbirsoft.dto.PersonDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService {

    Person add(Person person);

    PersonDto update(PersonDto personDto);

    ResponseEntity<String> deleteById(String id);

    ResponseEntity<String> deleteByFIO(PersonDto personDto);

    List<BookDto> listTookBooksThePersonById(String id);

    ResponseEntity<String> addBookTookThePerson(AddBookDto addBookDto);

    ResponseEntity<String> backBook(AddBookDto addBookDto);
}
