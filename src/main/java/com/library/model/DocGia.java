package com.library.model;

public class DocGia {

    private int id;
    private String maDocGia;
    private String hoTen;
    private String gioiTinh;
    private String ngaySinh;
    private String soDienThoai;
    private String email;
    private String diaChi;

    public DocGia() {
    }

    public DocGia(int id, String maDocGia, String hoTen, String gioiTinh,
            String ngaySinh, String soDienThoai,
            String email, String diaChi) {

        this.id = id;
        this.maDocGia = maDocGia;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMaDocGia() { return maDocGia; }
    public void setMaDocGia(String maDocGia) { this.maDocGia = maDocGia; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public String getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(String ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    @Override
    public String toString() {
        return hoTen;
    }
}