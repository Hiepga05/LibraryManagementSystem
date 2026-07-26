package com.library.controller;

import com.library.exception.LoiKiemTra;
import com.library.model.TaiKhoan;
import com.library.service.DangNhapService;

public class DangNhapController {

    private final DangNhapService service;

    public DangNhapController() {
        service = new DangNhapService();
    }

    public TaiKhoan dangNhap(String tenDangNhap, String matKhau)
            throws LoiKiemTra {

        return service.dangNhap(tenDangNhap, matKhau);
    }

    public boolean taoTaiKhoan(TaiKhoan taiKhoan)
            throws LoiKiemTra {

        return service.taoTaiKhoan(taiKhoan);
    }
}