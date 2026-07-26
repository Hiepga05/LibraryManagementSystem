package com.library.view.panel;

import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.TheLoai;
import com.library.service.TheLoaiService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TheLoaiPanel extends JPanel {

    private final TheLoaiService service;

    private JTextField txtMa;
    private JTextField txtTen;
    private JTextArea txtMoTa;

    private JTable table;
    private DefaultTableModel model;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;

    private int idDangChon = -1;

    public TheLoaiPanel() {

        service = new TheLoaiService();

        setLayout(new BorderLayout(10,10));

        JLabel lblTitle = new JLabel(
                "QUAN LY THE LOAI",
                SwingConstants.CENTER
        );

        lblTitle.setFont(new Font("Arial",Font.BOLD,24));

        add(lblTitle,BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());

        form.setBorder(
                BorderFactory.createTitledBorder("Thong tin the loai")
        );

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtMa = new JTextField(15);
        txtTen = new JTextField(15);
        txtMoTa = new JTextArea(4,15);

        gbc.gridx=0;
        gbc.gridy=0;
        form.add(new JLabel("Ma the loai"),gbc);

        gbc.gridx=1;
        form.add(txtMa,gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        form.add(new JLabel("Ten the loai"),gbc);

        gbc.gridx=1;
        form.add(txtTen,gbc);

        gbc.gridx=0;
        gbc.gridy=2;
        gbc.anchor=GridBagConstraints.NORTH;

        form.add(new JLabel("Mo ta"),gbc);

        gbc.gridx=1;

        form.add(new JScrollPane(txtMoTa),gbc);

        add(form,BorderLayout.WEST);

        model = new DefaultTableModel(
                new String[]{
                        "ID",
                        "Ma",
                        "Ten the loai",
                        "Mo ta"
                },0){

            @Override
            public boolean isCellEditable(
                    int row,
                    int column){

                return false;

            }

        };

        table = new JTable(model);

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        add(new JScrollPane(table),BorderLayout.CENTER);

        JPanel bottom = new JPanel();

        btnThem = new JButton("Them");
        btnSua = new JButton("Sua");
        btnXoa = new JButton("Xoa");
        btnLamMoi = new JButton("Lam moi");

        bottom.add(btnThem);
        bottom.add(btnSua);
        bottom.add(btnXoa);
        bottom.add(btnLamMoi);

        add(bottom,BorderLayout.SOUTH);

        loadTable();
        
                table.getSelectionModel().addListSelectionListener(e -> {

            if (e.getValueIsAdjusting()) return;

            int row = table.getSelectedRow();

            if (row == -1) return;

            idDangChon = Integer.parseInt(model.getValueAt(row, 0).toString());

            txtMa.setText(model.getValueAt(row, 1).toString());
            txtTen.setText(model.getValueAt(row, 2).toString());
            txtMoTa.setText(model.getValueAt(row, 3).toString());

        });

        btnLamMoi.addActionListener(e -> clearForm());

        btnThem.addActionListener(e -> them());

        btnSua.addActionListener(e -> sua());

        btnXoa.addActionListener(e -> xoa());

    }

    private void loadTable() {

        model.setRowCount(0);

        List<TheLoai> ds = service.layTatCa();

        for (TheLoai tl : ds) {

            model.addRow(new Object[]{
                    tl.getId(),
                    tl.getMaTheLoai(),
                    tl.getTenTheLoai(),
                    tl.getMoTa()
            });

        }

    }

    private void clearForm() {

        idDangChon = -1;

        txtMa.setText("");
        txtTen.setText("");
        txtMoTa.setText("");

        table.clearSelection();

    }
    
        private void them() {

        try {

            TheLoai tl = new TheLoai();

            tl.setMaTheLoai(txtMa.getText().trim());
            tl.setTenTheLoai(txtTen.getText().trim());
            tl.setMoTa(txtMoTa.getText().trim());

            if (service.them(tl)) {

                JOptionPane.showMessageDialog(this, "Them thanh cong.");

                loadTable();
                clearForm();

            } else {

                JOptionPane.showMessageDialog(this, "Them that bai.");

            }

        } catch (LoiKiemTra ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage());

        }

    }

    private void sua() {

        if (idDangChon == -1) {

            JOptionPane.showMessageDialog(this, "Chon the loai can sua.");
            return;

        }

        try {

            TheLoai tl = new TheLoai();

            tl.setId(idDangChon);
            tl.setMaTheLoai(txtMa.getText().trim());
            tl.setTenTheLoai(txtTen.getText().trim());
            tl.setMoTa(txtMoTa.getText().trim());

            if (service.sua(tl)) {

                JOptionPane.showMessageDialog(this, "Sua thanh cong.");

                loadTable();
                clearForm();

            } else {

                JOptionPane.showMessageDialog(this, "Sua that bai.");

            }

        } catch (LoiKiemTra ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage());

        }

    }

    private void xoa() {

        if (idDangChon == -1) {

            JOptionPane.showMessageDialog(this, "Chon the loai can xoa.");
            return;

        }

        int chon = JOptionPane.showConfirmDialog(
                this,
                "Ban co chac chan muon xoa?",
                "Xac nhan",
                JOptionPane.YES_NO_OPTION
        );

        if (chon != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            if (service.xoa(idDangChon)) {

                JOptionPane.showMessageDialog(this, "Xoa thanh cong.");

                loadTable();
                clearForm();

            } else {

                JOptionPane.showMessageDialog(this, "Xoa that bai.");

            }

        } catch (KhongTimThay ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage());

        }

    }

}