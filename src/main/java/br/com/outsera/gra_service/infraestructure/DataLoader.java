package br.com.outsera.gra_service.infraestructure;

import br.com.outsera.gra_service.application.usecase.MovieUseCases;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Autowired
    private final MovieUseCases movieUseCases;

    @Value("${csv.path}")
    private String csvPath;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public void run(String... args) throws Exception {
        try (Reader reader = resolveReader(csvPath)) {
            var csvParser = new CSVParserBuilder()
                    .withSeparator(';')
                    .withIgnoreQuotations(true)
                    .build();

            var csvReader = new CSVReaderBuilder(reader)
                    .withCSVParser(csvParser)
                    .withSkipLines(0)
                    .build();

            List<MovieCsv> movies = new CsvToBeanBuilder<MovieCsv>(csvReader)
                    .withType(MovieCsv.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();

            processCsvData(movies);
            log.info("msg=\"File read and process successfully!\" file_path={}", csvPath);
        } catch (Exception exception) {
            log.error("msg=\"Error on process file\" error={}", exception.getMessage());
        }
    }

    private Reader resolveReader(String path) throws Exception {
        if (path != null && path.startsWith("classpath:")) {
            Resource resource = resourceLoader.getResource(path);
            return new java.io.InputStreamReader(resource.getInputStream(), java.nio.charset.StandardCharsets.UTF_8);
        } else {
            return new java.io.FileReader(path, java.nio.charset.StandardCharsets.UTF_8);
        }
    }

    private void processCsvData(List<MovieCsv> movies) {
        log.info("msg=\"Starting store data\" total_movies={}", movies.size());
        for (MovieCsv row : movies) {
            movieUseCases.createMovieFromCsvRow(row);
        }
        log.info("msg=\"Data stored successfully!\" total_movies={}", movies.size());
    }
}
