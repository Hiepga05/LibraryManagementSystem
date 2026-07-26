package com.library.model;

public class Sach {

    private int id;
    private String maSach;
    private String tenSach;

    private int idTacGia;
    private int idTheLoai;
    private int idNXB;

    private int namXuatBan;
    private double gia;

    private int soLuong;
    private int soLuongCon;

    private String viTri;

    public Sach() {
    }

    public Sach(int id, String maSach, String tenSach, int idTacGia,
            int idTheLoai, int idNXB, int namXuatBan, double gia,
            int soLuong, int soLuongCon, String viTri) {

        this.id = id;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.idTacGia = idTacGia;
        this.idTheLoai = idTheLoai;
        this.idNXB = idNXB;
        this.namXuatBan = namXuatBan;
        this.gia = gia;
        this.soLuong = soLuong;
        this.soLuongCon = soLuongCon;
        this.viTri = viTri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getIdTacGia() {
        return idTacGia;
    }

    public void setIdTacGia(int idTacGia) {
        this.idTacGia = idTacGia;
    }

    public int getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(int idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public int getIdNXB() {
        return idNXB;
    }

    public void setIdNXB(int idNXB) {
        this.idNXB = idNXB;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getSoLuongCon() {
        return soLuongCon;
    }

    public void setSoLuongCon(int soLuongCon) {
        this.soLuongCon = soLuongCon;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    @Override
    public String toString() {
        return tenSach;
    }
}