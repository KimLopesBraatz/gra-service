package br.com.outsera.gra_service.adapters.inbound;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProducerControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturnProducersIntervals() {
        // Act
        var response = testRestTemplate
                .getForEntity("/api/v1/producers/intervals", ProducerResponse.class);

        var minIntervalWinExpected = ProducerConsecutiveWinDTO.builder()
                .producer("Joel Silver")
                .previousWin(1990)
                .followingWin(1991)
                .interval(1)
                .build();

        var maxIntervalWinExpected = ProducerConsecutiveWinDTO.builder()
                .producer("Matthew Vaughn")
                .previousWin(2002)
                .followingWin(2015)
                .interval(13)
                .build();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(minIntervalWinExpected, Objects.requireNonNull(response.getBody()).getMin().getFirst());
        assertEquals(maxIntervalWinExpected, Objects.requireNonNull(response.getBody()).getMax().getFirst());
    }

    @Test
    public void shouldResponseStructureCorrect() throws JSONException {
        // Act
        var response = testRestTemplate
                .getForEntity("/api/v1/producers/intervals", String.class);

        var expectedJson = """
                {
                  "min" : [ {
                    "producer" : "Joel Silver",
                    "previousWin" : 1990,
                    "followingWin" : 1991,
                    "interval" : 1
                  } ],
                  "max" : [ {
                    "producer" : "Matthew Vaughn",
                    "previousWin" : 2002,
                    "followingWin" : 2015,
                    "interval" : 13
                  } ]
                }
                """;

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expectedJson, Objects.requireNonNull(response.getBody()), true);
    }
}