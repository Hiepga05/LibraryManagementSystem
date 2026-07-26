package com.library.view.panel;

import com.library.controller.TaiKhoanController;
import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.TaiKhoan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TaiKhoanPanel extends JPanel {

    private final TaiKhoanController controller;
    private final DefaultTableModel tableModel;

    private JTextField txtTenDangNhap;
    private JPasswordField txtMatKhau;
    private JComboBox<String> cboVaiTro;
    private JTable table;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;

    private int idDangChon = -1;

    public TaiKhoanPanel() {
        controller = new TaiKhoanController();
        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Ten dang nhap", "Vai tro"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        khoiTaoGiaoDien();
        ganSuKien();
        loadTable();
    }

    private void khoiTaoGiaoDien() {
        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblTieuDe = new JLabel("QUAN LY TAI KHOAN", SwingConstants.CENTER);
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTieuDe, BorderLayout.NORTH);

        JPanel pnlNhap = new JPanel(new GridBagLayout());
        pnlNhap.setBorder(BorderFactory.createTitledBorder("Thong tin tai khoan"));
        pnlNhap.setPreferredSize(new Dimension(350, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtTenDangNhap = new JTextField(18);
        txtMatKhau = new JPasswordField(18);
        cboVaiTro = new JComboBox<>(new String[]{"ADMIN", "USER"});

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        pnlNhap.add(new JLabel("Ten dang nhap:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        pnlNhap.add(txtTenDangNhap, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        pnlNhap.add(new JLabel("Mat khau:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        pnlNhap.add(txtMatKhau, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        pnlNhap.add(new JLabel("Vai tro:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        pnlNhap.add(cboVaiTro, gbc);

        JLabel lblGhiChu = new JLabel("Khi sua, de trong mat khau neu khong muon doi.");
        lblGhiChu.setFont(new Font("Arial", Font.ITALIC, 11));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        pnlNhap.add(lblGhiChu, gbc);

        add(pnlNhap, BorderLayout.WEST);

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(25);
        table.getTableHeader().setReorderingAllowed(false);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel pnlNut = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 5));
        btnThem = new JButton("Them");
        btnSua = new JButton("Sua");
        btnXoa = new JButton("Xoa");
        btnLamMoi = new JButton("Lam moi");

        pnlNut.add(btnThem);
        pnlNut.add(btnSua);
        pnlNut.add(btnXoa);
        pnlNut.add(btnLamMoi);
        add(pnlNut, BorderLayout.SOUTH);
    }

    private void ganSuKien() {
        btnThem.addActionListener(e -> them());
        btnSua.addActionListener(e -> sua());
        btnXoa.addActionListener(e -> xoa());
        btnLamMoi.addActionListener(e -> lamMoi());

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                hienThiDongDangChon();
            }
        });
    }

    private void loadTable() {
        tableModel.setRowCount(0);
        List<TaiKhoan> danhSach = controller.layTatCa();
        for (TaiKhoan taiKhoan : danhSach) {
            tableModel.addRow(new Object[]{
                    taiKhoan.getId(),
                    taiKhoan.getTenDangNhap(),
                    taiKhoan.getVaiTro()
            });
        }
    }

    private void hienThiDongDangChon() {
        int dong = table.getSelectedRow();
        if (dong < 0) {
            return;
        }

        idDangChon = Integer.parseInt(tableModel.getValueAt(dong, 0).toString());
        txtTenDangNhap.setText(tableModel.getValueAt(dong, 1).toString());
        cboVaiTro.setSelectedItem(tableModel.getValueAt(dong, 2).toString());
        txtMatKhau.setText("");
    }

    private TaiKhoan layDuLieuForm() {
        return new TaiKhoan(
                idDangChon,
                txtTenDangNhap.getText().trim(),
                String.valueOf(txtMatKhau.getPassword()),
                String.valueOf(cboVaiTro.getSelectedItem())
        );
    }

    private void them() {
        try {
            TaiKhoan taiKhoan = layDuLieuForm();
            taiKhoan.setId(0);

            if (controller.them(taiKhoan)) {
                thongBao("Them tai khoan thanh cong.", JOptionPane.INFORMATION_MESSAGE);
                loadTable();
                lamMoi();
            } else {
                thongBao("Them tai khoan that bai.", JOptionPane.ERROR_MESSAGE);
            }
        } catch (LoiKiemTra ex) {
            thongBao(ex.getMessage(), JOptionPane.WARNING_MESSAGE);
        }
    }

    private void sua() {
        if (idDangChon <= 0) {
            thongBao("Hay chon tai khoan can sua.", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            if (controller.sua(layDuLieuForm())) {
                thongBao("Sua tai khoan thanh cong.", JOptionPane.INFORMATION_MESSAGE);
                loadTable();
                lamMoi();
            } else {
                thongBao("Sua tai khoan that bai.", JOptionPane.ERROR_MESSAGE);
            }
        } catch (LoiKiemTra | KhongTimThay ex) {
            thongBao(ex.getMessage(), JOptionPane.WARNING_MESSAGE);
        }
    }

    private void xoa() {
        if (idDangChon <= 0) {
            thongBao("Hay chon tai khoan can xoa.", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int luaChon = JOptionPane.showConfirmDialog(
                this,
                "Ban co chac muon xoa tai khoan nay?",
                "Xac nhan xoa",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (luaChon != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            if (controller.xoa(idDangChon)) {
                thongBao("Xoa tai khoan thanh cong.", JOptionPane.INFORMATION_MESSAGE);
                loadTable();
                lamMoi();
            } else {
                thongBao("Xoa tai khoan that bai.", JOptionPane.ERROR_MESSAGE);
            }
        } catch (KhongTimThay | LoiKiemTra ex) {
            thongBao(ex.getMessage(), JOptionPane.WARNING_MESSAGE);
        }
    }

    private void lamMoi() {
        idDangChon = -1;
        txtTenDangNhap.setText("");
        txtMatKhau.setText("");
        cboVaiTro.setSelectedItem("USER");
        table.clearSelection();
        txtTenDangNhap.requestFocus();
    }

    private void thongBao(String noiDung, int loai) {
        JOptionPane.showMessageDialog(this, noiDung, "Thong bao", loai);
    }
}
