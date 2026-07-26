package com.library.view;

import com.library.controller.DangNhapController;
import com.library.exception.LoiKiemTra;
import com.library.model.TaiKhoan;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtTenDangNhap;
    private JPasswordField txtMatKhau;

    private JButton btnDangNhap;
    private JButton btnThoat;

    private DangNhapController controller;

    public LoginFrame() {

        controller = new DangNhapController();

        initComponents();

        setTitle("Quan ly thu vien");

        setSize(450, 280);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setResizable(false);

    }

    private void initComponents() {

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitle = new JLabel("DANG NHAP");

        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        panel.add(lblTitle, gbc);

        gbc.gridwidth = 1;

        gbc.gridy++;

        panel.add(new JLabel("Ten dang nhap"), gbc);

        gbc.gridx = 1;

        txtTenDangNhap = new JTextField(18);

        panel.add(txtTenDangNhap, gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        panel.add(new JLabel("Mat khau"), gbc);

        gbc.gridx = 1;

        txtMatKhau = new JPasswordField(18);

        panel.add(txtMatKhau, gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        btnDangNhap = new JButton("Dang nhap");

        panel.add(btnDangNhap, gbc);

        gbc.gridx = 1;

        btnThoat = new JButton("Thoat");

        panel.add(btnThoat, gbc);

        add(panel);

        btnDangNhap.addActionListener(e -> dangNhap());

        btnThoat.addActionListener(e -> System.exit(0));

    }

    private void dangNhap() {

        String tenDangNhap = txtTenDangNhap.getText();

        String matKhau = String.valueOf(txtMatKhau.getPassword());

        try {

            TaiKhoan tk = controller.dangNhap(tenDangNhap, matKhau);

            JOptionPane.showMessageDialog(this,
                    "Xin chao " + tk.getTenDangNhap());

            dispose();

            new MainFrame(tk).setVisible(true);

        } catch (LoiKiemTra ex) {

            JOptionPane.showMessageDialog(this,
                    ex.getMessage());

        }

    }

}