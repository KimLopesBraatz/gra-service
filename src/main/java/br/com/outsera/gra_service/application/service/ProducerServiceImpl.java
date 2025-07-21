package br.com.outsera.gra_service.application.service;

import br.com.outsera.gra_service.adapters.inbound.ProducerConsecutiveWinDTO;
import br.com.outsera.gra_service.application.usecase.ProducerUseCases;
import br.com.outsera.gra_service.domain.producer.Producer;
import br.com.outsera.gra_service.domain.producer.ProducerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProducerServiceImpl implements ProducerUseCases {

    private final ProducerRepository producerRepository;

    public ProducerServiceImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public Producer getOrCreateProducersFromSting(String producerName) {
        return producerRepository.findByNameIgnoreCase(producerName)
                .orElseGet(() -> producerRepository.save(
                        new Producer(null, producerName)
                ));
    }

    @Override
    public List<ProducerConsecutiveWinDTO> findProducersWithMinIntervalWins() {
        return producerRepository.findProducerConsecutiveWinsWithIntervalMin();
    }

    @Override
    public List<ProducerConsecutiveWinDTO> findProducersWithMaxIntervalWins() {
        return producerRepository.findProducerConsecutiveWinsWithMaxInterval();
    }
}
