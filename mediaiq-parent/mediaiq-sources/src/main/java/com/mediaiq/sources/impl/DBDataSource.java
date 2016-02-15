package com.mediaiq.sources.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mediaiq.sources.dto.DataBaseSearchResponse;
import com.mediaiq.sources.entity.DataSourceEntity;
import com.mediaiq.sources.repository.DataSourceRepository;
import com.mediaiq.sources.service.DataSource;

@Service("dbDataSource")
@Transactional(readOnly = true)
public class DBDataSource implements DataSource {
    @Autowired
    private DataSourceRepository dataSourceRepository;

    @Override
    @SuppressWarnings("unchecked")
    public DataBaseSearchResponse readFromDataSource() throws Exception {
        DataBaseSearchResponse dataBaseSearchResponse = new DataBaseSearchResponse();
        List<DataSourceEntity> dataSourceEntities = dataSourceRepository.findAll();
        if (dataSourceEntities.isEmpty()) {
            System.out.println("Data source table is empty");
        }
        List<String> lines = new ArrayList<String>();
        for (DataSourceEntity dataSourceEntity : dataSourceEntities) {
            if (!dataSourceEntity.getData().isEmpty()) {
                lines.add(dataSourceEntity.getData());
            }
        }
        dataBaseSearchResponse.setLines(lines);
        return dataBaseSearchResponse;
    }

    @Override
    @Transactional(readOnly = false)
    public void writeToDataSource(Stream<String> data) {

    }

}
