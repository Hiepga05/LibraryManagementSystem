package com.library.service;

import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.DocGia;
import com.library.model.MuonTra;
import com.library.model.Sach;
import com.library.repository.DocGiaRepository;
import com.library.repository.MuonTraRepository;
import com.library.repository.SachRepository;
import com.library.util.KiemTraDuLieu;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class MuonTraService {
    private final MuonTraRepository muonTraRepository = new MuonTraRepository();
    private final SachRepository sachRepository = new SachRepository();
    private final DocGiaRepository docGiaRepository = new DocGiaRepository();

    public boolean muonSach(MuonTra mt) throws LoiKiemTra {
        if (KiemTraDuLieu.rong(mt.getMaMuon())) throw new LoiKiemTra("Ma muon khong duoc de trong.");
        if (muonTraRepository.timTheoMa(mt.getMaMuon()) != null) throw new LoiKiemTra("Ma muon da ton tai.");

        DocGia dg = docGiaRepository.timTheoId(mt.getIdDocGia());
        if (dg == null) throw new LoiKiemTra("Doc gia khong ton tai.");
        Sach sach = sachRepository.timTheoId(mt.getIdSach());
        if (sach == null) throw new LoiKiemTra("Sach khong ton tai.");
        if (sach.getSoLuongCon() <= 0) throw new LoiKiemTra("Sach da het.");

        LocalDate ngayMuon = parseDate(mt.getNgayMuon(), "Ngay muon");
        LocalDate hanTra = parseDate(mt.getHanTra(), "Han tra");
        if (hanTra.isBefore(ngayMuon)) throw new LoiKiemTra("Han tra phai sau hoac bang ngay muon.");

        mt.setNgayTra(null);
        mt.setTrangThai("Dang muon");
        if (!muonTraRepository.them(mt)) return false;

        sach.setSoLuongCon(sach.getSoLuongCon() - 1);
        if (!sachRepository.sua(sach)) {
            muonTraRepository.xoa(mt.getId());
            throw new LoiKiemTra("Khong cap nhat duoc so luong sach.");
        }
        return true;
    }

    public boolean traSach(int idMuonTra) throws LoiKiemTra {
        MuonTra mt = muonTraRepository.timTheoId(idMuonTra);
        if (mt == null) throw new LoiKiemTra("Khong tim thay phieu muon.");
        if ("Da tra".equalsIgnoreCase(mt.getTrangThai())) throw new LoiKiemTra("Phieu muon nay da duoc tra truoc do.");

        Sach sach = sachRepository.timTheoId(mt.getIdSach());
        if (sach == null) throw new LoiKiemTra("Khong tim thay sach.");

        mt.setNgayTra(LocalDate.now().toString());
        mt.setTrangThai("Da tra");
        if (!muonTraRepository.sua(mt)) return false;

        sach.setSoLuongCon(Math.min(sach.getSoLuong(), sach.getSoLuongCon() + 1));
        if (!sachRepository.sua(sach)) throw new LoiKiemTra("Khong cap nhat duoc so luong sach.");
        return true;
    }

    public boolean xoa(int id) throws KhongTimThay {
        MuonTra mt = muonTraRepository.timTheoId(id);
        if (mt == null) throw new KhongTimThay("Khong tim thay phieu muon.");
        if ("Dang muon".equalsIgnoreCase(mt.getTrangThai())) {
            throw new KhongTimThay("Khong the xoa phieu dang muon. Hay tra sach truoc.");
        }
        return muonTraRepository.xoa(id);
    }

    private LocalDate parseDate(String value, String tenTruong) throws LoiKiemTra {
        if (KiemTraDuLieu.rong(value)) throw new LoiKiemTra(tenTruong + " khong duoc de trong.");
        try { return LocalDate.parse(value); }
        catch (DateTimeParseException e) { throw new LoiKiemTra(tenTruong + " phai co dinh dang yyyy-MM-dd."); }
    }

    public MuonTra timTheoId(int id) { return muonTraRepository.timTheoId(id); }
    public MuonTra timTheoMa(String ma) { return muonTraRepository.timTheoMa(ma); }
    public List<MuonTra> layTatCa() { return muonTraRepository.layTatCa(); }
}
