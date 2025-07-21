package br.com.outsera.gra_service.application.usecase;

import br.com.outsera.gra_service.domain.producer.Producer;
import br.com.outsera.gra_service.adapters.inbound.ProducerConsecutiveWinDTO;

import java.util.List;

public interface ProducerUseCases {

    Producer getOrCreateProducersFromSting(String producerName);

    List<ProducerConsecutiveWinDTO> findProducersWithMinIntervalWins();

    List<ProducerConsecutiveWinDTO> findProducersWithMaxIntervalWins();
}
