package com.library.controller;

import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.TacGia;
import com.library.service.TacGiaService;

import java.util.List;

public class TacGiaController {

    private final TacGiaService service;

    public TacGiaController() {
        service = new TacGiaService();
    }

    public boolean them(TacGia tacGia) throws LoiKiemTra {
        return service.them(tacGia);
    }

    public boolean sua(TacGia tacGia) throws LoiKiemTra {
        return service.sua(tacGia);
    }

    public boolean xoa(int id) throws KhongTimThay {
        return service.xoa(id);
    }

    public TacGia timTheoId(int id) {
        return service.timTheoId(id);
    }

    public TacGia timTheoMa(String ma) {
        return service.timTheoMa(ma);
    }

    public List<TacGia> layTatCa() {
        return service.layTatCa();
    }

}