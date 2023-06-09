package com.example.banhanggiadung.model;

public class Product {



    int id;
    String name;
    int price;
    String image;


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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }





    public Product() {
    }

    public Product(int id, String name, int price, String image, String description, int category_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
//        this.description = description;
//        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "Product{" +
//                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
//                ", description='" + description + '\'' +
//                ", category_id=" + category_id +
                '}';
    }

}
