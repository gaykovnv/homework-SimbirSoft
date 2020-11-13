package com.NikitaGaikov.ProjectSimbirsoft.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@ToString(of = {"id","birthday"})
@EqualsAndHashCode(of = {"id"})
public class Person {

    private int id;
    private String fname;
    private String lname;
    private String patronymic;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthday;

    public Person() {
    }

    public Person(int id,String fname, String lname, String patronymic,LocalDate birthday) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.patronymic = patronymic;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "['fname':'" + fname +
                "', 'lname':'" + lname +
                "', 'patronymic':'" + patronymic +
                "', 'birthday':'" + birthday +
                "']";
    }
}
