package com.library.repository;

import com.library.database.DBConnection;
import com.library.model.DocGia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class DocGiaRepository implements Repository<DocGia> {

    @Override
    public boolean them(DocGia docGia) {

        if (timTheoMa(docGia.getMaDocGia()) != null) {
            System.out.println("Ma doc gia da ton tai.");
            return false;
        }

        String sql = """
                INSERT INTO docGia(
                    maDocGia,
                    hoTen,
                    gioiTinh,
                    ngaySinh,
                    soDienThoai,
                    email,
                    diaChi
                )
                VALUES(?,?,?,?,?,?,?)
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, docGia.getMaDocGia());
            ps.setString(2, docGia.getHoTen());
            ps.setString(3, docGia.getGioiTinh());
            ps.setString(4, docGia.getNgaySinh());
            ps.setString(5, docGia.getSoDienThoai());
            ps.setString(6, docGia.getEmail());
            ps.setString(7, docGia.getDiaChi());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean sua(DocGia docGia) {

        String sql = """
                UPDATE docGia
                SET maDocGia = ?,
                    hoTen = ?,
                    gioiTinh = ?,
                    ngaySinh = ?,
                    soDienThoai = ?,
                    email = ?,
                    diaChi = ?
                WHERE id = ?
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, docGia.getMaDocGia());
            ps.setString(2, docGia.getHoTen());
            ps.setString(3, docGia.getGioiTinh());
            ps.setString(4, docGia.getNgaySinh());
            ps.setString(5, docGia.getSoDienThoai());
            ps.setString(6, docGia.getEmail());
            ps.setString(7, docGia.getDiaChi());
            ps.setInt(8, docGia.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean xoa(int id) {

        String sql = "DELETE FROM docGia WHERE id = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return false;
    }

    @Override
    public DocGia timTheoId(int id) {

        String sql = "SELECT * FROM docGia WHERE id = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new DocGia(
                        rs.getInt("id"),
                        rs.getString("maDocGia"),
                        rs.getString("hoTen"),
                        rs.getString("gioiTinh"),
                        rs.getString("ngaySinh"),
                        rs.getString("soDienThoai"),
                        rs.getString("email"),
                        rs.getString("diaChi")
                );
            }

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return null;
    }

    @Override
    public DocGia timTheoMa(String ma) {

        String sql = "SELECT * FROM docGia WHERE maDocGia = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ma);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new DocGia(
                        rs.getInt("id"),
                        rs.getString("maDocGia"),
                        rs.getString("hoTen"),
                        rs.getString("gioiTinh"),
                        rs.getString("ngaySinh"),
                        rs.getString("soDienThoai"),
                        rs.getString("email"),
                        rs.getString("diaChi")
                );
            }

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<DocGia> layTatCa() {

        List<DocGia> danhSach = new ArrayList<>();

        String sql = "SELECT * FROM docGia ORDER BY id";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                danhSach.add(new DocGia(
                        rs.getInt("id"),
                        rs.getString("maDocGia"),
                        rs.getString("hoTen"),
                        rs.getString("gioiTinh"),
                        rs.getString("ngaySinh"),
                        rs.getString("soDienThoai"),
                        rs.getString("email"),
                        rs.getString("diaChi")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return danhSach;
    }
}