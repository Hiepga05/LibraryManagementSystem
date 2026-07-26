package com.library.repository;

import com.library.database.DBConnection;
import com.library.model.TheLoai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiRepository implements Repository<TheLoai> {

    @Override
    public boolean them(TheLoai theLoai) {

        String sql = """
                INSERT INTO theLoai(maTheLoai, tenTheLoai, moTa)
                VALUES (?, ?, ?)
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, theLoai.getMaTheLoai());
            ps.setString(2, theLoai.getTenTheLoai());
            ps.setString(3, theLoai.getMoTa());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE")) {
                System.out.println("Ma the loai da ton tai.");
            } else {
                System.out.println("Loi: " + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean sua(TheLoai theLoai) {

        String sql = """
                UPDATE theLoai
                SET maTheLoai = ?,
                    tenTheLoai = ?,
                    moTa = ?
                WHERE id = ?
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, theLoai.getMaTheLoai());
            ps.setString(2, theLoai.getTenTheLoai());
            ps.setString(3, theLoai.getMoTa());
            ps.setInt(4, theLoai.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean xoa(int id) {

        String sql = "DELETE FROM theLoai WHERE id = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public TheLoai timTheoId(int id) {

        String sql = "SELECT * FROM theLoai WHERE id = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new TheLoai(
                        rs.getInt("id"),
                        rs.getString("maTheLoai"),
                        rs.getString("tenTheLoai"),
                        rs.getString("moTa")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    @Override
    public TheLoai timTheoMa(String ma) {

        String sql = "SELECT * FROM theLoai WHERE maTheLoai = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ma);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new TheLoai(
                        rs.getInt("id"),
                        rs.getString("maTheLoai"),
                        rs.getString("tenTheLoai"),
                        rs.getString("moTa")
                );
            }

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

    return null;
    }

    @Override
    public List<TheLoai> layTatCa() {

        List<TheLoai> danhSach = new ArrayList<>();

        String sql = "SELECT * FROM theLoai";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                danhSach.add(new TheLoai(
                        rs.getInt("id"),
                        rs.getString("maTheLoai"),
                        rs.getString("tenTheLoai"),
                        rs.getString("moTa")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSach;
    }
}