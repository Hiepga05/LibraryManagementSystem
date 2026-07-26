package com.library.view.panel;

import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.TacGia;
import com.library.service.TacGiaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TacGiaPanel extends JPanel {

    private final TacGiaService service;

    private JTextField txtMa;
    private JTextField txtTen;
    private JTextField txtQuocTich;

    private JTable table;
    private DefaultTableModel model;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;

    private int idDangChon = -1;

    public TacGiaPanel() {

        service = new TacGiaService();

        setLayout(new BorderLayout(10,10));

        JLabel lblTitle = new JLabel("QUAN LY TAC GIA",SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial",Font.BOLD,24));
        add(lblTitle,BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(3,2,10,10));
        form.setBorder(BorderFactory.createTitledBorder("Thong tin tac gia"));

        txtMa = new JTextField();
        txtTen = new JTextField();
        txtQuocTich = new JTextField();

        form.add(new JLabel("Ma tac gia"));
        form.add(txtMa);

        form.add(new JLabel("Ten tac gia"));
        form.add(txtTen);

        form.add(new JLabel("Quoc tich"));
        form.add(txtQuocTich);

        add(form,BorderLayout.WEST);

        model = new DefaultTableModel(
                new String[]{
                        "ID",
                        "Ma",
                        "Ten tac gia",
                        "Quoc tich"
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

        table.getSelectionModel().addListSelectionListener(e->{

            if(e.getValueIsAdjusting()) return;

            int row = table.getSelectedRow();

            if(row==-1) return;

            idDangChon = Integer.parseInt(model.getValueAt(row,0).toString());

            txtMa.setText(model.getValueAt(row,1).toString());

            txtTen.setText(model.getValueAt(row,2).toString());

            txtQuocTich.setText(model.getValueAt(row,3).toString());

        });

        btnLamMoi.addActionListener(e->clearForm());

        btnThem.addActionListener(e->them());

        btnSua.addActionListener(e->sua());

        btnXoa.addActionListener(e->xoa());

    }

    private void loadTable(){

        model.setRowCount(0);

        List<TacGia> ds = service.layTatCa();

        for(TacGia tg:ds){

            model.addRow(new Object[]{
                    tg.getId(),
                    tg.getMaTacGia(),
                    tg.getTenTacGia(),
                    tg.getQuocTich()
            });

        }

    }

    private void clearForm(){

        idDangChon=-1;

        txtMa.setText("");

        txtTen.setText("");

        txtQuocTich.setText("");

        table.clearSelection();

    }
    
        private void them() {

        try {

            TacGia tg = new TacGia();

            tg.setMaTacGia(txtMa.getText().trim());
            tg.setTenTacGia(txtTen.getText().trim());
            tg.setQuocTich(txtQuocTich.getText().trim());

            if (service.them(tg)) {

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

            JOptionPane.showMessageDialog(this, "Chon tac gia can sua.");
            return;

        }

        try {

            TacGia tg = new TacGia();

            tg.setId(idDangChon);
            tg.setMaTacGia(txtMa.getText().trim());
            tg.setTenTacGia(txtTen.getText().trim());
            tg.setQuocTich(txtQuocTich.getText().trim());

            if (service.sua(tg)) {

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

            JOptionPane.showMessageDialog(this, "Chon tac gia can xoa.");
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