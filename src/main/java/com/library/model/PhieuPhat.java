package com.library.model;

public class PhieuPhat {

    private int id;
    private String maPhieuPhat;
    private int idMuonTra;
    private String lyDo;
    private double soTien;
    private String ngayLap;
    private int daThanhToan;
    private String ghiChu;

    public PhieuPhat() {
    }

    public PhieuPhat(int id, String maPhieuPhat, int idMuonTra,
            String lyDo, double soTien, String ngayLap,
            int daThanhToan, String ghiChu) {

        this.id = id;
        this.maPhieuPhat = maPhieuPhat;
        this.idMuonTra = idMuonTra;
        this.lyDo = lyDo;
        this.soTien = soTien;
        this.ngayLap = ngayLap;
        this.daThanhToan = daThanhToan;
        this.ghiChu = ghiChu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaPhieuPhat() {
        return maPhieuPhat;
    }

    public void setMaPhieuPhat(String maPhieuPhat) {
        this.maPhieuPhat = maPhieuPhat;
    }

    public int getIdMuonTra() {
        return idMuonTra;
    }

    public void setIdMuonTra(int idMuonTra) {
        this.idMuonTra = idMuonTra;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public int getDaThanhToan() {
        return daThanhToan;
    }

    public void setDaThanhToan(int daThanhToan) {
        this.daThanhToan = daThanhToan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return maPhieuPhat;
    }
}