package com.library.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MatKhauUtil {

    public static String maHoa(String matKhau) {

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(matKhau.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();

            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (Exception e) {
            return null;
        }

    }

}