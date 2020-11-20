package com.NikitaGaikov.ProjectSimbirsoft.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {

    private Long id;
    private String genre;
    private int countBook;
}
