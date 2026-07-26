package com.library.view.panel;

import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.DocGia;
import com.library.service.DocGiaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DocGiaPanel extends JPanel {

    private final DocGiaService service;

    private JTextField txtMa;
    private JTextField txtTen;
    private JTextField txtNgaySinh;
    private JTextField txtSDT;
    private JTextField txtEmail;
    private JTextField txtDiaChi;
    private JComboBox<String> cboGioiTinh;

    private JTable table;
    private DefaultTableModel model;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;

    private int idDangChon = -1;

    public DocGiaPanel() {

        service = new DocGiaService();

        setLayout(new BorderLayout(10,10));

        JLabel lblTitle = new JLabel(
                "QUAN LY DOC GIA",
                SwingConstants.CENTER);

        lblTitle.setFont(new Font("Arial",Font.BOLD,24));

        add(lblTitle,BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(7,2,10,10));

        form.setBorder(BorderFactory.createTitledBorder("Thong tin doc gia"));

        txtMa = new JTextField();
        txtTen = new JTextField();
        txtNgaySinh = new JTextField();
        txtSDT = new JTextField();
        txtEmail = new JTextField();
        txtDiaChi = new JTextField();

        cboGioiTinh = new JComboBox<>(
                new String[]{"Nam","Nu"}
        );

        form.add(new JLabel("Ma doc gia"));
        form.add(txtMa);

        form.add(new JLabel("Ho ten"));
        form.add(txtTen);

        form.add(new JLabel("Gioi tinh"));
        form.add(cboGioiTinh);

        form.add(new JLabel("Ngay sinh"));
        form.add(txtNgaySinh);

        form.add(new JLabel("So dien thoai"));
        form.add(txtSDT);

        form.add(new JLabel("Email"));
        form.add(txtEmail);

        form.add(new JLabel("Dia chi"));
        form.add(txtDiaChi);

        add(form,BorderLayout.WEST);

        model = new DefaultTableModel(
                new String[]{
                        "ID",
                        "Ma",
                        "Ho ten",
                        "Gioi tinh",
                        "Ngay sinh",
                        "SDT",
                        "Email",
                        "Dia chi"
                },0){

            @Override
            public boolean isCellEditable(int r,int c){
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

            cboGioiTinh.setSelectedItem(model.getValueAt(row,3).toString());

            txtNgaySinh.setText(model.getValueAt(row,4).toString());
            txtSDT.setText(model.getValueAt(row,5).toString());
            txtEmail.setText(model.getValueAt(row,6).toString());
            txtDiaChi.setText(model.getValueAt(row,7).toString());

        });

        btnLamMoi.addActionListener(e -> clearForm());

        btnThem.addActionListener(e -> them());

        btnSua.addActionListener(e -> sua());

        btnXoa.addActionListener(e -> xoa());

    }

    private void loadTable(){

        model.setRowCount(0);

        List<DocGia> ds = service.layTatCa();

        for(DocGia dg : ds){

            model.addRow(new Object[]{
                    dg.getId(),
                    dg.getMaDocGia(),
                    dg.getHoTen(),
                    dg.getGioiTinh(),
                    dg.getNgaySinh(),
                    dg.getSoDienThoai(),
                    dg.getEmail(),
                    dg.getDiaChi()
            });

        }

    }

    private void clearForm(){

        idDangChon = -1;

        txtMa.setText("");
        txtTen.setText("");
        cboGioiTinh.setSelectedIndex(0);
        txtNgaySinh.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");

        table.clearSelection();

    }
    
        private void them() {

        try {

            DocGia dg = new DocGia();

            dg.setMaDocGia(txtMa.getText().trim());
            dg.setHoTen(txtTen.getText().trim());
            dg.setGioiTinh(cboGioiTinh.getSelectedItem().toString());
            dg.setNgaySinh(txtNgaySinh.getText().trim());
            dg.setSoDienThoai(txtSDT.getText().trim());
            dg.setEmail(txtEmail.getText().trim());
            dg.setDiaChi(txtDiaChi.getText().trim());

            if (service.them(dg)) {

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

            JOptionPane.showMessageDialog(this, "Chon doc gia can sua.");
            return;

        }

        try {

            DocGia dg = new DocGia();

            dg.setId(idDangChon);
            dg.setMaDocGia(txtMa.getText().trim());
            dg.setHoTen(txtTen.getText().trim());
            dg.setGioiTinh(cboGioiTinh.getSelectedItem().toString());
            dg.setNgaySinh(txtNgaySinh.getText().trim());
            dg.setSoDienThoai(txtSDT.getText().trim());
            dg.setEmail(txtEmail.getText().trim());
            dg.setDiaChi(txtDiaChi.getText().trim());

            if (service.sua(dg)) {

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

            JOptionPane.showMessageDialog(this, "Chon doc gia can xoa.");
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