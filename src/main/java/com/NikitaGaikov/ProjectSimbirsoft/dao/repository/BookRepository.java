package com.NikitaGaikov.ProjectSimbirsoft.dao.repository;

import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Book;
import com.NikitaGaikov.ProjectSimbirsoft.dto.BookDto;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EntityScan
public interface BookRepository extends CrudRepository<Book,Long> {


}
