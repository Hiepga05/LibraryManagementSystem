package com.library.service;

import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.PhieuPhat;
import com.library.repository.PhieuPhatRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class PhieuPhatService {

    private final PhieuPhatRepository repository;

    public PhieuPhatService() {
        repository = new PhieuPhatRepository();
    }

    public boolean them(PhieuPhat phieuPhat) throws LoiKiemTra {

        if (phieuPhat.getIdMuonTra() <= 0) {
            throw new LoiKiemTra("Phieu muon khong hop le.");
        }

        if (phieuPhat.getSoTien() < 0) {
            throw new LoiKiemTra("So tien khong hop le.");
        }

        return repository.them(phieuPhat);
    }

    public boolean sua(PhieuPhat phieuPhat) throws LoiKiemTra {

        if (phieuPhat.getId() <= 0) {
            throw new LoiKiemTra("Id khong hop le.");
        }

        return repository.sua(phieuPhat);
    }

    public boolean xoa(int id) throws KhongTimThay {

        if (repository.timTheoId(id) == null) {
            throw new KhongTimThay("Khong tim thay phieu phat.");
        }

        return repository.xoa(id);
    }

    public PhieuPhat timTheoId(int id) {
        return repository.timTheoId(id);
    }

    public PhieuPhat timTheoMa(String ma) {
        return repository.timTheoMa(ma);
    }

    public List<PhieuPhat> layTatCa() {
        return repository.layTatCa();
    }

    // =======================
    // Nghiep vu tinh tien phat
    // =======================

    public double tinhPhatTraMuon(String hanTra, String ngayTra) {

        LocalDate han = LocalDate.parse(hanTra);
        LocalDate tra = LocalDate.parse(ngayTra);

        if (!tra.isAfter(han)) {
            return 0;
        }

        long soNgay = ChronoUnit.DAYS.between(han, tra);

        return soNgay * 5000; // 5.000đ / ngày
    }

    public double tinhPhatHongSach(double giaSach) {
        return giaSach * 0.5; // 50% giá sách
    }

    public double tinhPhatMatSach(double giaSach) {
        return giaSach; // 100% giá sách
    }

    public boolean thanhToan(PhieuPhat phieuPhat) {

        phieuPhat.setDaThanhToan(1);

        return repository.sua(phieuPhat);
    }
}