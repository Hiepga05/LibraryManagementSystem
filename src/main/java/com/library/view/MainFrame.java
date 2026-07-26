package com.library.view;

import com.library.model.TaiKhoan;
import com.library.view.panel.DocGiaPanel;
import com.library.view.panel.MuonTraPanel;
import com.library.view.panel.NhaXuatBanPanel;
import com.library.view.panel.PhieuPhatPanel;
import com.library.view.panel.SachPanel;
import com.library.view.panel.TacGiaPanel;
import com.library.view.panel.TaiKhoanPanel;
import com.library.view.panel.TheLoaiPanel;
import com.library.view.panel.TrangChuPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final TaiKhoan taiKhoanDangNhap;
    private JPanel contentPanel;

    public MainFrame() {
        this(null);
    }

    public MainFrame(TaiKhoan taiKhoanDangNhap) {
        this.taiKhoanDangNhap = taiKhoanDangNhap;

        setTitle("Quan ly thu vien");
        setSize(1200, 700);
        setMinimumSize(new Dimension(1000, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel menu = new JPanel(new GridLayout(0, 1, 5, 5));
        menu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        menu.setPreferredSize(new Dimension(170, 0));

        JButton btnTrangChu = new JButton("Trang chu");
        JButton btnSach = new JButton("Sach");
        JButton btnTacGia = new JButton("Tac gia");
        JButton btnTheLoai = new JButton("The loai");
        JButton btnNXB = new JButton("Nha xuat ban");
        JButton btnDocGia = new JButton("Doc gia");
        JButton btnMuonTra = new JButton("Muon tra");
        JButton btnPhieuPhat = new JButton("Phieu phat");
        JButton btnTaiKhoan = new JButton("Tai khoan");
        JButton btnDangXuat = new JButton("Dang xuat");

        menu.add(btnTrangChu);
        menu.add(btnSach);
        menu.add(btnTacGia);
        menu.add(btnTheLoai);
        menu.add(btnNXB);
        menu.add(btnDocGia);
        menu.add(btnMuonTra);
        menu.add(btnPhieuPhat);

        boolean laAdmin = taiKhoanDangNhap == null
                || "ADMIN".equalsIgnoreCase(taiKhoanDangNhap.getVaiTro());
        if (laAdmin) {
            menu.add(btnTaiKhoan);
        }

        menu.add(btnDangXuat);
        add(menu, BorderLayout.WEST);

        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        btnTrangChu.addActionListener(e -> openPanel(new TrangChuPanel()));
        btnSach.addActionListener(e -> openPanel(new SachPanel()));
        btnTacGia.addActionListener(e -> openPanel(new TacGiaPanel()));
        btnTheLoai.addActionListener(e -> openPanel(new TheLoaiPanel()));
        btnNXB.addActionListener(e -> openPanel(new NhaXuatBanPanel()));
        btnDocGia.addActionListener(e -> openPanel(new DocGiaPanel()));
        btnMuonTra.addActionListener(e -> openPanel(new MuonTraPanel()));
        btnPhieuPhat.addActionListener(e -> openPanel(new PhieuPhatPanel()));
        btnTaiKhoan.addActionListener(e -> openPanel(new TaiKhoanPanel()));
        btnDangXuat.addActionListener(e -> dangXuat());

        openPanel(new TrangChuPanel());
    }

    private void openPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void dangXuat() {
        int luaChon = JOptionPane.showConfirmDialog(
                this,
                "Ban co muon dang xuat?",
                "Xac nhan",
                JOptionPane.YES_NO_OPTION
        );

        if (luaChon == JOptionPane.YES_OPTION) {
            dispose();
            new LoginFrame().setVisible(true);
        }
    }
}
