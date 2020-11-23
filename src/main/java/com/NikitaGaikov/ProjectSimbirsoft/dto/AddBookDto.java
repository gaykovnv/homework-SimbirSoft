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
    private String authorFname;
    private String authorLname;
    private String authorMiddlename;
    private String genreName;
    private String bookId;
    private String personId;
}
