package br.com.outsera.gra_service.adapters.inbound;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import java.util.List;
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
                .getForEntity("/api/v1/producers/winning-intervals", ProducerResponse.class);

        var minIntervalWinListExpected = buildMinIntervalWinListExpected();
        var maxIntervalWinListExpected = buildMaxIntervalWinListExpected();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertResponseResultEquals(minIntervalWinListExpected, Objects.requireNonNull(response.getBody()).getMin());
        assertResponseResultEquals(maxIntervalWinListExpected, Objects.requireNonNull(response.getBody()).getMax());
    }

    @Test
    public void shouldResponseStructureCorrect() throws JSONException {
        // Act
        var response = testRestTemplate
                .getForEntity("/api/v1/producers/winning-intervals", String.class);

        var expectedJson = """
                {
                   "min" : [ {
                     "producer" : "Joel Silver",
                     "previousWin" : 1990,
                     "followingWin" : 1991,
                     "interval" : 1
                   }, {
                     "producer" : "Matthew Vaughn",
                     "previousWin" : 2002,
                     "followingWin" : 2003,
                     "interval" : 1
                   } ],
                   "max" : [ {
                     "producer" : "Matthew Vaughn",
                     "previousWin" : 1980,
                     "followingWin" : 2002,
                     "interval" : 22
                   }, {
                     "producer" : "Matthew Vaughn",
                     "previousWin" : 2015,
                     "followingWin" : 2037,
                     "interval" : 22
                   } ]
                 }
                """;

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expectedJson, Objects.requireNonNull(response.getBody()), true);
    }

    private List<ProducerConsecutiveWinDTO> buildMaxIntervalWinListExpected() {
        return List.of(
                ProducerConsecutiveWinDTO.builder()
                        .producer("Matthew Vaughn")
                        .previousWin(1980)
                        .followingWin(2002)
                        .interval(22)
                        .build(),
                ProducerConsecutiveWinDTO.builder()
                        .producer("Matthew Vaughn")
                        .previousWin(2015)
                        .followingWin(2037)
                        .interval(22)
                        .build()
        );
    }

    private List<ProducerConsecutiveWinDTO> buildMinIntervalWinListExpected() {
        return List.of(
                ProducerConsecutiveWinDTO.builder()
                        .producer("Joel Silver")
                        .previousWin(1990)
                        .followingWin(1991)
                        .interval(1)
                        .build(),
                ProducerConsecutiveWinDTO.builder()
                        .producer("Matthew Vaughn")
                        .previousWin(2002)
                        .followingWin(2003)
                        .interval(1)
                        .build()
        );
    }

    private void assertResponseResultEquals(List<ProducerConsecutiveWinDTO> minIntervalWinListExpected, List<ProducerConsecutiveWinDTO> response) {
        minIntervalWinListExpected.forEach(
                expected -> assertEquals(
                        expected,
                        response.stream()
                                .filter(actual -> actual.getProducer().equals(expected.getProducer())
                                        && Objects.equals(actual.getPreviousWin(), expected.getPreviousWin())
                                        && Objects.equals(actual.getFollowingWin(), expected.getFollowingWin())
                                        && Objects.equals(actual.getInterval(), expected.getInterval()))
                                .findFirst()
                                .orElse(null),
                        "Expected and actual producer intervals do not match"
                )
        );
    }
}