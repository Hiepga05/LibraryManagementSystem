package com.library.controller;

import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.PhieuPhat;
import com.library.service.PhieuPhatService;

import java.util.List;

public class PhieuPhatController {

    private final PhieuPhatService service;

    public PhieuPhatController() {
        service = new PhieuPhatService();
    }

    public boolean them(PhieuPhat phieuPhat) throws LoiKiemTra {
        return service.them(phieuPhat);
    }

    public boolean sua(PhieuPhat phieuPhat) throws LoiKiemTra {
        return service.sua(phieuPhat);
    }

    public boolean xoa(int id) throws KhongTimThay {
        return service.xoa(id);
    }

    public boolean thanhToan(PhieuPhat phieuPhat) {
        return service.thanhToan(phieuPhat);
    }

    public double tinhPhatTraMuon(String hanTra, String ngayTra) {
        return service.tinhPhatTraMuon(hanTra, ngayTra);
    }

    public double tinhPhatHongSach(double giaSach) {
        return service.tinhPhatHongSach(giaSach);
    }

    public double tinhPhatMatSach(double giaSach) {
        return service.tinhPhatMatSach(giaSach);
    }

    public PhieuPhat timTheoId(int id) {
        return service.timTheoId(id);
    }

    public PhieuPhat timTheoMa(String ma) {
        return service.timTheoMa(ma);
    }

    public List<PhieuPhat> layTatCa() {
        return service.layTatCa();
    }

}