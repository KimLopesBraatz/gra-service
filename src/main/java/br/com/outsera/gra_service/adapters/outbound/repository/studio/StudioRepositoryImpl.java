package br.com.outsera.gra_service.adapters.outbound.repository.studio;

import br.com.outsera.gra_service.adapters.outbound.entity.StudioJpaEntity;
import br.com.outsera.gra_service.domain.studio.Studio;
import br.com.outsera.gra_service.domain.studio.StudioRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StudioRepositoryImpl implements StudioRepository {

    private final StudioJpaRepository studioJpaRepository;

    public StudioRepositoryImpl(StudioJpaRepository studioJpaRepository) {
        this.studioJpaRepository = studioJpaRepository;
    }

    @Override
    public Studio save(Studio studio) {
        return StudioJpaEntity.toDomain(
                studioJpaRepository.save(StudioJpaEntity.fromDomain(studio))
        );
    }

    @Override
    public Optional<Studio> findByNameIgnoreCase(String name) {
        return studioJpaRepository.findByNameIgnoreCase(name)
                .map(StudioJpaEntity::toDomain);
    }
}
