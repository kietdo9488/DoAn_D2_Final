package com.example.banhanggiadung.model;

public class Giohang {
    public int idsp;
    public String tensp;
    public long giasp;
    public String hinhsp;
    public int soluongsp;

    public Giohang(int idsp, String tensp, long giasp, String hinhsp, int soluongsp) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.hinhsp = hinhsp;
        this.soluongsp = soluongsp;
    }

    public int getIdsp() {
        return idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public long getGiasp() {
        return giasp;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public void setGiasp(long giasp) {
        this.giasp = giasp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }
}
