package com.mediaiq.aggregator.service;

import com.mediaiq.aggregator.dto.SearchResponse;

public interface SearchService {

    SearchResponse searchLinesForKeyWord(String keyword);

}
