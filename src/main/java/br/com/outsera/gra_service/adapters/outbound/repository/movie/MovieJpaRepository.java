package br.com.outsera.gra_service.adapters.outbound.repository.movie;

import br.com.outsera.gra_service.adapters.outbound.entity.MovieJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieJpaRepository extends JpaRepository<MovieJpaEntity, Long> {
}
