package com.library.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initializeDatabase() {

        try (
                Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement()) {

            stmt.execute("PRAGMA foreign_keys = ON;");

            createTheLoai(stmt);
            createTacGia(stmt);
            createNhaXuatBan(stmt);
            createSach(stmt);
            createDocGia(stmt);
            createTaiKhoan(stmt);
            createTaiKhoanMacDinh(stmt);
            createMuonTra(stmt);
            createPhieuPhat(stmt);

            System.out.println("Database initialized successfully.");

        } catch (SQLException e) {
            System.out.println("Database initialization failed!");
            e.printStackTrace();
        }
    }

    private static void createTheLoai(Statement stmt) throws SQLException {

        String sql = """
            CREATE TABLE IF NOT EXISTS theLoai(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                maTheLoai TEXT UNIQUE NOT NULL,
                tenTheLoai TEXT NOT NULL,
                moTa TEXT
            );
            """;

        stmt.execute(sql);
        System.out.println("Table theLoai created.");
    }

    private static void createTacGia(Statement stmt) throws SQLException {

        String sql = """
            CREATE TABLE IF NOT EXISTS tacGia(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                maTacGia TEXT UNIQUE NOT NULL,
                tenTacGia TEXT NOT NULL,
                quocTich TEXT
            );
            """;

        stmt.execute(sql);
        System.out.println("Table tacGia created.");
    }

    private static void createNhaXuatBan(Statement stmt) throws SQLException {

        String sql = """
            CREATE TABLE IF NOT EXISTS nhaXuatBan(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                maNXB TEXT UNIQUE NOT NULL,
                tenNXB TEXT NOT NULL,
                diaChi TEXT
            );
            """;

        stmt.execute(sql);
        System.out.println("Table nhaXuatBan created.");
    }

    private static void createSach(Statement stmt) throws SQLException {

        String sql = """
            CREATE TABLE IF NOT EXISTS sach(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                maSach TEXT UNIQUE NOT NULL,
                tenSach TEXT NOT NULL,

                idTacGia INTEGER NOT NULL,
                idTheLoai INTEGER NOT NULL,
                idNXB INTEGER NOT NULL,

                namXuatBan INTEGER,
                gia REAL,

                soLuong INTEGER NOT NULL,
                soLuongCon INTEGER NOT NULL,

                viTri TEXT,

                FOREIGN KEY(idTacGia)
                    REFERENCES tacGia(id)
                    ON UPDATE CASCADE
                    ON DELETE RESTRICT,

                FOREIGN KEY(idTheLoai)
                    REFERENCES theLoai(id)
                    ON UPDATE CASCADE
                    ON DELETE RESTRICT,

                FOREIGN KEY(idNXB)
                    REFERENCES nhaXuatBan(id)
                    ON UPDATE CASCADE
                    ON DELETE RESTRICT
            );
            """;

        stmt.execute(sql);
        System.out.println("Table sach created.");
    }
    
    private static void createDocGia(Statement stmt) throws SQLException {

        String sql = """
            CREATE TABLE IF NOT EXISTS docGia(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                maDocGia TEXT UNIQUE NOT NULL,
                hoTen TEXT NOT NULL,
                gioiTinh TEXT,
                ngaySinh TEXT,
                soDienThoai TEXT,
                email TEXT,
                diaChi TEXT
            );
            """;

        stmt.execute(sql);
        System.out.println("Table docGia created.");
    }

    private static void createTaiKhoan(Statement stmt) throws SQLException {

        String sql = """
            CREATE TABLE IF NOT EXISTS taiKhoan(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                tenDangNhap TEXT UNIQUE NOT NULL,
                matKhau TEXT NOT NULL,
                vaiTro TEXT NOT NULL
            );
            """;

        stmt.execute(sql);
        System.out.println("Table taiKhoan created.");
    }

    private static void createTaiKhoanMacDinh(Statement stmt) throws SQLException {

        String sql = """
            INSERT OR IGNORE INTO taiKhoan(tenDangNhap, matKhau, vaiTro)
            VALUES(
                'admin',
                '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9',
                'ADMIN'
            );
            """;

        stmt.executeUpdate(sql);
    }

    private static void createMuonTra(Statement stmt) throws SQLException {

        String sql = """
            CREATE TABLE IF NOT EXISTS muonTra(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                maMuon TEXT UNIQUE NOT NULL,

                idDocGia INTEGER NOT NULL,
                idSach INTEGER NOT NULL,

                ngayMuon TEXT NOT NULL,
                hanTra TEXT NOT NULL,
                ngayTra TEXT,

                trangThai TEXT NOT NULL,

                FOREIGN KEY(idDocGia)
                    REFERENCES docGia(id)
                    ON UPDATE CASCADE
                    ON DELETE RESTRICT,

                FOREIGN KEY(idSach)
                    REFERENCES sach(id)
                    ON UPDATE CASCADE
                    ON DELETE RESTRICT
            );
            """;

        stmt.execute(sql);
        System.out.println("Table muonTra created.");
    }
    
    private static void createPhieuPhat(Statement stmt) throws SQLException {

        String sql = """
            CREATE TABLE IF NOT EXISTS phieuPhat(
                id INTEGER PRIMARY KEY AUTOINCREMENT,

                maPhieuPhat TEXT UNIQUE NOT NULL,

                idMuonTra INTEGER NOT NULL,

                lyDo TEXT NOT NULL,

                soTien REAL NOT NULL,

                ngayLap TEXT NOT NULL,

                daThanhToan INTEGER DEFAULT 0,

                ghiChu TEXT,

                FOREIGN KEY(idMuonTra)
                    REFERENCES muonTra(id)
                    ON UPDATE CASCADE
                    ON DELETE RESTRICT
            );
            """;

        stmt.execute(sql);
        System.out.println("Table phieuPhat created.");
    }
}