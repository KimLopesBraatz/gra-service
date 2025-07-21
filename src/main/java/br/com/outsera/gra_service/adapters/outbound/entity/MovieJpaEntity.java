package br.com.outsera.gra_service.adapters.outbound.entity;

import br.com.outsera.gra_service.domain.producer.Producer;
import br.com.outsera.gra_service.domain.studio.Studio;
import br.com.outsera.gra_service.domain.movie.Movie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GRA_MOVIE")
public class MovieJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDT_MOVIE", nullable = false)
    private Long id;

    @Column(name = "DAT_YEAR", nullable = false)
    private Integer year;

    @Column(name = "DES_TITLE", nullable = false)
    private String title;

    @Column(name = "FLG_WINNER")
    private Boolean winner;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "GRA_MOVIE_STUDIO",
            joinColumns = @JoinColumn(name = "IDT_MOVIE"),
            inverseJoinColumns = @JoinColumn(name = "IDT_STUDIO")
    )
    private Set<StudioJpaEntity> studios;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "GRA_MOVIE_PRODUCER",
            joinColumns = @JoinColumn(name = "IDT_MOVIE"),
            inverseJoinColumns = @JoinColumn(name = "IDT_PRODUCER")
    )
    private Set<ProducerJpaEntity> producers;

    public static MovieJpaEntity fromDomain(Movie movie) {
        return new MovieJpaEntity(
                movie.getId(),
                movie.getYear(),
                movie.getTitle(),
                movie.getWinner(),
                getStudiosFromDomain(movie.getStudios()),
                getProducersFromDomain(movie.getProducers())
        );
    }

    private static Set<StudioJpaEntity> getStudiosFromDomain(Set<Studio> studios) {
        return studios.stream()
                .map(StudioJpaEntity::fromDomain)
                .collect(Collectors.toSet());
    }

    private static Set<ProducerJpaEntity> getProducersFromDomain(Set<Producer> studios) {
        return studios.stream()
                .map(ProducerJpaEntity::fromDomain)
                .collect(Collectors.toSet());
    }

    public static Movie toDomain(MovieJpaEntity movieJpaEntity) {
        return new Movie(
                movieJpaEntity.getId(),
                movieJpaEntity.getYear(),
                movieJpaEntity.getTitle(),
                movieJpaEntity.getWinner(),
                getStudiosToDomain(movieJpaEntity.getStudios()),
                getProducersToDomain(movieJpaEntity.getProducers())
        );
    }

    private static Set<Studio> getStudiosToDomain(Set<StudioJpaEntity> studios) {
        return studios.stream()
                .map(StudioJpaEntity::toDomain)
                .collect(Collectors.toSet());
    }

    private static Set<Producer> getProducersToDomain(Set<ProducerJpaEntity> producers) {
        return producers.stream()
                .map(ProducerJpaEntity::toDomain)
                .collect(Collectors.toSet());
    }
}
