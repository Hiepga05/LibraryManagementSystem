package com.library.test;

import com.library.model.TheLoai;
import com.library.repository.TheLoaiRepository;

public class TestLoaiSach {

    public static void main(String[] args) {

        TheLoaiRepository repo = new TheLoaiRepository();

        TheLoai tl = new TheLoai();

        tl.setMaTheLoai("TL001");
        tl.setTenTheLoai("Cong nghe thong tin");
        tl.setMoTa("Sach CNTT");

        if (repo.them(tl)) {
            System.out.println("Them thanh cong.");
        } else {
            System.out.println("Them that bai.");
        }

        System.out.println();

        for (TheLoai x : repo.layTatCa()) {
            System.out.println(
                    x.getId() + " | "
                    + x.getMaTheLoai() + " | "
                    + x.getTenTheLoai() + " | "
                    + x.getMoTa());
        }

    }
}