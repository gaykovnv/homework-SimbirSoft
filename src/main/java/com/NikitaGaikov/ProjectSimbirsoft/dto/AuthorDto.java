package com.NikitaGaikov.ProjectSimbirsoft.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AuthorDto {

    private Long id;
    private String fname;
    private String lname;
    private String patronymic;
}
