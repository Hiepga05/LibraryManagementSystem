package com.library.repository;

import com.library.database.DBConnection;
import com.library.model.Sach;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SachRepository implements Repository<Sach> {

    @Override
    public boolean them(Sach sach) {

        if (timTheoMa(sach.getMaSach()) != null) {
            System.out.println("Ma sach da ton tai.");
            return false;
        }

        String sql = """
            INSERT INTO sach(
                maSach,
                tenSach,
                idTacGia,
                idTheLoai,
                idNXB,
                namXuatBan,
                gia,
                soLuong,
                soLuongCon,
                viTri
            )
            VALUES(?,?,?,?,?,?,?,?,?,?)
            """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sach.getMaSach());
            ps.setString(2, sach.getTenSach());
            ps.setInt(3, sach.getIdTacGia());
            ps.setInt(4, sach.getIdTheLoai());
            ps.setInt(5, sach.getIdNXB());
            ps.setInt(6, sach.getNamXuatBan());
            ps.setDouble(7, sach.getGia());
            ps.setInt(8, sach.getSoLuong());
            ps.setInt(9, sach.getSoLuongCon());
            ps.setString(10, sach.getViTri());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean sua(Sach sach) {

        String sql = """
            UPDATE sach
            SET maSach=?,
                tenSach=?,
                idTacGia=?,
                idTheLoai=?,
                idNXB=?,
                namXuatBan=?,
                gia=?,
                soLuong=?,
                soLuongCon=?,
                viTri=?
            WHERE id=?
            """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sach.getMaSach());
            ps.setString(2, sach.getTenSach());
            ps.setInt(3, sach.getIdTacGia());
            ps.setInt(4, sach.getIdTheLoai());
            ps.setInt(5, sach.getIdNXB());
            ps.setInt(6, sach.getNamXuatBan());
            ps.setDouble(7, sach.getGia());
            ps.setInt(8, sach.getSoLuong());
            ps.setInt(9, sach.getSoLuongCon());
            ps.setString(10, sach.getViTri());
            ps.setInt(11, sach.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean xoa(int id) {

        String sql = "DELETE FROM sach WHERE id=?";

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
    public Sach timTheoId(int id) {

        String sql = "SELECT * FROM sach WHERE id=?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Sach(
                        rs.getInt("id"),
                        rs.getString("maSach"),
                        rs.getString("tenSach"),
                        rs.getInt("idTacGia"),
                        rs.getInt("idTheLoai"),
                        rs.getInt("idNXB"),
                        rs.getInt("namXuatBan"),
                        rs.getDouble("gia"),
                        rs.getInt("soLuong"),
                        rs.getInt("soLuongCon"),
                        rs.getString("viTri")
                );
            }

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return null;
    }
    
        @Override
    public Sach timTheoMa(String ma) {

        String sql = "SELECT * FROM sach WHERE maSach=?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ma);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Sach(
                        rs.getInt("id"),
                        rs.getString("maSach"),
                        rs.getString("tenSach"),
                        rs.getInt("idTacGia"),
                        rs.getInt("idTheLoai"),
                        rs.getInt("idNXB"),
                        rs.getInt("namXuatBan"),
                        rs.getDouble("gia"),
                        rs.getInt("soLuong"),
                        rs.getInt("soLuongCon"),
                        rs.getString("viTri")
                );
            }

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Sach> layTatCa() {

        List<Sach> danhSach = new ArrayList<>();

        String sql = "SELECT * FROM sach ORDER BY id";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                danhSach.add(new Sach(
                        rs.getInt("id"),
                        rs.getString("maSach"),
                        rs.getString("tenSach"),
                        rs.getInt("idTacGia"),
                        rs.getInt("idTheLoai"),
                        rs.getInt("idNXB"),
                        rs.getInt("namXuatBan"),
                        rs.getDouble("gia"),
                        rs.getInt("soLuong"),
                        rs.getInt("soLuongCon"),
                        rs.getString("viTri")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return danhSach;
    }
}