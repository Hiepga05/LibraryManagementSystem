package com.library.repository;

import com.library.database.DBConnection;
import com.library.model.TaiKhoan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class TaiKhoanRepository implements Repository<TaiKhoan> {

    @Override
    public boolean them(TaiKhoan taiKhoan) {

        if (timTheoMa(taiKhoan.getTenDangNhap()) != null) {
            System.out.println("Ten dang nhap da ton tai.");
            return false;
        }

        String sql = """
                INSERT INTO taiKhoan(
                    tenDangNhap,
                    matKhau,
                    vaiTro
                )
                VALUES(?,?,?)
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, taiKhoan.getTenDangNhap());
            ps.setString(2, taiKhoan.getMatKhau());
            ps.setString(3, taiKhoan.getVaiTro());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean sua(TaiKhoan taiKhoan) {

        String sql = """
                UPDATE taiKhoan
                SET tenDangNhap = ?,
                    matKhau = ?,
                    vaiTro = ?
                WHERE id = ?
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, taiKhoan.getTenDangNhap());
            ps.setString(2, taiKhoan.getMatKhau());
            ps.setString(3, taiKhoan.getVaiTro());
            ps.setInt(4, taiKhoan.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean xoa(int id) {

        String sql = "DELETE FROM taiKhoan WHERE id = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public TaiKhoan timTheoId(int id) {

        String sql = "SELECT * FROM taiKhoan WHERE id = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new TaiKhoan(
                        rs.getInt("id"),
                        rs.getString("tenDangNhap"),
                        rs.getString("matKhau"),
                        rs.getString("vaiTro")
                );
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public TaiKhoan timTheoMa(String tenDangNhap) {

        String sql = "SELECT * FROM taiKhoan WHERE tenDangNhap = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tenDangNhap);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new TaiKhoan(
                        rs.getInt("id"),
                        rs.getString("tenDangNhap"),
                        rs.getString("matKhau"),
                        rs.getString("vaiTro")
                );
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<TaiKhoan> layTatCa() {

        List<TaiKhoan> danhSach = new ArrayList<>();

        String sql = "SELECT * FROM taiKhoan ORDER BY id";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                danhSach.add(new TaiKhoan(
                        rs.getInt("id"),
                        rs.getString("tenDangNhap"),
                        rs.getString("matKhau"),
                        rs.getString("vaiTro")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        return danhSach;
    }

    /**
     * Dung cho chuc nang dang nhap.
     */
    public TaiKhoan dangNhap(String tenDangNhap, String matKhau) {

        String sql = """
                SELECT *
                FROM taiKhoan
                WHERE tenDangNhap = ?
                  AND matKhau = ?
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tenDangNhap);
            ps.setString(2, matKhau);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new TaiKhoan(
                        rs.getInt("id"),
                        rs.getString("tenDangNhap"),
                        rs.getString("matKhau"),
                        rs.getString("vaiTro")
                );
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        return null;
    }
}