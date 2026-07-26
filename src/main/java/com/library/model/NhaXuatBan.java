package com.library.model;

public class NhaXuatBan {

    private int id;
    private String maNXB;
    private String tenNXB;
    private String diaChi;

    public NhaXuatBan() {
    }

    public NhaXuatBan(int id, String maNXB, String tenNXB, String diaChi) {
        this.id = id;
        this.maNXB = maNXB;
        this.tenNXB = tenNXB;
        this.diaChi = diaChi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaNhaXuatBan() {
        return maNXB;
    }

    public void setMaNhaXuatBan(String maNXB) {
        this.maNXB = maNXB;
    }

    public String getTenNhaXuatBan() {
        return tenNXB;
    }

    public void setTenNhaXuatBan(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return tenNXB;
    }
}