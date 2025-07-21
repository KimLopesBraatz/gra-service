package br.com.outsera.gra_service.domain.movie;

import br.com.outsera.gra_service.domain.producer.Producer;
import br.com.outsera.gra_service.domain.studio.Studio;

import java.util.Set;

public class Movie {

    private Long id;

    private Integer year;

    private String title;

    private Boolean winner;

    private Set<Studio> studios;

    private Set<Producer> producers;

    public Movie() {}

    public Movie(Long id, Integer year, String title, Boolean winner, Set<Studio> studios, Set<Producer> producers) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.winner = winner;
        this.studios = studios;
        this.producers = producers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    public Set<Studio> getStudios() {
        return studios;
    }

    public void setStudios(Set<Studio> studios) {
        this.studios = studios;
    }

    public Set<Producer> getProducers() {
        return producers;
    }

    public void setProducers(Set<Producer> producers) {
        this.producers = producers;
    }
}
