package com.library.model;

public class MuonTra {

    private int id;
    private String maMuon;
    private int idDocGia;
    private int idSach;
    private String ngayMuon;
    private String hanTra;
    private String ngayTra;
    private String trangThai;

    public MuonTra() {
    }

    public MuonTra(int id, String maMuon, int idDocGia, int idSach,
            String ngayMuon, String hanTra,
            String ngayTra, String trangThai) {

        this.id = id;
        this.maMuon = maMuon;
        this.idDocGia = idDocGia;
        this.idSach = idSach;
        this.ngayMuon = ngayMuon;
        this.hanTra = hanTra;
        this.ngayTra = ngayTra;
        this.trangThai = trangThai;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMaMuon() { return maMuon; }
    public void setMaMuon(String maMuon) { this.maMuon = maMuon; }

    public int getIdDocGia() { return idDocGia; }
    public void setIdDocGia(int idDocGia) { this.idDocGia = idDocGia; }

    public int getIdSach() { return idSach; }
    public void setIdSach(int idSach) { this.idSach = idSach; }

    public String getNgayMuon() { return ngayMuon; }
    public void setNgayMuon(String ngayMuon) { this.ngayMuon = ngayMuon; }

    public String getHanTra() { return hanTra; }
    public void setHanTra(String hanTra) { this.hanTra = hanTra; }

    public String getNgayTra() { return ngayTra; }
    public void setNgayTra(String ngayTra) { this.ngayTra = ngayTra; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    @Override
    public String toString() {
        return maMuon;
    }
}