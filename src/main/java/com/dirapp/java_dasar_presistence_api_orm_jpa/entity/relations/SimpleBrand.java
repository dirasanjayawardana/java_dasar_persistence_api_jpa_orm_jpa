package com.dirapp.java_dasar_presistence_api_orm_jpa.entity.relations;

public class SimpleBrand {

    private String id;

    private String name;

    public SimpleBrand(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
