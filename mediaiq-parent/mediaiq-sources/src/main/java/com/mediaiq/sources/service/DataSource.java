package com.mediaiq.sources.service;

import java.util.stream.Stream;

import com.mediaiq.sources.dto.SearchString;

public interface DataSource {

    <T extends SearchString> T readFromDataSource() throws Exception;

    void writeToDataSource(Stream<String> data);

}
