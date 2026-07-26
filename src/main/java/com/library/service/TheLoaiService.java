package com.library.service;

import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.TheLoai;
import com.library.repository.TheLoaiRepository;
import com.library.util.KiemTraDuLieu;

import java.util.List;

public class TheLoaiService {

    private final TheLoaiRepository repository;

    public TheLoaiService() {
        repository = new TheLoaiRepository();
    }

    public boolean them(TheLoai loaiSach) throws LoiKiemTra {

        if (KiemTraDuLieu.rong(loaiSach.getMaTheLoai())) {
            throw new LoiKiemTra("Ma the loai khong duoc de trong.");
        }

        if (KiemTraDuLieu.rong(loaiSach.getTenTheLoai())) {
            throw new LoiKiemTra("Ten the loai khong duoc de trong.");
        }

        return repository.them(loaiSach);
    }

    public boolean sua(TheLoai loaiSach) throws LoiKiemTra {

        if (loaiSach.getId() <= 0) {
            throw new LoiKiemTra("Id khong hop le.");
        }

        if (KiemTraDuLieu.rong(loaiSach.getTenTheLoai())) {
            throw new LoiKiemTra("Ten the loai khong duoc de trong.");
        }

        return repository.sua(loaiSach);
    }

    public boolean xoa(int id) throws KhongTimThay {

        if (repository.timTheoId(id) == null) {
            throw new KhongTimThay("Khong tim thay the loai.");
        }

        return repository.xoa(id);
    }

    public TheLoai timTheoMa(String ma) {
        return repository.timTheoMa(ma);
    }

    public List<TheLoai> layTatCa() {
        return repository.layTatCa();
    }

    public TheLoai timTheoId(int id) {
        return repository.timTheoId(id);
    }

}