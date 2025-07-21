package br.com.outsera.gra_service.adapters.outbound.repository.studio;

import br.com.outsera.gra_service.adapters.outbound.entity.StudioJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudioJpaRepository extends JpaRepository<StudioJpaEntity, Long> {

    Optional<StudioJpaEntity> findByNameIgnoreCase(String name);
}
