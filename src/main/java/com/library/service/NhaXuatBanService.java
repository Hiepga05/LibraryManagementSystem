package com.library.service;

import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.NhaXuatBan;
import com.library.repository.NhaXuatBanRepository;
import com.library.util.KiemTraDuLieu;

import java.util.List;

public class NhaXuatBanService {

    private final NhaXuatBanRepository repository;

    public NhaXuatBanService() {
        repository = new NhaXuatBanRepository();
    }

    public boolean them(NhaXuatBan nhaXuatBan) throws LoiKiemTra {

        if (KiemTraDuLieu.rong(nhaXuatBan.getMaNhaXuatBan())) {
            throw new LoiKiemTra("Ma nha xuat ban khong duoc de trong.");
        }

        if (KiemTraDuLieu.rong(nhaXuatBan.getTenNhaXuatBan())) {
            throw new LoiKiemTra("Ten nha xuat ban khong duoc de trong.");
        }

        return repository.them(nhaXuatBan);
    }

    public boolean sua(NhaXuatBan nhaXuatBan) throws LoiKiemTra {

        if (nhaXuatBan.getId() <= 0) {
            throw new LoiKiemTra("Id khong hop le.");
        }

        if (KiemTraDuLieu.rong(nhaXuatBan.getTenNhaXuatBan())) {
            throw new LoiKiemTra("Ten nha xuat ban khong duoc de trong.");
        }

        return repository.sua(nhaXuatBan);
    }

    public boolean xoa(int id) throws KhongTimThay {

        if (repository.timTheoId(id) == null) {
            throw new KhongTimThay("Khong tim thay nha xuat ban.");
        }

        return repository.xoa(id);
    }

    public NhaXuatBan timTheoId(int id) {
        return repository.timTheoId(id);
    }

    public NhaXuatBan timTheoMa(String ma) {
        return repository.timTheoMa(ma);
    }

    public List<NhaXuatBan> layTatCa() {
        return repository.layTatCa();
    }
}