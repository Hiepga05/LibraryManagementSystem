package com.library.service;

import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.TaiKhoan;
import com.library.repository.TaiKhoanRepository;
import com.library.util.KiemTraDuLieu;
import com.library.util.MatKhauUtil;

import java.util.List;

public class TaiKhoanService {

    private final TaiKhoanRepository repository;

    public TaiKhoanService() {
        repository = new TaiKhoanRepository();
    }

    public boolean them(TaiKhoan taiKhoan) throws LoiKiemTra {
        kiemTraThongTin(taiKhoan, true);

        if (repository.timTheoMa(taiKhoan.getTenDangNhap().trim()) != null) {
            throw new LoiKiemTra("Ten dang nhap da ton tai.");
        }

        taiKhoan.setTenDangNhap(taiKhoan.getTenDangNhap().trim());
        taiKhoan.setVaiTro(chuanHoaVaiTro(taiKhoan.getVaiTro()));
        taiKhoan.setMatKhau(MatKhauUtil.maHoa(taiKhoan.getMatKhau()));

        return repository.them(taiKhoan);
    }

    public boolean sua(TaiKhoan taiKhoan) throws LoiKiemTra, KhongTimThay {
        if (taiKhoan.getId() <= 0) {
            throw new LoiKiemTra("Id tai khoan khong hop le.");
        }

        TaiKhoan hienTai = repository.timTheoId(taiKhoan.getId());
        if (hienTai == null) {
            throw new KhongTimThay("Khong tim thay tai khoan.");
        }

        kiemTraThongTin(taiKhoan, false);

        TaiKhoan trungTen = repository.timTheoMa(taiKhoan.getTenDangNhap().trim());
        if (trungTen != null && trungTen.getId() != taiKhoan.getId()) {
            throw new LoiKiemTra("Ten dang nhap da ton tai.");
        }

        String vaiTroMoi = chuanHoaVaiTro(taiKhoan.getVaiTro());
        if ("ADMIN".equals(hienTai.getVaiTro())
                && !"ADMIN".equals(vaiTroMoi)
                && demAdmin() <= 1) {
            throw new LoiKiemTra("He thong phai con it nhat mot tai khoan ADMIN.");
        }

        taiKhoan.setTenDangNhap(taiKhoan.getTenDangNhap().trim());
        taiKhoan.setVaiTro(vaiTroMoi);

        if (KiemTraDuLieu.rong(taiKhoan.getMatKhau())) {
            taiKhoan.setMatKhau(hienTai.getMatKhau());
        } else {
            taiKhoan.setMatKhau(MatKhauUtil.maHoa(taiKhoan.getMatKhau()));
        }

        return repository.sua(taiKhoan);
    }

    public boolean xoa(int id) throws KhongTimThay, LoiKiemTra {
        TaiKhoan taiKhoan = repository.timTheoId(id);
        if (taiKhoan == null) {
            throw new KhongTimThay("Khong tim thay tai khoan.");
        }

        if ("ADMIN".equals(taiKhoan.getVaiTro()) && demAdmin() <= 1) {
            throw new LoiKiemTra("Khong the xoa tai khoan ADMIN cuoi cung.");
        }

        return repository.xoa(id);
    }

    public TaiKhoan timTheoId(int id) {
        return repository.timTheoId(id);
    }

    public TaiKhoan timTheoMa(String tenDangNhap) {
        return repository.timTheoMa(tenDangNhap);
    }

    public List<TaiKhoan> layTatCa() {
        return repository.layTatCa();
    }

    private void kiemTraThongTin(TaiKhoan taiKhoan, boolean batBuocMatKhau)
            throws LoiKiemTra {
        if (taiKhoan == null) {
            throw new LoiKiemTra("Thong tin tai khoan khong hop le.");
        }

        if (KiemTraDuLieu.rong(taiKhoan.getTenDangNhap())) {
            throw new LoiKiemTra("Ten dang nhap khong duoc de trong.");
        }

        if (taiKhoan.getTenDangNhap().trim().length() < 3) {
            throw new LoiKiemTra("Ten dang nhap phai co it nhat 3 ky tu.");
        }

        if (batBuocMatKhau && KiemTraDuLieu.rong(taiKhoan.getMatKhau())) {
            throw new LoiKiemTra("Mat khau khong duoc de trong.");
        }

        if (!KiemTraDuLieu.rong(taiKhoan.getMatKhau())
                && taiKhoan.getMatKhau().length() < 6) {
            throw new LoiKiemTra("Mat khau phai co it nhat 6 ky tu.");
        }

        chuanHoaVaiTro(taiKhoan.getVaiTro());
    }

    private String chuanHoaVaiTro(String vaiTro) throws LoiKiemTra {
        if (KiemTraDuLieu.rong(vaiTro)) {
            throw new LoiKiemTra("Vai tro khong duoc de trong.");
        }

        String ketQua = vaiTro.trim().toUpperCase();
        if (!"ADMIN".equals(ketQua) && !"USER".equals(ketQua)) {
            throw new LoiKiemTra("Vai tro chi duoc la ADMIN hoac USER.");
        }
        return ketQua;
    }

    private long demAdmin() {
        return repository.layTatCa().stream()
                .filter(tk -> "ADMIN".equalsIgnoreCase(tk.getVaiTro()))
                .count();
    }
}
