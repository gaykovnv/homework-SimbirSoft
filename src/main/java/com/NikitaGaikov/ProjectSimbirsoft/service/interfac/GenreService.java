package com.NikitaGaikov.ProjectSimbirsoft.service.interfac;

import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Genre;
import com.NikitaGaikov.ProjectSimbirsoft.dto.BookDto;
import com.NikitaGaikov.ProjectSimbirsoft.dto.GenreDto;

import java.util.List;

public interface GenreService {

    List<Genre> findAll();

    Genre add(GenreDto genre);

    GenreDto findAllBookByGenre(GenreDto genreDto);
}
