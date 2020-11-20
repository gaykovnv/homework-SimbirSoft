package com.NikitaGaikov.ProjectSimbirsoft.dao.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@EntityScan(basePackages = {"com.NikitaGaikov.ProjectSimbirsoft.dao.entity"})
@Table(name = "person")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NonNull
    private String fname;

    @Column(name = "last_name", nullable = false)
    @NonNull
    private String lname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "birthday")
    private LocalDate date;

    @ManyToMany
    @JoinTable(name = "library_card",
        joinColumns = @JoinColumn(name = "person_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id"))
    @Fetch(FetchMode.SELECT)
    private Set<Book> books = new HashSet<>();
}
