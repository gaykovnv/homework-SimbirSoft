package com.NikitaGaikov.ProjectSimbirsoft.service.implemention;

import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Genre;
import com.NikitaGaikov.ProjectSimbirsoft.dao.repository.GenreRepository;
import com.NikitaGaikov.ProjectSimbirsoft.dto.GenreDto;
import com.NikitaGaikov.ProjectSimbirsoft.service.connectToDB.DBWork;
import com.NikitaGaikov.ProjectSimbirsoft.service.interfac.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private final GenreRepository genreRepo;

    public GenreServiceImpl(GenreRepository genreRepo) {
        this.genreRepo = genreRepo;
    }


    @Override
    public List<Genre> findAll() {
        return (List<Genre>) genreRepo.findAll();
    }

    @Override
    public Genre add(GenreDto genreDto) {
        Genre genre = new Genre();
        genre.setGenre(genreDto.getGenre());
        genre.setDateTime(ZonedDateTime.now());
        return genreRepo.save(genre);
    }

    @Override
    public GenreDto findAllBookByGenre(GenreDto genreDto) {
        DBWork dbWork = new DBWork();
        int count = 0;
        List<String> books = new ArrayList<>();
        Optional<Genre> genre = genreRepo.findById(genreDto.getId());
        String query = "select * from book_genre where genre_id = ?;";
        try {
            PreparedStatement preparedStatement = dbWork.getConn().prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(genreDto.getId()));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                books.add(resultSet.getString("book_id"));
                count++;
            }
            genreDto.setGenre(genre.get().getGenre());
            genreDto.setCountBook(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return genreDto;
    }
}
