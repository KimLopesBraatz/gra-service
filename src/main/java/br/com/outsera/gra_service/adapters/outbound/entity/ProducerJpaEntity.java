package br.com.outsera.gra_service.adapters.outbound.entity;

import br.com.outsera.gra_service.domain.producer.Producer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GRA_PRODUCER")
public class ProducerJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDT_PRODUCER", nullable = false)
    private Long id;

    @Column(name = "NAM_NAME", nullable = false, unique = true)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProducerJpaEntity producer)) return false;
        return name != null && name.equalsIgnoreCase(producer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name != null ? name.toLowerCase() : null);
    }

    public static ProducerJpaEntity fromDomain(Producer producer) {
        return new ProducerJpaEntity(
                producer.getId(),
                producer.getName()
        );
    }

    public static Producer toDomain(ProducerJpaEntity producerJpaEntity) {
        return new Producer(
                producerJpaEntity.getId(),
                producerJpaEntity.getName()
        );
    }
}
