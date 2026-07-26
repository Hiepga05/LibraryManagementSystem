package com.library.repository;

import com.library.database.DBConnection;
import com.library.model.TacGia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class TacGiaRepository implements Repository<TacGia> {

    @Override
    public boolean them(TacGia tacGia) {

        if (timTheoMa(tacGia.getMaTacGia()) != null) {
            System.out.println("Ma tac gia da ton tai.");
            return false;
        }

        String sql = """
                INSERT INTO tacGia(maTacGia, tenTacGia, quocTich)
                VALUES (?, ?, ?)
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tacGia.getMaTacGia());
            ps.setString(2, tacGia.getTenTacGia());
            ps.setString(3, tacGia.getQuocTich());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean sua(TacGia tacGia) {

        String sql = """
                UPDATE tacGia
                SET maTacGia = ?, tenTacGia = ?, quocTich = ?
                WHERE id = ?
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tacGia.getMaTacGia());
            ps.setString(2, tacGia.getTenTacGia());
            ps.setString(3, tacGia.getQuocTich());
            ps.setInt(4, tacGia.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean xoa(int id) {

        String sql = "DELETE FROM tacGia WHERE id = ?";

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
    public TacGia timTheoId(int id) {

        String sql = "SELECT * FROM tacGia WHERE id = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new TacGia(
                        rs.getInt("id"),
                        rs.getString("maTacGia"),
                        rs.getString("tenTacGia"),
                        rs.getString("quocTich")
                );
            }

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return null;
    }

    @Override
    public TacGia timTheoMa(String ma) {

        String sql = "SELECT * FROM tacGia WHERE maTacGia = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ma);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new TacGia(
                        rs.getInt("id"),
                        rs.getString("maTacGia"),
                        rs.getString("tenTacGia"),
                        rs.getString("quocTich")
                );
            }

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<TacGia> layTatCa() {

        List<TacGia> danhSach = new ArrayList<>();

        String sql = "SELECT * FROM tacGia";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                danhSach.add(new TacGia(
                        rs.getInt("id"),
                        rs.getString("maTacGia"),
                        rs.getString("tenTacGia"),
                        rs.getString("quocTich")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return danhSach;
    }
}