package br.com.outsera.gra_service.application.usecase;

import br.com.outsera.gra_service.infraestructure.MovieCsv;

public interface MovieUseCases {

    void createMovieFromCsvRow(MovieCsv movieCsv);
}
