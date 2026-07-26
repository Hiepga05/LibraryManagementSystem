package com.library.test;

import com.library.model.Sach;
import com.library.repository.SachRepository;

public class TestSach {

    public static void main(String[] args) {

        SachRepository repo = new SachRepository();

        Sach sach = new Sach(
                0,
                "S001",
                "Lap trinh Java",
                1,      // idTacGia
                1,      // idTheLoai
                1,      // idNXB
                2026,
                150000,
                20,
                20,
                "Ke A1"
        );

        if (repo.them(sach)) {
            System.out.println("Them thanh cong.");
        } else {
            System.out.println("Them that bai.");
        }

        System.out.println();

        for (Sach s : repo.layTatCa()) {
            System.out.println(
                    s.getId() + " | "
                    + s.getMaSach() + " | "
                    + s.getTenSach() + " | "
                    + s.getIdTacGia() + " | "
                    + s.getIdTheLoai() + " | "
                    + s.getIdNXB() + " | "
                    + s.getNamXuatBan() + " | "
                    + s.getGia() + " | "
                    + s.getSoLuong() + " | "
                    + s.getSoLuongCon() + " | "
                    + s.getViTri()
            );
        }
    }
}