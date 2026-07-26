package com.library.service;

import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.Sach;
import com.library.repository.SachRepository;
import com.library.util.KiemTraDuLieu;

import java.time.Year;
import java.util.List;

public class SachService {

    private final SachRepository repository;

    public SachService() {
        repository = new SachRepository();
    }

    public boolean them(Sach sach) throws LoiKiemTra {

        if (KiemTraDuLieu.rong(sach.getMaSach())) {
            throw new LoiKiemTra("Ma sach khong duoc de trong.");
        }

        if (KiemTraDuLieu.rong(sach.getTenSach())) {
            throw new LoiKiemTra("Ten sach khong duoc de trong.");
        }

        if (sach.getIdTacGia() <= 0) {
            throw new LoiKiemTra("Tac gia khong hop le.");
        }

        if (sach.getIdTheLoai() <= 0) {
            throw new LoiKiemTra("The loai khong hop le.");
        }

        if (sach.getIdNXB() <= 0) {
            throw new LoiKiemTra("Nha xuat ban khong hop le.");
        }

        if (sach.getNamXuatBan() > Year.now().getValue()) {
            throw new LoiKiemTra("Nam xuat ban khong hop le.");
        }

        if (sach.getGia() < 0) {
            throw new LoiKiemTra("Gia sach khong hop le.");
        }

        if (sach.getSoLuong() < 0) {
            throw new LoiKiemTra("So luong khong hop le.");
        }

        if (sach.getSoLuongCon() < 0) {
            throw new LoiKiemTra("So luong con khong hop le.");
        }

        return repository.them(sach);
    }

    public boolean sua(Sach sach) throws LoiKiemTra {

        if (sach.getId() <= 0) {
            throw new LoiKiemTra("Id khong hop le.");
        }

        if (KiemTraDuLieu.rong(sach.getTenSach())) {
            throw new LoiKiemTra("Ten sach khong duoc de trong.");
        }

        return repository.sua(sach);
    }

    public boolean xoa(int id) throws KhongTimThay {

        if (repository.timTheoId(id) == null) {
            throw new KhongTimThay("Khong tim thay sach.");
        }

        return repository.xoa(id);
    }

    public Sach timTheoId(int id) {
        return repository.timTheoId(id);
    }

    public Sach timTheoMa(String ma) {
        return repository.timTheoMa(ma);
    }

    public List<Sach> layTatCa() {
        return repository.layTatCa();
    }

}