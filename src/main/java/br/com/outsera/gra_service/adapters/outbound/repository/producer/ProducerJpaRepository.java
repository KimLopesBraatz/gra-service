package br.com.outsera.gra_service.adapters.outbound.repository.producer;

import br.com.outsera.gra_service.adapters.outbound.entity.ProducerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProducerJpaRepository extends JpaRepository<ProducerJpaEntity, Long> {

    Optional<ProducerJpaEntity> findByNameIgnoreCase(String name);

    @Query(value = "SELECT p.NAM_NAME as producer, m1.DAT_YEAR as previousWin, m2.DAT_YEAR as followingWin, (m2.DAT_YEAR - m1.DAT_YEAR) as `interval` " +
            "FROM GRA_PRODUCER p " +
            "JOIN GRA_MOVIE_PRODUCER mp1 ON p.IDT_PRODUCER = mp1.IDT_PRODUCER " +
            "JOIN GRA_MOVIE m1 ON mp1.IDT_MOVIE = m1.IDT_MOVIE " +
            "JOIN GRA_MOVIE_PRODUCER mp2 ON p.IDT_PRODUCER = mp2.IDT_PRODUCER " +
            "JOIN GRA_MOVIE m2 ON mp2.IDT_MOVIE = m2.IDT_MOVIE " +
            "WHERE m1.FLG_WINNER = true AND m2.FLG_WINNER = true " +
            "AND m1.IDT_MOVIE <> m2.IDT_MOVIE " +
            "AND (m2.DAT_YEAR - m1.DAT_YEAR) = 1",
            nativeQuery = true)
    List<ProducerConsecutiveWinProjection> findProducerConsecutiveWinsWithIntervalMin();

    @Query(value = "SELECT p.NAM_NAME as producer, m1.DAT_YEAR as previousWin, m2.DAT_YEAR as followingWin, (m2.DAT_YEAR - m1.DAT_YEAR) as `interval` " +
            "FROM GRA_PRODUCER p " +
            "JOIN GRA_MOVIE_PRODUCER mp1 ON p.IDT_PRODUCER = mp1.IDT_PRODUCER " +
            "JOIN GRA_MOVIE m1 ON mp1.IDT_MOVIE = m1.IDT_MOVIE " +
            "JOIN GRA_MOVIE_PRODUCER mp2 ON p.IDT_PRODUCER = mp2.IDT_PRODUCER " +
            "JOIN GRA_MOVIE m2 ON mp2.IDT_MOVIE = m2.IDT_MOVIE " +
            "WHERE m1.FLG_WINNER = true AND m2.FLG_WINNER = true " +
            "AND m1.IDT_MOVIE <> m2.IDT_MOVIE " +
            "AND m2.DAT_YEAR > m1.DAT_YEAR " +
            "AND (m2.DAT_YEAR - m1.DAT_YEAR) = (" +
            "   SELECT MAX(m2b.DAT_YEAR - m1b.DAT_YEAR) " +
            "   FROM GRA_PRODUCER pb " +
            "   JOIN GRA_MOVIE_PRODUCER mp1b ON pb.IDT_PRODUCER = mp1b.IDT_PRODUCER " +
            "   JOIN GRA_MOVIE m1b ON mp1b.IDT_MOVIE = m1b.IDT_MOVIE " +
            "   JOIN GRA_MOVIE_PRODUCER mp2b ON pb.IDT_PRODUCER = mp2b.IDT_PRODUCER " +
            "   JOIN GRA_MOVIE m2b ON mp2b.IDT_MOVIE = m2b.IDT_MOVIE " +
            "   WHERE m1b.FLG_WINNER = true AND m2b.FLG_WINNER = true " +
            "   AND m1b.IDT_MOVIE <> m2b.IDT_MOVIE " +
            "   AND m2b.DAT_YEAR > m1b.DAT_YEAR" +
            ") ",
            nativeQuery = true)
    List<ProducerConsecutiveWinProjection> findProducerConsecutiveWinsWithMaxInterval();
}
