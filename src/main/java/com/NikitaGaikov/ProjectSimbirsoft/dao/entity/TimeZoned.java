package com.NikitaGaikov.ProjectSimbirsoft.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
public class TimeZoned {

    @Column(name = "date_change")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss.SSSXXX",timezone = "GMT")
    private ZonedDateTime dateTime;
}
