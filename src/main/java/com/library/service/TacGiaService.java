package com.library.service;

import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.TacGia;
import com.library.repository.TacGiaRepository;
import com.library.util.KiemTraDuLieu;

import java.util.List;

public class TacGiaService {

    private final TacGiaRepository repository;

    public TacGiaService() {
        repository = new TacGiaRepository();
    }

    public boolean them(TacGia tacGia) throws LoiKiemTra {

        if (KiemTraDuLieu.rong(tacGia.getMaTacGia())) {
            throw new LoiKiemTra("Ma tac gia khong duoc de trong.");
        }

        if (KiemTraDuLieu.rong(tacGia.getTenTacGia())) {
            throw new LoiKiemTra("Ten tac gia khong duoc de trong.");
        }

        return repository.them(tacGia);
    }

    public boolean sua(TacGia tacGia) throws LoiKiemTra {

        if (tacGia.getId() <= 0) {
            throw new LoiKiemTra("Id khong hop le.");
        }

        if (KiemTraDuLieu.rong(tacGia.getTenTacGia())) {
            throw new LoiKiemTra("Ten tac gia khong duoc de trong.");
        }

        return repository.sua(tacGia);
    }

    public boolean xoa(int id) throws KhongTimThay {

        if (repository.timTheoId(id) == null) {
            throw new KhongTimThay("Khong tim thay tac gia.");
        }

        return repository.xoa(id);
    }

    public TacGia timTheoId(int id) {
        return repository.timTheoId(id);
    }

    public TacGia timTheoMa(String ma) {
        return repository.timTheoMa(ma);
    }

    public List<TacGia> layTatCa() {
        return repository.layTatCa();
    }

}