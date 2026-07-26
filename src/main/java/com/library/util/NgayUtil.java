package com.library.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NgayUtil {

    private static final DateTimeFormatter DINH_DANG =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String homNay() {
        return LocalDate.now().format(DINH_DANG);
    }

    public static LocalDate chuyenThanhNgay(String ngay) {
        return LocalDate.parse(ngay, DINH_DANG);
    }

    public static String dinhDang(LocalDate ngay) {
        return ngay.format(DINH_DANG);
    }

}