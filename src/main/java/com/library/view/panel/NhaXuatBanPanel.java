package com.library.view.panel;

import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.NhaXuatBan;
import com.library.service.NhaXuatBanService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class NhaXuatBanPanel extends JPanel {

    private final NhaXuatBanService service;

    private JTextField txtMa;
    private JTextField txtTen;
    private JTextField txtDiaChi;

    private JTable table;
    private DefaultTableModel model;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;

    private int idDangChon = -1;

    public NhaXuatBanPanel() {

        service = new NhaXuatBanService();

        setLayout(new BorderLayout(10,10));

        JLabel lblTitle = new JLabel(
                "QUAN LY NHA XUAT BAN",
                SwingConstants.CENTER
        );

        lblTitle.setFont(new Font("Arial",Font.BOLD,24));

        add(lblTitle,BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(3,2,10,10));
        form.setBorder(
                BorderFactory.createTitledBorder("Thong tin nha xuat ban")
        );

        txtMa = new JTextField();
        txtTen = new JTextField();
        txtDiaChi = new JTextField();

        form.add(new JLabel("Ma NXB"));
        form.add(txtMa);

        form.add(new JLabel("Ten NXB"));
        form.add(txtTen);

        form.add(new JLabel("Dia chi"));
        form.add(txtDiaChi);

        add(form,BorderLayout.WEST);

        model = new DefaultTableModel(
                new String[]{
                        "ID",
                        "Ma",
                        "Ten NXB",
                        "Dia chi"
                },0){

            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }

        };

        table = new JTable(model);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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

            idDangChon = Integer.parseInt(model.getValueAt(row,0).toString());

            txtMa.setText(model.getValueAt(row,1).toString());
            txtTen.setText(model.getValueAt(row,2).toString());
            txtDiaChi.setText(model.getValueAt(row,3).toString());

        });

        btnLamMoi.addActionListener(e -> clearForm());

        btnThem.addActionListener(e -> them());

        btnSua.addActionListener(e -> sua());

        btnXoa.addActionListener(e -> xoa());

    }

    private void loadTable(){

        model.setRowCount(0);

        List<NhaXuatBan> ds = service.layTatCa();

        for(NhaXuatBan nxb : ds){

            model.addRow(new Object[]{
                    nxb.getId(),
                    nxb.getMaNhaXuatBan(),
                    nxb.getTenNhaXuatBan(),
                    nxb.getDiaChi()
            });

        }

    }

    private void clearForm(){

        idDangChon = -1;

        txtMa.setText("");
        txtTen.setText("");
        txtDiaChi.setText("");

        table.clearSelection();

    }
    
        private void them() {

        try {

            NhaXuatBan nxb = new NhaXuatBan();

            nxb.setMaNhaXuatBan(txtMa.getText().trim());
            nxb.setTenNhaXuatBan(txtTen.getText().trim());
            nxb.setDiaChi(txtDiaChi.getText().trim());

            if (service.them(nxb)) {

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

            JOptionPane.showMessageDialog(this, "Chon nha xuat ban can sua.");
            return;

        }

        try {

            NhaXuatBan nxb = new NhaXuatBan();

            nxb.setId(idDangChon);
            nxb.setMaNhaXuatBan(txtMa.getText().trim());
            nxb.setTenNhaXuatBan(txtTen.getText().trim());
            nxb.setDiaChi(txtDiaChi.getText().trim());

            if (service.sua(nxb)) {

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

            JOptionPane.showMessageDialog(this, "Chon nha xuat ban can xoa.");
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