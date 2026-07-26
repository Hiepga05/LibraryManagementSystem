package com.library.view.panel;

import com.library.controller.DocGiaController;
import com.library.controller.MuonTraController;
import com.library.controller.SachController;
import com.library.exception.KhongTimThay;
import com.library.exception.LoiKiemTra;
import com.library.model.DocGia;
import com.library.model.MuonTra;
import com.library.model.Sach;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class MuonTraPanel extends JPanel {

    private final MuonTraController controller = new MuonTraController();
    private final DocGiaController docGiaController = new DocGiaController();
    private final SachController sachController = new SachController();

    private JTextField txtMa;
    private JComboBox<DocGia> cboDocGia;
    private JComboBox<Sach> cboSach;
    private JTextField txtNgayMuon;
    private JTextField txtHanTra;
    private JTextField txtNgayTra;
    private JTextField txtTrangThai;
    private JTable table;
    private DefaultTableModel model;
    private int idDangChon = -1;

    public MuonTraPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("QUAN LY MUON TRA", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(7, 2, 8, 8));
        form.setBorder(BorderFactory.createTitledBorder("Thong tin muon tra"));

        txtMa = new JTextField();
        cboDocGia = new JComboBox<>();
        cboSach = new JComboBox<>();
        txtNgayMuon = new JTextField(LocalDate.now().toString());
        txtHanTra = new JTextField(LocalDate.now().plusDays(14).toString());
        txtNgayTra = new JTextField();
        txtTrangThai = new JTextField("Dang muon");
        txtNgayTra.setEditable(false);
        txtTrangThai.setEditable(false);

        form.add(new JLabel("Ma muon")); form.add(txtMa);
        form.add(new JLabel("Doc gia")); form.add(cboDocGia);
        form.add(new JLabel("Sach")); form.add(cboSach);
        form.add(new JLabel("Ngay muon (yyyy-MM-dd)")); form.add(txtNgayMuon);
        form.add(new JLabel("Han tra (yyyy-MM-dd)")); form.add(txtHanTra);
        form.add(new JLabel("Ngay tra")); form.add(txtNgayTra);
        form.add(new JLabel("Trang thai")); form.add(txtTrangThai);

        add(form, BorderLayout.WEST);

        model = new DefaultTableModel(new String[]{
                "ID", "Ma muon", "Ma doc gia", "Ho ten", "Ma sach", "Ten sach",
                "Ngay muon", "Han tra", "Ngay tra", "Trang thai"
        }, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(model);
        table.setRowHeight(26);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnMuon = new JButton("Muon");
        JButton btnTra = new JButton("Tra");
        JButton btnXoa = new JButton("Xoa");
        JButton btnLamMoi = new JButton("Lam moi");
        JPanel bottom = new JPanel();
        bottom.add(btnMuon); bottom.add(btnTra); bottom.add(btnXoa); bottom.add(btnLamMoi);
        add(bottom, BorderLayout.SOUTH);

        napDanhMuc();
        loadTable();

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) hienThiDongDangChon();
        });
        btnMuon.addActionListener(e -> muonSach());
        btnTra.addActionListener(e -> traSach());
        btnXoa.addActionListener(e -> xoa());
        btnLamMoi.addActionListener(e -> clearForm());
    }

    private void napDanhMuc() {
        cboDocGia.removeAllItems();
        for (DocGia dg : docGiaController.layTatCa()) cboDocGia.addItem(dg);
        cboSach.removeAllItems();
        for (Sach s : sachController.layTatCa()) cboSach.addItem(s);

        cboDocGia.setRenderer(new DefaultListCellRenderer() {
            @Override public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof DocGia dg) setText(dg.getMaDocGia() + " - " + dg.getHoTen());
                return this;
            }
        });
        cboSach.setRenderer(new DefaultListCellRenderer() {
            @Override public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Sach s) setText(s.getMaSach() + " - " + s.getTenSach() + " (con " + s.getSoLuongCon() + ")");
                return this;
            }
        });
    }

    private void loadTable() {
        model.setRowCount(0);
        for (MuonTra mt : controller.layTatCa()) {
            DocGia dg = docGiaController.timTheoId(mt.getIdDocGia());
            Sach s = sachController.timTheoId(mt.getIdSach());
            model.addRow(new Object[]{
                    mt.getId(), mt.getMaMuon(),
                    dg == null ? "" : dg.getMaDocGia(), dg == null ? "" : dg.getHoTen(),
                    s == null ? "" : s.getMaSach(), s == null ? "" : s.getTenSach(),
                    mt.getNgayMuon(), mt.getHanTra(), mt.getNgayTra(), mt.getTrangThai()
            });
        }
    }

    private void hienThiDongDangChon() {
        int row = table.getSelectedRow();
        if (row < 0) return;
        idDangChon = Integer.parseInt(model.getValueAt(row, 0).toString());
        MuonTra mt = controller.timTheoId(idDangChon);
        if (mt == null) return;
        txtMa.setText(mt.getMaMuon());
        chonTheoId(cboDocGia, mt.getIdDocGia());
        chonSachTheoId(mt.getIdSach());
        txtNgayMuon.setText(mt.getNgayMuon());
        txtHanTra.setText(mt.getHanTra());
        txtNgayTra.setText(mt.getNgayTra() == null ? "" : mt.getNgayTra());
        txtTrangThai.setText(mt.getTrangThai());
    }

    private void chonTheoId(JComboBox<DocGia> combo, int id) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i).getId() == id) { combo.setSelectedIndex(i); return; }
        }
    }

    private void chonSachTheoId(int id) {
        for (int i = 0; i < cboSach.getItemCount(); i++) {
            if (cboSach.getItemAt(i).getId() == id) { cboSach.setSelectedIndex(i); return; }
        }
    }

    private void muonSach() {
        try {
            DocGia dg = (DocGia) cboDocGia.getSelectedItem();
            Sach s = (Sach) cboSach.getSelectedItem();
            if (dg == null) throw new LoiKiemTra("Chua co doc gia. Hay them doc gia truoc.");
            if (s == null) throw new LoiKiemTra("Chua co sach. Hay them sach truoc.");

            MuonTra mt = new MuonTra();
            mt.setMaMuon(txtMa.getText().trim());
            mt.setIdDocGia(dg.getId());
            mt.setIdSach(s.getId());
            mt.setNgayMuon(txtNgayMuon.getText().trim());
            mt.setHanTra(txtHanTra.getText().trim());
            mt.setNgayTra(null);
            mt.setTrangThai("Dang muon");

            if (controller.muonSach(mt)) {
                JOptionPane.showMessageDialog(this, "Muon sach thanh cong.");
                napDanhMuc(); loadTable(); clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Muon sach that bai. Ma muon co the da ton tai.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void traSach() {
        if (idDangChon < 0) { JOptionPane.showMessageDialog(this, "Chon phieu muon can tra."); return; }
        try {
            if (controller.traSach(idDangChon)) {
                JOptionPane.showMessageDialog(this, "Tra sach thanh cong.");
                napDanhMuc(); loadTable(); clearForm();
            }
        } catch (LoiKiemTra ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void xoa() {
        if (idDangChon < 0) { JOptionPane.showMessageDialog(this, "Chon phieu muon can xoa."); return; }
        if (JOptionPane.showConfirmDialog(this, "Ban co chac muon xoa?", "Xac nhan",
                JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;
        try {
            if (controller.xoa(idDangChon)) { loadTable(); clearForm(); }
        } catch (KhongTimThay ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        idDangChon = -1;
        txtMa.setText("");
        if (cboDocGia.getItemCount() > 0) cboDocGia.setSelectedIndex(0);
        if (cboSach.getItemCount() > 0) cboSach.setSelectedIndex(0);
        txtNgayMuon.setText(LocalDate.now().toString());
        txtHanTra.setText(LocalDate.now().plusDays(14).toString());
        txtNgayTra.setText("");
        txtTrangThai.setText("Dang muon");
        table.clearSelection();
    }
}
