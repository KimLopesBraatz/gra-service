package br.com.outsera.gra_service.infraestructure;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieCsv {

    @CsvBindByName
    private Integer year;

    @CsvBindByName
    private String title;

    @CsvBindByName
    private String studios;

    @CsvBindByName
    private String producers;

    @CsvBindByName
    private Boolean winner;
}
