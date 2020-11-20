package com.NikitaGaikov.ProjectSimbirsoft.сontroller;

import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Genre;
import com.NikitaGaikov.ProjectSimbirsoft.dto.GenreDto;
import com.NikitaGaikov.ProjectSimbirsoft.service.implemention.GenreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreRestController {

    @Autowired
    private GenreServiceImpl service;

    @GetMapping("/list")
    public List<Genre> listGenre(){
        return service.findAll();
    }

    @PostMapping("/add")
    public Genre add(@RequestBody GenreDto genreDto){
        return service.add(genreDto);
    }

    @PostMapping("/listGenreAndBook")
    public GenreDto listGenreAndBook(@RequestBody GenreDto genreDto){
        return service.findAllBookByGenre(genreDto);
    }
}
