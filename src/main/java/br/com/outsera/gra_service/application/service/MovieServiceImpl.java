package br.com.outsera.gra_service.application.service;

import br.com.outsera.gra_service.application.usecase.MovieUseCases;
import br.com.outsera.gra_service.application.usecase.ProducerUseCases;
import br.com.outsera.gra_service.application.usecase.StudioUseCases;
import br.com.outsera.gra_service.domain.movie.Movie;
import br.com.outsera.gra_service.domain.movie.MovieRepository;
import br.com.outsera.gra_service.domain.producer.Producer;
import br.com.outsera.gra_service.domain.studio.Studio;
import br.com.outsera.gra_service.infraestructure.MovieCsv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MovieServiceImpl implements MovieUseCases {

    private final MovieRepository movieRepository;

    private final ProducerUseCases producerUseCases;

    private final StudioUseCases studioUseCases;

    public MovieServiceImpl(MovieRepository movieRepository, ProducerUseCases producerUseCases, StudioUseCases studioUseCases) {
        this.movieRepository = movieRepository;
        this.producerUseCases = producerUseCases;
        this.studioUseCases = studioUseCases;
    }

    @Override
    public void createMovieFromCsvRow(MovieCsv movieCsv) {
        log.info("msg=\"Processing movie\" title={} year={}", movieCsv.getTitle(), movieCsv.getYear());
        Set<Studio> studios = getStudios(movieCsv);
        Set<Producer> producers = getProducers(movieCsv);

        Movie movie = new Movie(
                null,
                movieCsv.getYear(),
                movieCsv.getTitle(),
                Objects.isNull(movieCsv.getWinner()) ? Boolean.FALSE : movieCsv.getWinner(),
                studios,
                producers
        );

        movieRepository.save(movie);
    }

    private Set<Producer> getProducers(MovieCsv row) {
        log.info("msg=\"Processing producers\" producers={}", row.getProducers());
        return Arrays.stream(row.getProducers().split(",| and "))
                .map(String::trim)
                .filter(name -> !name.isEmpty())
                .map(producerUseCases::getOrCreateProducersFromSting)
                .collect(Collectors.toSet());
    }

    private Set<Studio> getStudios(MovieCsv row) {
        log.info("msg=\"Processing studios\" studios={}", row.getStudios());
        return Arrays.stream(row.getStudios().split(","))
                .map(String::trim)
                .map(studioUseCases::getOrCreateStudioFromString)
                .collect(Collectors.toSet());
    }
}
