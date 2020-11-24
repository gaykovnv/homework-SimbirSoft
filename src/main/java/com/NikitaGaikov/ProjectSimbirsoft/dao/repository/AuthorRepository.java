package com.NikitaGaikov.ProjectSimbirsoft.dao.repository;

import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Author;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EntityScan
public interface AuthorRepository extends CrudRepository<Author,Long> {
}
