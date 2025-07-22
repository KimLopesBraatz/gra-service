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

    @Query(value = "SELECT * " +
            "FROM (" +
            "    SELECT p.NAM_NAME AS producer, m1.DAT_YEAR AS previousWin, m2.DAT_YEAR AS followingWin, (m2.DAT_YEAR - m1.DAT_YEAR) AS `interval` " +
            "    FROM GRA_PRODUCER p " +
            "    JOIN GRA_MOVIE_PRODUCER mp1 ON p.IDT_PRODUCER = mp1.IDT_PRODUCER " +
            "    JOIN GRA_MOVIE m1 ON mp1.IDT_MOVIE = m1.IDT_MOVIE " +
            "    JOIN GRA_MOVIE_PRODUCER mp2 ON p.IDT_PRODUCER = mp2.IDT_PRODUCER " +
            "    JOIN GRA_MOVIE m2 ON mp2.IDT_MOVIE = m2.IDT_MOVIE " +
            "    WHERE " +
            "        m1.FLG_WINNER = true AND m2.FLG_WINNER = true " +
            "        AND m2.DAT_YEAR > m1.DAT_YEAR " +
            "        AND NOT EXISTS ( " +
            "            SELECT 1 " +
            "            FROM GRA_MOVIE_PRODUCER mp3 " +
            "            JOIN GRA_MOVIE m3 ON mp3.IDT_MOVIE = m3.IDT_MOVIE " +
            "            WHERE " +
            "                mp3.IDT_PRODUCER = p.IDT_PRODUCER " +
            "                AND m3.FLG_WINNER = true " +
            "                AND m3.DAT_YEAR > m1.DAT_YEAR " +
            "                AND m3.DAT_YEAR < m2.DAT_YEAR " +
            "        ) " +
            ") intervals " +
            "WHERE intervals.`interval` = ( " +
            "    SELECT MAX(m2.DAT_YEAR - m1.DAT_YEAR) " +
            "    FROM GRA_PRODUCER p " +
            "    JOIN GRA_MOVIE_PRODUCER mp1 ON p.IDT_PRODUCER = mp1.IDT_PRODUCER " +
            "    JOIN GRA_MOVIE m1 ON mp1.IDT_MOVIE = m1.IDT_MOVIE " +
            "    JOIN GRA_MOVIE_PRODUCER mp2 ON p.IDT_PRODUCER = mp2.IDT_PRODUCER " +
            "    JOIN GRA_MOVIE m2 ON mp2.IDT_MOVIE = m2.IDT_MOVIE " +
            "    WHERE " +
            "        m1.FLG_WINNER = true AND m2.FLG_WINNER = true " +
            "        AND m2.DAT_YEAR > m1.DAT_YEAR " +
            "        AND NOT EXISTS ( " +
            "            SELECT 1 " +
            "            FROM GRA_MOVIE_PRODUCER mp3 " +
            "            JOIN GRA_MOVIE m3 ON mp3.IDT_MOVIE = m3.IDT_MOVIE " +
            "            WHERE " +
            "                mp3.IDT_PRODUCER = p.IDT_PRODUCER " +
            "                AND m3.FLG_WINNER = true " +
            "                AND m3.DAT_YEAR > m1.DAT_YEAR " +
            "                AND m3.DAT_YEAR < m2.DAT_YEAR " +
            "        ) " +
            ") " +
            "ORDER BY producer, previousWin",
            nativeQuery = true)
    List<ProducerConsecutiveWinProjection> findProducerConsecutiveWinsWithMaxInterval();
}
