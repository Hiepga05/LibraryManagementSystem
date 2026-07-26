package com.library.model;

public class TacGia {

    private int id;
    private String maTacGia;
    private String tenTacGia;
    private String quocTich;

    public TacGia() {
    }

    public TacGia(int id, String maTacGia, String tenTacGia, String quocTich) {
        this.id = id;
        this.maTacGia = maTacGia;
        this.tenTacGia = tenTacGia;
        this.quocTich = quocTich;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(String maTacGia) {
        this.maTacGia = maTacGia;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    @Override
    public String toString() {
        return tenTacGia;
    }
}