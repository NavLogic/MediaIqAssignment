package com.mediaiq.sources.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mediaiq.sources.entity.DataSourceEntity;

public interface DataSourceRepository extends JpaRepository<DataSourceEntity, Long> {

}
