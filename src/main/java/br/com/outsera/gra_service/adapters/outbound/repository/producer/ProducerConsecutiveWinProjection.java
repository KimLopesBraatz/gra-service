package br.com.outsera.gra_service.adapters.outbound.repository.producer;

public interface ProducerConsecutiveWinProjection {

    String getProducer();
    Integer getPreviousWin();
    Integer getFollowingWin();
    Integer getInterval();

}
