package com.library.repository;

import com.library.database.DBConnection;
import com.library.model.MuonTra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class MuonTraRepository implements Repository<MuonTra> {

    @Override
    public boolean them(MuonTra muonTra) {

        if (timTheoMa(muonTra.getMaMuon()) != null) {
            System.out.println("Ma muon da ton tai.");
            return false;
        }

        String sql = """
                INSERT INTO muonTra(
                    maMuon,
                    idDocGia,
                    idSach,
                    ngayMuon,
                    hanTra,
                    ngayTra,
                    trangThai
                )
                VALUES(?,?,?,?,?,?,?)
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, muonTra.getMaMuon());
            ps.setInt(2, muonTra.getIdDocGia());
            ps.setInt(3, muonTra.getIdSach());
            ps.setString(4, muonTra.getNgayMuon());
            ps.setString(5, muonTra.getHanTra());
            ps.setString(6, muonTra.getNgayTra());
            ps.setString(7, muonTra.getTrangThai());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean sua(MuonTra muonTra) {

        String sql = """
                UPDATE muonTra
                SET maMuon=?,
                    idDocGia=?,
                    idSach=?,
                    ngayMuon=?,
                    hanTra=?,
                    ngayTra=?,
                    trangThai=?
                WHERE id=?
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, muonTra.getMaMuon());
            ps.setInt(2, muonTra.getIdDocGia());
            ps.setInt(3, muonTra.getIdSach());
            ps.setString(4, muonTra.getNgayMuon());
            ps.setString(5, muonTra.getHanTra());
            ps.setString(6, muonTra.getNgayTra());
            ps.setString(7, muonTra.getTrangThai());
            ps.setInt(8, muonTra.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean xoa(int id) {

        String sql = "DELETE FROM muonTra WHERE id=?";

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
    public MuonTra timTheoId(int id) {

        String sql = "SELECT * FROM muonTra WHERE id=?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new MuonTra(
                        rs.getInt("id"),
                        rs.getString("maMuon"),
                        rs.getInt("idDocGia"),
                        rs.getInt("idSach"),
                        rs.getString("ngayMuon"),
                        rs.getString("hanTra"),
                        rs.getString("ngayTra"),
                        rs.getString("trangThai")
                );
            }

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return null;
    }

    @Override
    public MuonTra timTheoMa(String ma) {

        String sql = "SELECT * FROM muonTra WHERE maMuon=?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ma);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new MuonTra(
                        rs.getInt("id"),
                        rs.getString("maMuon"),
                        rs.getInt("idDocGia"),
                        rs.getInt("idSach"),
                        rs.getString("ngayMuon"),
                        rs.getString("hanTra"),
                        rs.getString("ngayTra"),
                        rs.getString("trangThai")
                );
            }

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<MuonTra> layTatCa() {

        List<MuonTra> danhSach = new ArrayList<>();

        String sql = "SELECT * FROM muonTra ORDER BY id";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                danhSach.add(new MuonTra(
                        rs.getInt("id"),
                        rs.getString("maMuon"),
                        rs.getInt("idDocGia"),
                        rs.getInt("idSach"),
                        rs.getString("ngayMuon"),
                        rs.getString("hanTra"),
                        rs.getString("ngayTra"),
                        rs.getString("trangThai")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return danhSach;
    }
}