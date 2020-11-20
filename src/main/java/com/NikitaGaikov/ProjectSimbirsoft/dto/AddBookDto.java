package com.NikitaGaikov.ProjectSimbirsoft.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AddBookDto {

    private String name;
    private String author_fname;
    private String author_lname;
    private String author_middlename;
    private String genre_name;
    private String book_id;
    private String person_id;
}
