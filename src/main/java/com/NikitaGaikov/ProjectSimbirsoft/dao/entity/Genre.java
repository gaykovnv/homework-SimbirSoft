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
@EqualsAndHashCode
@EntityScan(basePackages = {"com.NikitaGaikov.ProjectSimbirsoft.dao.entity"})
@Table(name = "genre")
public class Genre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "genre", nullable = false)
    private String genre;

    @ManyToMany
    @JoinTable(name = "book_genre",
        joinColumns = @JoinColumn(name = "genre_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id"))
    @Fetch(FetchMode.SELECT)
    private Set<Book> books = new HashSet<>();
}
