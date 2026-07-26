package com.library.controller;

import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.TheLoai;
import com.library.service.TheLoaiService;

import java.util.List;

public class TheLoaiController {

    private final TheLoaiService service;

    public TheLoaiController() {
        service = new TheLoaiService();
    }

    public boolean them(TheLoai theLoai) throws LoiKiemTra {
        return service.them(theLoai);
    }

    public boolean sua(TheLoai theLoai) throws LoiKiemTra {
        return service.sua(theLoai);
    }

    public boolean xoa(int id) throws KhongTimThay {
        return service.xoa(id);
    }

    public TheLoai timTheoId(int id) {
        return service.timTheoId(id);
    }

    public TheLoai timTheoMa(String ma) {
        return service.timTheoMa(ma);
    }

    public List<TheLoai> layTatCa() {
        return service.layTatCa();
    }

}