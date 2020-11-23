package com.NikitaGaikov.ProjectSimbirsoft.service.interfac;

import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Person;
import com.NikitaGaikov.ProjectSimbirsoft.dto.AddBookDto;
import com.NikitaGaikov.ProjectSimbirsoft.dto.BookDto;
import com.NikitaGaikov.ProjectSimbirsoft.dto.PersonDto;

import java.util.List;

public interface PersonService {

    Person add(Person person);

    PersonDto update(PersonDto personDto);

    boolean deleteById(String id);

    boolean deleteByFIO(PersonDto personDto);

    List<BookDto> listTookBooksThePersonById(String id);

    boolean addBookTookThePerson(AddBookDto addBookDto);

    boolean backBook(AddBookDto addBookDto);
}
