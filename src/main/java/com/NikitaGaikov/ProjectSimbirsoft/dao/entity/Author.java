package com.NikitaGaikov.ProjectSimbirsoft.dao.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor

@EntityScan(basePackages = {"com.NikitaGaikov.ProjectSimbirsoft.dao.entity"})
@Table(name = "author")
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String fname;

    @Column(name = "last_name", nullable = false)
    private String lname;

    @Column(name = "middle_name")
    private String patronymic;

    @OneToMany(mappedBy = "author" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private Set<Book> books = new HashSet<>();
}
