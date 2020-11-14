package com.NikitaGaikov.ProjectSimbirsoft.dto;

import lombok.*;

@Getter
@Setter
@ToString(of = {"name","author","genre"})
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private int id;
    private String name;
    private String author;
    private String genre;

}
