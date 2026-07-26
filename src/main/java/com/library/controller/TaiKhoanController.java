package com.library.controller;

import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.TaiKhoan;
import com.library.service.TaiKhoanService;

import java.util.List;

public class TaiKhoanController {

    private final TaiKhoanService service;

    public TaiKhoanController() {
        service = new TaiKhoanService();
    }

    public boolean them(TaiKhoan taiKhoan) throws LoiKiemTra {
        return service.them(taiKhoan);
    }

    public boolean sua(TaiKhoan taiKhoan) throws LoiKiemTra, KhongTimThay {
        return service.sua(taiKhoan);
    }

    public boolean xoa(int id) throws KhongTimThay, LoiKiemTra {
        return service.xoa(id);
    }

    public TaiKhoan timTheoId(int id) {
        return service.timTheoId(id);
    }

    public TaiKhoan timTheoMa(String tenDangNhap) {
        return service.timTheoMa(tenDangNhap);
    }

    public List<TaiKhoan> layTatCa() {
        return service.layTatCa();
    }
}
