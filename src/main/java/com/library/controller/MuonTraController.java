package com.library.controller;

import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.MuonTra;
import com.library.service.MuonTraService;

import java.util.List;

public class MuonTraController {

    private final MuonTraService service;

    public MuonTraController() {
        service = new MuonTraService();
    }

    public boolean muonSach(MuonTra muonTra) throws LoiKiemTra {
        return service.muonSach(muonTra);
    }

    public boolean traSach(int idMuonTra) throws LoiKiemTra {
        return service.traSach(idMuonTra);
    }

    public boolean xoa(int id) throws KhongTimThay {
        return service.xoa(id);
    }

    public MuonTra timTheoId(int id) {
        return service.timTheoId(id);
    }

    public MuonTra timTheoMa(String ma) {
        return service.timTheoMa(ma);
    }

    public List<MuonTra> layTatCa() {
        return service.layTatCa();
    }

}