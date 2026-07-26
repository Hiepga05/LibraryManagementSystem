package com.library.repository;

import com.library.database.DBConnection;
import com.library.model.PhieuPhat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class PhieuPhatRepository implements Repository<PhieuPhat> {

    @Override
    public boolean them(PhieuPhat phieuPhat) {

        if (timTheoMa(phieuPhat.getMaPhieuPhat()) != null) {
            System.out.println("Ma phieu phat da ton tai.");
            return false;
        }

        String sql = """
                INSERT INTO phieuPhat(
                    maPhieuPhat,
                    idMuonTra,
                    lyDo,
                    soTien,
                    ngayLap,
                    daThanhToan,
                    ghiChu
                )
                VALUES(?,?,?,?,?,?,?)
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, phieuPhat.getMaPhieuPhat());
            ps.setInt(2, phieuPhat.getIdMuonTra());
            ps.setString(3, phieuPhat.getLyDo());
            ps.setDouble(4, phieuPhat.getSoTien());
            ps.setString(5, phieuPhat.getNgayLap());
            ps.setInt(6, phieuPhat.getDaThanhToan());
            ps.setString(7, phieuPhat.getGhiChu());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean sua(PhieuPhat phieuPhat) {

        String sql = """
                UPDATE phieuPhat
                SET maPhieuPhat=?,
                    idMuonTra=?,
                    lyDo=?,
                    soTien=?,
                    ngayLap=?,
                    daThanhToan=?,
                    ghiChu=?
                WHERE id=?
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, phieuPhat.getMaPhieuPhat());
            ps.setInt(2, phieuPhat.getIdMuonTra());
            ps.setString(3, phieuPhat.getLyDo());
            ps.setDouble(4, phieuPhat.getSoTien());
            ps.setString(5, phieuPhat.getNgayLap());
            ps.setInt(6, phieuPhat.getDaThanhToan());
            ps.setString(7, phieuPhat.getGhiChu());
            ps.setInt(8, phieuPhat.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean xoa(int id) {

        String sql = "DELETE FROM phieuPhat WHERE id=?";

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
    public PhieuPhat timTheoId(int id) {

        String sql = "SELECT * FROM phieuPhat WHERE id=?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new PhieuPhat(
                        rs.getInt("id"),
                        rs.getString("maPhieuPhat"),
                        rs.getInt("idMuonTra"),
                        rs.getString("lyDo"),
                        rs.getDouble("soTien"),
                        rs.getString("ngayLap"),
                        rs.getInt("daThanhToan"),
                        rs.getString("ghiChu")
                );
            }

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return null;
    }

    @Override
    public PhieuPhat timTheoMa(String ma) {

        String sql = "SELECT * FROM phieuPhat WHERE maPhieuPhat=?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ma);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new PhieuPhat(
                        rs.getInt("id"),
                        rs.getString("maPhieuPhat"),
                        rs.getInt("idMuonTra"),
                        rs.getString("lyDo"),
                        rs.getDouble("soTien"),
                        rs.getString("ngayLap"),
                        rs.getInt("daThanhToan"),
                        rs.getString("ghiChu")
                );
            }

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<PhieuPhat> layTatCa() {

        List<PhieuPhat> danhSach = new ArrayList<>();

        String sql = "SELECT * FROM phieuPhat ORDER BY id";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                danhSach.add(new PhieuPhat(
                        rs.getInt("id"),
                        rs.getString("maPhieuPhat"),
                        rs.getInt("idMuonTra"),
                        rs.getString("lyDo"),
                        rs.getDouble("soTien"),
                        rs.getString("ngayLap"),
                        rs.getInt("daThanhToan"),
                        rs.getString("ghiChu")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        return danhSach;
    }
}