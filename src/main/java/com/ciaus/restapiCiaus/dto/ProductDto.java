package com.ciaus.restapiCiaus.dto;

public class ProductDto {
    private int id;
    private String name;

    public ProductDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductDto(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
