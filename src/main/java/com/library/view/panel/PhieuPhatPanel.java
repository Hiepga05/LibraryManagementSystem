package com.library.view.panel;

import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.PhieuPhat;
import com.library.service.PhieuPhatService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PhieuPhatPanel extends JPanel {

    private final PhieuPhatService service;

    private JTextField txtMa;
    private JTextField txtIdMuonTra;
    private JTextField txtLyDo;
    private JTextField txtSoTien;
    private JTextField txtNgayLap;
    private JTextField txtDaThanhToan;
    private JTextField txtGhiChu;

    private JTable table;
    private DefaultTableModel model;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnThanhToan;
    private JButton btnLamMoi;

    private int idDangChon = -1;

    public PhieuPhatPanel() {

        service = new PhieuPhatService();

        setLayout(new BorderLayout(10,10));

        JLabel lbl = new JLabel(
                "QUAN LY PHIEU PHAT",
                SwingConstants.CENTER);

        lbl.setFont(new Font("Arial",Font.BOLD,24));

        add(lbl,BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(7,2,10,10));

        txtMa = new JTextField();
        txtIdMuonTra = new JTextField();
        txtLyDo = new JTextField();
        txtSoTien = new JTextField();
        txtNgayLap = new JTextField();
        txtDaThanhToan = new JTextField();
        txtGhiChu = new JTextField();

        form.add(new JLabel("Ma phieu"));
        form.add(txtMa);

        form.add(new JLabel("ID Muon"));
        form.add(txtIdMuonTra);

        form.add(new JLabel("Ly do"));
        form.add(txtLyDo);

        form.add(new JLabel("So tien"));
        form.add(txtSoTien);

        form.add(new JLabel("Ngay lap"));
        form.add(txtNgayLap);

        form.add(new JLabel("Da thanh toan"));
        form.add(txtDaThanhToan);

        form.add(new JLabel("Ghi chu"));
        form.add(txtGhiChu);

        add(form,BorderLayout.WEST);

        model = new DefaultTableModel(
                new String[]{
                        "ID","Ma","ID Muon","Ly do",
                        "So tien","Ngay lap",
                        "Da TT","Ghi chu"
                },0){

            @Override
            public boolean isCellEditable(int r,int c){
                return false;
            }

        };

        table = new JTable(model);

        add(new JScrollPane(table),BorderLayout.CENTER);

        JPanel bottom = new JPanel();

        btnThem = new JButton("Them");
        btnSua = new JButton("Sua");
        btnXoa = new JButton("Xoa");
        btnThanhToan = new JButton("Thanh toan");
        btnLamMoi = new JButton("Lam moi");

        bottom.add(btnThem);
        bottom.add(btnSua);
        bottom.add(btnXoa);
        bottom.add(btnThanhToan);
        bottom.add(btnLamMoi);

        add(bottom,BorderLayout.SOUTH);

        loadTable();
        
                table.getSelectionModel().addListSelectionListener(e -> {

            if (e.getValueIsAdjusting()) return;

            int row = table.getSelectedRow();

            if (row == -1) return;

            idDangChon = Integer.parseInt(model.getValueAt(row,0).toString());

            txtMa.setText(model.getValueAt(row,1).toString());
            txtIdMuonTra.setText(model.getValueAt(row,2).toString());
            txtLyDo.setText(model.getValueAt(row,3).toString());
            txtSoTien.setText(model.getValueAt(row,4).toString());
            txtNgayLap.setText(model.getValueAt(row,5).toString());
            txtDaThanhToan.setText(model.getValueAt(row,6).toString());
            txtGhiChu.setText(model.getValueAt(row,7).toString());

        });

        btnLamMoi.addActionListener(e -> clearForm());

        btnThem.addActionListener(e -> them());

        btnSua.addActionListener(e -> sua());

        btnXoa.addActionListener(e -> xoa());

        btnThanhToan.addActionListener(e -> thanhToan());

    }

    private void loadTable() {

        model.setRowCount(0);

        List<PhieuPhat> ds = service.layTatCa();

        for (PhieuPhat pp : ds) {

            model.addRow(new Object[]{
                    pp.getId(),
                    pp.getMaPhieuPhat(),
                    pp.getIdMuonTra(),
                    pp.getLyDo(),
                    pp.getSoTien(),
                    pp.getNgayLap(),
                    pp.getDaThanhToan(),
                    pp.getGhiChu()
            });

        }

    }

    private void clearForm() {

        idDangChon = -1;

        txtMa.setText("");
        txtIdMuonTra.setText("");
        txtLyDo.setText("");
        txtSoTien.setText("");
        txtNgayLap.setText("");
        txtDaThanhToan.setText("");
        txtGhiChu.setText("");

        table.clearSelection();

    }
    
        private void them() {

        try {

            PhieuPhat pp = new PhieuPhat();

            pp.setMaPhieuPhat(txtMa.getText().trim());
            pp.setIdMuonTra(Integer.parseInt(txtIdMuonTra.getText().trim()));
            pp.setLyDo(txtLyDo.getText().trim());
            pp.setSoTien(Double.parseDouble(txtSoTien.getText().trim()));
            pp.setNgayLap(txtNgayLap.getText().trim());
            pp.setDaThanhToan(Integer.parseInt(txtDaThanhToan.getText().trim()));
            pp.setGhiChu(txtGhiChu.getText().trim());

            if (service.them(pp)) {

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

            JOptionPane.showMessageDialog(this, "Chon phieu phat can sua.");
            return;

        }

        try {

            PhieuPhat pp = new PhieuPhat();

            pp.setId(idDangChon);
            pp.setMaPhieuPhat(txtMa.getText().trim());
            pp.setIdMuonTra(Integer.parseInt(txtIdMuonTra.getText().trim()));
            pp.setLyDo(txtLyDo.getText().trim());
            pp.setSoTien(Double.parseDouble(txtSoTien.getText().trim()));
            pp.setNgayLap(txtNgayLap.getText().trim());
            pp.setDaThanhToan(Integer.parseInt(txtDaThanhToan.getText().trim()));
            pp.setGhiChu(txtGhiChu.getText().trim());

            if (service.sua(pp)) {

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

            JOptionPane.showMessageDialog(this, "Chon phieu phat can xoa.");
            return;

        }

        int chon = JOptionPane.showConfirmDialog(
                this,
                "Ban co chac chan muon xoa?",
                "Xac nhan",
                JOptionPane.YES_NO_OPTION
        );

        if (chon != JOptionPane.YES_OPTION) return;

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

    private void thanhToan() {

        if (idDangChon == -1) {

            JOptionPane.showMessageDialog(this, "Chon phieu phat.");
            return;

        }

        PhieuPhat pp = service.timTheoId(idDangChon);

        if (pp != null && service.thanhToan(pp)) {

            JOptionPane.showMessageDialog(this, "Thanh toan thanh cong.");

            loadTable();
            clearForm();

        }

    }

}