package br.com.outsera.gra_service.adapters.inbound;

import br.com.outsera.gra_service.application.usecase.ProducerUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/producers")
public class ProducerController {

    @Autowired
    private final ProducerUseCases producerUseCases;

    @GetMapping("/intervals")
    @ResponseBody
    public ResponseEntity<ProducerResponse> getProducersIntervals() {

        List<ProducerConsecutiveWinDTO> minList = producerUseCases.findProducersWithMinIntervalWins();
        List<ProducerConsecutiveWinDTO> maxList = producerUseCases.findProducersWithMaxIntervalWins();

        ProducerResponse response = ProducerResponse.builder().min(minList).max(maxList).build();

        return ResponseEntity.ok(response);
    }
}
