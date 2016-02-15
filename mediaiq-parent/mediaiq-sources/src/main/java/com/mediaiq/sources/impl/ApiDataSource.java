package com.mediaiq.sources.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mediaiq.sources.dto.ApiSearchResponse;
import com.mediaiq.sources.service.DataSource;

@Service("apiDataSource")
public class ApiDataSource implements DataSource {

    @Value("${URL}")
    private String url;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    @SuppressWarnings("unchecked")
    public ApiSearchResponse readFromDataSource() throws Exception {
        ApiSearchResponse apiSearchResponse = new ApiSearchResponse();
        String string = restTemplate.getForObject(url, String.class);
        List<String> list = new ArrayList<String>();
        list.add(string);
        apiSearchResponse.setLines(list);
        // String theString = IOUtils.toString(resource.getInputStream(), "UTF-8");
        // Files.readAllLines(resource.getFile().toPath(), Charset.defaultCharset());
        return apiSearchResponse;
    }

    @Override
    public void writeToDataSource(Stream<String> data) {
        // TODO Auto-generated method stub

    }

}
