package com.mediaiq.sources.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "datasource")
public class DataSourceEntity implements Serializable {

    private static final long serialVersionUID = -3508925402394442813L;

    @Id
    @Column(name = "datasource_id")
    private Long dataSourceId;

    private String data;

    // @Column(name = "created_time", nullable = false, updatable = false)
    // private Date created;
    //
    // @Column(name = "lastmodified_time", nullable = false)
    // private Date updated;
    //
    // @PrePersist
    // protected void onCreate() {
    // updated = created = new Date();
    // }
    //
    // @PreUpdate
    // protected void onUpdate() {
    // updated = new Date();
    // }

    public Long getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(Long dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
