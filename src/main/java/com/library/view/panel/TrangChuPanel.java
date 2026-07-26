package com.library.view.panel;

import javax.swing.*;
import java.awt.*;

public class TrangChuPanel extends JPanel {

    public TrangChuPanel() {

        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("HE THONG QUAN LY THU VIEN");

        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        lblTitle.setFont(new Font("Arial", Font.BOLD, 30));

        add(lblTitle, BorderLayout.NORTH);

        JTextArea txtThongTin = new JTextArea();

        txtThongTin.setEditable(false);

        txtThongTin.setFont(new Font("Arial", Font.PLAIN, 18));

        txtThongTin.setText("""
                Chao mung ban den voi he thong Quan Ly Thu Vien.

                Chuc nang hien co:

                - Quan ly sach
                - Quan ly the loai
                - Quan ly tac gia
                - Quan ly nha xuat ban
                - Quan ly doc gia
                - Quan ly muon tra
                - Quan ly phieu phat
                - Quan ly tai khoan

                Chon chuc nang ben trai de bat dau.
                """);

        add(new JScrollPane(txtThongTin), BorderLayout.CENTER);

    }

}