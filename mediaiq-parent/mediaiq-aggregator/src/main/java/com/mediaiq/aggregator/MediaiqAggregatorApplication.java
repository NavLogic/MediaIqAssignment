package com.mediaiq.aggregator;

import io.swagger.annotations.Api;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mediaiq.aggregator.configurations.SwaggerConfiguration;
import com.mediaiq.aggregator.dto.SearchResponse;
import com.mediaiq.aggregator.service.SearchService;

@RestController
@SpringBootApplication
@Import({ SwaggerConfiguration.class })
@Api(value = "static", description = "Serves static content")
public class MediaiqAggregatorApplication {

    @Autowired
    private Environment environment;

    @Autowired
    private SearchService searchService;

    public static void main(String[] args) {
        SpringApplication.run(MediaiqAggregatorApplication.class, args);
    }

    @RequestMapping(value = "/rest/searchsources/{keyword}", method = RequestMethod.GET)
    public SearchResponse searchLinesForKeyWordInAllSources(@PathVariable String keyword) {
        return searchService.searchLinesForKeyWord(keyword);
    }

    @RequestMapping(value = "/rest/gettextfile", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> streamTextFile() throws IOException {
        ClassPathResource txtFile = new ClassPathResource(environment.getProperty("FILE_PATH"));
        return ResponseEntity.ok().contentLength(txtFile.contentLength()).contentType(MediaType.parseMediaType("text/plain"))
                .body(new InputStreamResource(txtFile.getInputStream()));
    }

}
