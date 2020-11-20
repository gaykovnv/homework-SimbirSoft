package com.NikitaGaikov.ProjectSimbirsoft.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonDto {
    @JsonProperty("person_id")
    private Integer id;
    private String fname;
    private String lname;
    private String patronymic;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;

}
