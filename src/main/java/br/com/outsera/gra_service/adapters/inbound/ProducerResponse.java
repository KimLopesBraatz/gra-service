package br.com.outsera.gra_service.adapters.inbound;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProducerResponse {

    private List<ProducerConsecutiveWinDTO> min;
    private List<ProducerConsecutiveWinDTO> max;
}
