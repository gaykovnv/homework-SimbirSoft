package com.NikitaGaikov.ProjectSimbirsoft.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
public class AuthorWithTimeZoned extends Author{

    @Column(name = "date_change")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS'Z'", timezone="GMT")
    private ZonedDateTime date = ZonedDateTime.now().plusHours(3);
}
