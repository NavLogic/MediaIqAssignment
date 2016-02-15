package com.mediaiq.aggregator.dto;

import com.mediaiq.sources.dto.ApiSearchResponse;
import com.mediaiq.sources.dto.DataBaseSearchResponse;
import com.mediaiq.sources.dto.FileSearchResponse;

public class SearchResponse {

    ApiSearchResponse apiSearchResponse;
    FileSearchResponse fileSearchResponse;
    DataBaseSearchResponse dataBaseSearchResponse;

    public ApiSearchResponse getApiSearchResponse() {
        return apiSearchResponse;
    }

    public void setApiSearchResponse(ApiSearchResponse apiSearchResponse) {
        this.apiSearchResponse = apiSearchResponse;
    }

    public FileSearchResponse getFileSearchResponse() {
        return fileSearchResponse;
    }

    public void setFileSearchResponse(FileSearchResponse fileSearchResponse) {
        this.fileSearchResponse = fileSearchResponse;
    }

    public DataBaseSearchResponse getDataBaseSearchResponse() {
        return dataBaseSearchResponse;
    }

    public void setDataBaseSearchResponse(DataBaseSearchResponse dataBaseSearchResponse) {
        this.dataBaseSearchResponse = dataBaseSearchResponse;
    }

    @Override
    public String toString() {
        return "SearchResponse [apiSearchResponse=" + apiSearchResponse + ", fileSearchResponse=" + fileSearchResponse
                + ", dataBaseSearchResponse=" + dataBaseSearchResponse + "]";
    }

}
