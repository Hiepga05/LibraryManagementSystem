package com.library.controller;

import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.NhaXuatBan;
import com.library.service.NhaXuatBanService;

import java.util.List;

public class NhaXuatBanController {

    private final NhaXuatBanService service;

    public NhaXuatBanController() {
        service = new NhaXuatBanService();
    }

    public boolean them(NhaXuatBan nhaXuatBan) throws LoiKiemTra {
        return service.them(nhaXuatBan);
    }

    public boolean sua(NhaXuatBan nhaXuatBan) throws LoiKiemTra {
        return service.sua(nhaXuatBan);
    }

    public boolean xoa(int id) throws KhongTimThay {
        return service.xoa(id);
    }

    public NhaXuatBan timTheoId(int id) {
        return service.timTheoId(id);
    }

    public NhaXuatBan timTheoMa(String ma) {
        return service.timTheoMa(ma);
    }

    public List<NhaXuatBan> layTatCa() {
        return service.layTatCa();
    }

}