package br.com.outsera.gra_service.application.service;

import br.com.outsera.gra_service.application.usecase.StudioUseCases;
import br.com.outsera.gra_service.domain.studio.Studio;
import br.com.outsera.gra_service.domain.studio.StudioRepository;
import org.springframework.stereotype.Service;

@Service
public class StudioServiceImpl implements StudioUseCases {

    private final StudioRepository studioRepository;

    public StudioServiceImpl(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    @Override
    public Studio getOrCreateStudioFromString(String studioName) {
        return studioRepository.findByNameIgnoreCase(studioName)
                .orElseGet(() -> studioRepository.save(
                        new Studio(null, studioName)
                ));
    }
}
