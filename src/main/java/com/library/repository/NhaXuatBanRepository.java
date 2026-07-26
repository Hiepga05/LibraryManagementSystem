package com.library.repository;

import com.library.database.DBConnection;
import com.library.model.NhaXuatBan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class NhaXuatBanRepository implements Repository<NhaXuatBan> {

    @Override
    public boolean them(NhaXuatBan nxb) {

        if (timTheoMa(nxb.getMaNhaXuatBan()) != null) {
            System.out.println("Ma nha xuat ban da ton tai.");
            return false;
        }

        String sql = """
                INSERT INTO nhaXuatBan(maNXB, tenNXB, diaChi)
                VALUES (?, ?, ?)
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nxb.getMaNhaXuatBan());
            ps.setString(2, nxb.getTenNhaXuatBan());
            ps.setString(3, nxb.getDiaChi());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean sua(NhaXuatBan nxb) {

        String sql = """
                UPDATE nhaXuatBan
                SET maNXB = ?,
                    tenNXB = ?,
                    diaChi = ?
                WHERE id = ?
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nxb.getMaNhaXuatBan());
            ps.setString(2, nxb.getTenNhaXuatBan());
            ps.setString(3, nxb.getDiaChi());
            ps.setInt(4, nxb.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean xoa(int id) {

        String sql = "DELETE FROM nhaXuatBan WHERE id = ?";

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
    public NhaXuatBan timTheoId(int id) {

        String sql = "SELECT * FROM nhaXuatBan WHERE id = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new NhaXuatBan(
                        rs.getInt("id"),
                        rs.getString("maNXB"),
                        rs.getString("tenNXB"),
                        rs.getString("diaChi")
                );
            }

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return null;
    }

    @Override
    public NhaXuatBan timTheoMa(String ma) {

        String sql = "SELECT * FROM nhaXuatBan WHERE maNXB = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ma);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new NhaXuatBan(
                        rs.getInt("id"),
                        rs.getString("maNXB"),
                        rs.getString("tenNXB"),
                        rs.getString("diaChi")
                );
            }

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<NhaXuatBan> layTatCa() {

        List<NhaXuatBan> danhSach = new ArrayList<>();

        String sql = "SELECT * FROM nhaXuatBan";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                danhSach.add(new NhaXuatBan(
                        rs.getInt("id"),
                        rs.getString("maNXB"),
                        rs.getString("tenNXB"),
                        rs.getString("diaChi")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return danhSach;
    }
}