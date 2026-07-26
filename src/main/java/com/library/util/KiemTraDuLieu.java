package com.library.util;

public class KiemTraDuLieu {

    public static boolean rong(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean emailHopLe(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean soDienThoaiHopLe(String sdt) {
        return sdt.matches("\\d{10}");
    }

}