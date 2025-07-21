package br.com.outsera.gra_service.application.usecase;

import br.com.outsera.gra_service.domain.studio.Studio;

public interface StudioUseCases {

    Studio getOrCreateStudioFromString(String studioName);
}
