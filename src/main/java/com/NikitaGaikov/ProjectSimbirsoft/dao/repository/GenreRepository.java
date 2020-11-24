package com.NikitaGaikov.ProjectSimbirsoft.dao.repository;

import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Genre;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@EntityScan
public interface GenreRepository extends CrudRepository<Genre,Long> {

}
