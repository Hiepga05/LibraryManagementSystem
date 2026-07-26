package com.library.controller;

import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.Sach;
import com.library.service.SachService;

import java.util.List;

public class SachController {

    private final SachService service;

    public SachController() {
        service = new SachService();
    }

    public boolean them(Sach sach) throws LoiKiemTra {
        return service.them(sach);
    }

    public boolean sua(Sach sach) throws LoiKiemTra {
        return service.sua(sach);
    }

    public boolean xoa(int id) throws KhongTimThay {
        return service.xoa(id);
    }

    public Sach timTheoId(int id) {
        return service.timTheoId(id);
    }

    public Sach timTheoMa(String ma) {
        return service.timTheoMa(ma);
    }

    public List<Sach> layTatCa() {
        return service.layTatCa();
    }

}