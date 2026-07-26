package com.library.controller;

import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.DocGia;
import com.library.service.DocGiaService;

import java.util.List;

public class DocGiaController {

    private final DocGiaService service;

    public DocGiaController() {
        service = new DocGiaService();
    }

    public boolean them(DocGia docGia) throws LoiKiemTra {
        return service.them(docGia);
    }

    public boolean sua(DocGia docGia) throws LoiKiemTra {
        return service.sua(docGia);
    }

    public boolean xoa(int id) throws KhongTimThay {
        return service.xoa(id);
    }

    public DocGia timTheoId(int id) {
        return service.timTheoId(id);
    }

    public DocGia timTheoMa(String ma) {
        return service.timTheoMa(ma);
    }

    public List<DocGia> layTatCa() {
        return service.layTatCa();
    }
}