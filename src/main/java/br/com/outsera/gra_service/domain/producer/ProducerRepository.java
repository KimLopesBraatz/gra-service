package br.com.outsera.gra_service.domain.producer;

import br.com.outsera.gra_service.adapters.inbound.ProducerConsecutiveWinDTO;

import java.util.List;
import java.util.Optional;

public interface ProducerRepository {

    Producer save(Producer producer);

    Optional<Producer> findByNameIgnoreCase(String name);

    List<ProducerConsecutiveWinDTO> findProducerConsecutiveWinsWithIntervalMin();

    List<ProducerConsecutiveWinDTO> findProducerConsecutiveWinsWithMaxInterval();
}
