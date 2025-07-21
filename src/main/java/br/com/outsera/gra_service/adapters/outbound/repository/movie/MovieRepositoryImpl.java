package br.com.outsera.gra_service.adapters.outbound.repository.movie;

import br.com.outsera.gra_service.adapters.outbound.entity.MovieJpaEntity;
import br.com.outsera.gra_service.domain.movie.Movie;
import br.com.outsera.gra_service.domain.movie.MovieRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    private final MovieJpaRepository movieJpaRepository;

    public MovieRepositoryImpl(MovieJpaRepository movieJpaRepository) {
        this.movieJpaRepository = movieJpaRepository;
    }

    @Override
    public Movie save(Movie movie) {
        return MovieJpaEntity.toDomain(
                movieJpaRepository.save(MovieJpaEntity.fromDomain(movie))
        );
    }
}
