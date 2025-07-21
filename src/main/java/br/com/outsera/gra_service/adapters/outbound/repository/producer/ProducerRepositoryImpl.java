package br.com.outsera.gra_service.adapters.outbound.repository.producer;

import br.com.outsera.gra_service.adapters.inbound.ProducerConsecutiveWinDTO;
import br.com.outsera.gra_service.adapters.outbound.entity.ProducerJpaEntity;
import br.com.outsera.gra_service.domain.producer.Producer;
import br.com.outsera.gra_service.domain.producer.ProducerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProducerRepositoryImpl implements ProducerRepository {

    private final ProducerJpaRepository producerJpaRepository;

    public ProducerRepositoryImpl(ProducerJpaRepository producerJpaRepository) {
        this.producerJpaRepository = producerJpaRepository;
    }

    @Override
    public Producer save(Producer producer) {
        return ProducerJpaEntity.toDomain(
                producerJpaRepository.save(ProducerJpaEntity.fromDomain(producer))
        );
    }

    @Override
    public Optional<Producer> findByNameIgnoreCase(String name) {
        return producerJpaRepository.findByNameIgnoreCase(name)
                .map(ProducerJpaEntity::toDomain);
    }

    @Override
    public List<ProducerConsecutiveWinDTO> findProducerConsecutiveWinsWithIntervalMin() {
        return producerJpaRepository.findProducerConsecutiveWinsWithIntervalMin().stream()
                .map(projection -> ProducerConsecutiveWinDTO.builder()
                        .producer(projection.getProducer())
                        .previousWin(projection.getPreviousWin())
                        .followingWin(projection.getFollowingWin())
                        .interval(projection.getInterval())
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public List<ProducerConsecutiveWinDTO> findProducerConsecutiveWinsWithMaxInterval() {
        return producerJpaRepository.findProducerConsecutiveWinsWithMaxInterval().stream()
                .map(projection -> ProducerConsecutiveWinDTO.builder()
                        .producer(projection.getProducer())
                        .previousWin(projection.getPreviousWin())
                        .followingWin(projection.getFollowingWin())
                        .interval(projection.getInterval())
                        .build()
                ).collect(Collectors.toList());
    }

}
