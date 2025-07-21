package br.com.outsera.gra_service.adapters.outbound.entity;

import br.com.outsera.gra_service.domain.studio.Studio;
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
@Table(name = "GRA_STUDIO")
public class StudioJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDT_STUDIO", nullable = false)
    private Long id;

    @Column(name = "NAM_NAME", nullable = false, unique = true)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudioJpaEntity studio)) return false;
        return name != null && name.equalsIgnoreCase(studio.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name != null ? name.toLowerCase() : null);
    }

    public static StudioJpaEntity fromDomain(Studio studio) {
        return new StudioJpaEntity(
                studio.getId(),
                studio.getName()
        );
    }

    public static Studio toDomain(StudioJpaEntity studioJpaEntity) {
        return new Studio(
                studioJpaEntity.getId(),
                studioJpaEntity.getName()
        );
    }
}
