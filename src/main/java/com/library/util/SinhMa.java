package com.library.util;

public class SinhMa {

    public static String taoMa(String tienTo, int soThuTu) {
        return String.format("%s%03d", tienTo, soThuTu);
    }

}