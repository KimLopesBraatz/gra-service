package br.com.outsera.gra_service.domain.studio;

import java.util.Optional;

public interface StudioRepository {

    Studio save(Studio studio);

    Optional<Studio> findByNameIgnoreCase(String name);
}
