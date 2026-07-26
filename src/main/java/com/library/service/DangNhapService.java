package com.library.service;

import com.library.exception.LoiKiemTra;
import com.library.model.TaiKhoan;
import com.library.repository.TaiKhoanRepository;
import com.library.util.KiemTraDuLieu;
import com.library.util.MatKhauUtil;

public class DangNhapService {

    private final TaiKhoanRepository repository;

    public DangNhapService() {
        repository = new TaiKhoanRepository();
    }

    public TaiKhoan dangNhap(String tenDangNhap, String matKhau)
            throws LoiKiemTra {

        if (KiemTraDuLieu.rong(tenDangNhap)) {
            throw new LoiKiemTra("Ten dang nhap khong duoc de trong.");
        }

        if (KiemTraDuLieu.rong(matKhau)) {
            throw new LoiKiemTra("Mat khau khong duoc de trong.");
        }

        TaiKhoan taiKhoan = repository.timTheoMa(tenDangNhap);

        if (taiKhoan == null) {
            throw new LoiKiemTra("Tai khoan khong ton tai.");
        }

        String matKhauMaHoa = MatKhauUtil.maHoa(matKhau);

        if (!taiKhoan.getMatKhau().equals(matKhauMaHoa)) {
            throw new LoiKiemTra("Mat khau khong dung.");
        }

        return taiKhoan;
    }

    public boolean taoTaiKhoan(TaiKhoan taiKhoan)
            throws LoiKiemTra {

        if (KiemTraDuLieu.rong(taiKhoan.getTenDangNhap())) {
            throw new LoiKiemTra("Ten dang nhap khong duoc de trong.");
        }

        if (KiemTraDuLieu.rong(taiKhoan.getMatKhau())) {
            throw new LoiKiemTra("Mat khau khong duoc de trong.");
        }

        taiKhoan.setMatKhau(
                MatKhauUtil.maHoa(taiKhoan.getMatKhau())
        );

        return repository.them(taiKhoan);
    }

}