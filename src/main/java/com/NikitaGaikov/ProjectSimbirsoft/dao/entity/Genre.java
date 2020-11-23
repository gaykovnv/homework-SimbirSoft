package com.NikitaGaikov.ProjectSimbirsoft.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@EqualsAndHashCode(of = {"genre"})
@Inheritance
@EntityScan(basePackages = {"com.NikitaGaikov.ProjectSimbirsoft.dao.entity"})
@Table(name = "genre")
public class Genre extends TimeZoned implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "genre", nullable = false)
    private String genre;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "book_genre",
        joinColumns = @JoinColumn(name = "genre_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id"))
    @Fetch(FetchMode.SELECT)
    private Set<Book> books = new HashSet<>();
}
