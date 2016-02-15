package com.mediaiq.aggregator.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mediaiq.aggregator.concurrent.UnifiedResultParallelExecutor;
import com.mediaiq.aggregator.concurrent.UnifiedResultParallelExecutorFactory;
import com.mediaiq.aggregator.dto.SearchResponse;
import com.mediaiq.sources.dto.ApiSearchResponse;
import com.mediaiq.sources.dto.DataBaseSearchResponse;
import com.mediaiq.sources.dto.FileSearchResponse;
import com.mediaiq.sources.service.DataSource;

@Service
@Transactional(readOnly = true)
public class SearchServiceImpl implements SearchService {

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
    UnifiedResultParallelExecutorFactory unifiedResultParallelExecutorFactory;

    @Override
    public SearchResponse searchLinesForKeyWord(String keyword) {
        SearchResponse searchResponse = new SearchResponse();
        UnifiedResultParallelExecutor executor = unifiedResultParallelExecutorFactory.getExecutor();
        try {
            executor.submit(() -> fileDataSource.readFromDataSource());
            executor.submit(() -> dbDataSource.readFromDataSource());
            executor.submit(() -> apiDataSource.readFromDataSource());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            List<Object> unifiedResult = executor.getUnifiedResult();
            for (Object object : unifiedResult) {
                if (object instanceof ApiSearchResponse) {
                    searchResponse.setApiSearchResponse((ApiSearchResponse) object);
                } else if (object instanceof DataBaseSearchResponse) {
                    searchResponse.setDataBaseSearchResponse((DataBaseSearchResponse) object);
                } else if (object instanceof FileSearchResponse) {
                    searchResponse.setFileSearchResponse((FileSearchResponse) object);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return filterLinesBySearchKey(keyword, searchResponse);
    }

    private SearchResponse filterLinesBySearchKey(String keyword, SearchResponse searchResponse) {
        if (keyword.isEmpty()) {
            return searchResponse;
        }
        SearchResponse filteredSearchResponse = new SearchResponse();
        ApiSearchResponse filteredApiSearchResponse = new ApiSearchResponse();
        DataBaseSearchResponse filteredDataBaseSearchResponse = new DataBaseSearchResponse();
        FileSearchResponse filteredFileSearchResponse = new FileSearchResponse();
        filteredApiSearchResponse.setLines(searchResponse.getApiSearchResponse().getLines().stream()
                .filter(str -> str.trim().toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList()));
        filteredDataBaseSearchResponse.setLines(searchResponse.getDataBaseSearchResponse().getLines().stream()
                .filter(str -> str.trim().toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList()));
        filteredFileSearchResponse.setLines(searchResponse.getFileSearchResponse().getLines().stream()
                .filter(str -> str.trim().toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList()));

        filteredSearchResponse.setApiSearchResponse(filteredApiSearchResponse);
        filteredSearchResponse.setDataBaseSearchResponse(filteredDataBaseSearchResponse);
        filteredSearchResponse.setFileSearchResponse(filteredFileSearchResponse);
        return filteredSearchResponse;

    }
}
