package br.com.outsera.gra_service.adapters.inbound;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProducerConsecutiveWinDTO {

    private String producer;
    private Integer previousWin;
    private Integer followingWin;
    private Integer interval;
}
