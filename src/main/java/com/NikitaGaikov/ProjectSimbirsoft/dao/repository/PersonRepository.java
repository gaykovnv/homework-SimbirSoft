package com.NikitaGaikov.ProjectSimbirsoft.dao.repository;

import com.NikitaGaikov.ProjectSimbirsoft.dao.entity.Person;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EntityScan
public interface PersonRepository extends CrudRepository<Person,Long> {

}
