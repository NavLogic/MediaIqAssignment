package com.mediaiq.sources.impl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.mediaiq.sources.dto.FileSearchResponse;
import com.mediaiq.sources.service.DataSource;

@Service("fileDataSource")
public class FileDataSource implements DataSource {

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${FILE_PATH}")
    private String fileName;

    @Override
    @SuppressWarnings("unchecked")
    public FileSearchResponse readFromDataSource() throws IOException {
        FileSearchResponse fileSearchResponse = new FileSearchResponse();
        Resource resource = resourceLoader.getResource("classpath:" + fileName);
        fileSearchResponse.setLines(Files.readAllLines(resource.getFile().toPath(), Charset.defaultCharset()));
        return fileSearchResponse;
    }

    @Override
    public void writeToDataSource(Stream<String> data) {

    }

}
