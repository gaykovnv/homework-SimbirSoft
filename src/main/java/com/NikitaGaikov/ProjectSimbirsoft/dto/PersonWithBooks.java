package com.NikitaGaikov.ProjectSimbirsoft.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.text.SimpleDateFormat;
import java.util.*;

@JsonAutoDetect
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property="type")
@JsonSubTypes({
        @JsonSubTypes.Type(value=Person.class, name="person"),
        @JsonSubTypes.Type(value=Book.class, name="book")
})
public class PersonWithBooks {

    private int id;
    private Person person;
    @JsonDeserialize(contentAs=Arrays.class)
    private List<Book> books = new ArrayList<>();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String date;

    public PersonWithBooks() {
    }

    public PersonWithBooks(int id,Person person, List<Book> book, Date date) {
        this.id = id;
        this.person = person;
        this.books = book;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSXXX");
        this.date = simpleDateFormat.format(calendar.getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSXXX");
        this.date = simpleDateFormat.format(calendar.getTime());
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0 ; i<books.size(); i++){
            str += "[ 'id': '"+ books.get(i).getId() + "', 'name': '"+books.get(i).getName() + "', 'author': '"
                    + books.get(i).getAuthor() + "', 'genre': '" + books.get(i).getGenre() + "']";
        }
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonWithBooks that = (PersonWithBooks) o;
        return id == that.id &&
                Objects.equals(person, that.person) &&
                Objects.equals(books, that.books) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, person, books, date);
    }
}
