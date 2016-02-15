package com.mediaiq.aggregator;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import com.mediaiq.aggregator.dto.SearchResponse;
import com.mediaiq.sources.dto.ApiSearchResponse;
import com.mediaiq.sources.dto.DataBaseSearchResponse;
import com.mediaiq.sources.dto.FileSearchResponse;
import com.mediaiq.sources.service.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MediaiqAggregatorApplication.class)
@WebAppConfiguration
// @TestPropertySource(locations = "classpath:application-test.properties")
public class MediaiqAggregatorApplicationTests {

    @Autowired
    @Qualifier("fileDataSource")
    private DataSource fileDataSource;

    @Autowired
    @Qualifier("dbDataSource")
    private DataSource dbDataSource;

    @Autowired
    @Qualifier("apiDataSource")
    private DataSource apiDataSource;

    @Autowired
    MediaiqAggregatorApplication mediaiqAggregatorApplication;

    @Test
    public void contextLoads() {
    }

    @Test
    public void readFromFileSource() {
        try {
            FileSearchResponse fileSearchResponse = fileDataSource.readFromDataSource();
            Assert.notEmpty(fileSearchResponse.getLines());
            System.out.println(fileSearchResponse.getLines());
        } catch (Exception e) {
            e.printStackTrace();
            org.junit.Assert.assertFalse(true);
        }
    }

    @Test
    public void readFromDbDataSource() {
        try {
            DataBaseSearchResponse dbSearchResponse = dbDataSource.readFromDataSource();
            Assert.notEmpty(dbSearchResponse.getLines());
            System.out.println(dbSearchResponse.getLines());
        } catch (Exception e) {
            e.printStackTrace();
            org.junit.Assert.assertFalse(true);
        }
    }

    @Test
    public void readFromApiSource() {
        try {
            ApiSearchResponse apiSearchResponse = apiDataSource.readFromDataSource();
            Assert.notEmpty(apiSearchResponse.getLines());
            System.out.println(apiSearchResponse.getLines());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Ignore
    public void testParallelCalls() {
        SearchResponse searchResponse = mediaiqAggregatorApplication.searchLinesForKeyWordInAllSources("");
        System.out.println(searchResponse.getApiSearchResponse().getLines());
        System.out.println(searchResponse.getDataBaseSearchResponse().getLines());
        System.out.println(searchResponse.getFileSearchResponse().getLines());
    }
}
