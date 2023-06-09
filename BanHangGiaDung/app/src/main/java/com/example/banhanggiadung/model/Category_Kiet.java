package com.example.banhanggiadung.model;

public class Category_Kiet {
    private int id;
    private String category_name;
    private int category_image;

    public Category_Kiet(int id, String category_name, int category_image) {
        this.id = id;
        this.category_name = category_name;
        this.category_image = category_image;
    }

    public Category_Kiet() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getCategory_image() {
        return category_image;
    }

    public void setCategory_image(int category_image) {
        this.category_image = category_image;
    }
}
