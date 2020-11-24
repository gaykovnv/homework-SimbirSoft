package com.NikitaGaikov.ProjectSimbirsoft.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString(of = {"name","author","genre"})
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    @JsonProperty("book_id")
    private int id;
    private String name;

    private AuthorDto authorDto;
    private GenreDto genreDto;
}
