package com.library.service;

import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.DocGia;
import com.library.repository.DocGiaRepository;
import com.library.util.KiemTraDuLieu;

import java.util.List;

public class DocGiaService {

    private final DocGiaRepository repository;

    public DocGiaService() {
        repository = new DocGiaRepository();
    }

    public boolean them(DocGia docGia) throws LoiKiemTra {

        if (KiemTraDuLieu.rong(docGia.getMaDocGia())) {
            throw new LoiKiemTra("Ma doc gia khong duoc de trong.");
        }

        if (KiemTraDuLieu.rong(docGia.getHoTen())) {
            throw new LoiKiemTra("Ho ten khong duoc de trong.");
        }

        if (!KiemTraDuLieu.emailHopLe(docGia.getEmail())) {
            throw new LoiKiemTra("Email khong hop le.");
        }

        if (!KiemTraDuLieu.soDienThoaiHopLe(docGia.getSoDienThoai())) {
            throw new LoiKiemTra("So dien thoai khong hop le.");
        }

        return repository.them(docGia);
    }

    public boolean sua(DocGia docGia) throws LoiKiemTra {

        if (docGia.getId() <= 0) {
            throw new LoiKiemTra("Id khong hop le.");
        }

        if (KiemTraDuLieu.rong(docGia.getHoTen())) {
            throw new LoiKiemTra("Ho ten khong duoc de trong.");
        }

        return repository.sua(docGia);
    }

    public boolean xoa(int id) throws KhongTimThay {

        if (repository.timTheoId(id) == null) {
            throw new KhongTimThay("Khong tim thay doc gia.");
        }

        return repository.xoa(id);
    }

    public DocGia timTheoId(int id) {
        return repository.timTheoId(id);
    }

    public DocGia timTheoMa(String ma) {
        return repository.timTheoMa(ma);
    }

    public List<DocGia> layTatCa() {
        return repository.layTatCa();
    }
}