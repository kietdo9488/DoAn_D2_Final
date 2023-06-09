package com.example.banhanggiadung.model;

public class Category_Kiet {
     int id;
     String category_name;
     String category_img;

    public Category_Kiet(int id, String category_name, String category_img) {
        this.id = id;
        this.category_name = category_name;
        this.category_img = category_img;
    }

    public Category_Kiet() {

    }

    @Override
    public String toString() {
        return "Category_Kiet{" +
                "id=" + id +
                ", category_name='" + category_name + '\'' +
                ", category_img=" + category_img +
                '}';
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

    public String getCategory_img() {
        return category_img;
    }

    public void setCategory_img(String category_img) {
        this.category_img = category_img;
    }
}
