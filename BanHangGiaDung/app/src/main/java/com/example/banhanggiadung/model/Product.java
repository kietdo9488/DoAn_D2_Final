package com.example.banhanggiadung.model;

public class Product {
    public int id;
    public String tenSanPham;
    public Integer giaSanPham;
    public int hinhSanPham;
    public String moTa;
    public int IDSanPham;

    public Product(int id, String tenSanPham, Integer giaSanPham, Integer hinhSanPham, String moTa, int IDSanPham) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.hinhSanPham = hinhSanPham;
        this.moTa = moTa;
        this.IDSanPham = IDSanPham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Integer getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(Integer giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public int getHinhSanPham() {
        return hinhSanPham;
    }

    public void setHinhSanPham(int hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(int IDSanPham) {
        this.IDSanPham = IDSanPham;
    }
}
