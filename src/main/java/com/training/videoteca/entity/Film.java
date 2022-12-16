package com.training.videoteca.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="film")
public class Film {
    @Id
    @Column(name="id")

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Titolo")
    private String titolo;

    @Column(name="Anno")
    private String data;

    @Column(name = "idgenere", insertable = false, updatable = false)
    @JsonIgnore
    private Long idgenere;

    @ManyToOne
    @JoinColumn(name="idgenere")
    private Genere genere;

    @ManyToMany
    @JoinTable(name="film_interprete",
            joinColumns = @JoinColumn(name="id"),
            inverseJoinColumns = @JoinColumn(name="id_interprete")
    )
    private Set<Interprete> interpreteSet;


    public Film(String titolo, String data, Genere genere, Set<Interprete> numInterpretiPerF) {
        this.titolo = titolo;
        this.data = data;
        this.genere = genere;
        this.interpreteSet = numInterpretiPerF;
    }

    public Film(String titolo, String data) {
        this.titolo = titolo;
        this.data = data;
    }
}
