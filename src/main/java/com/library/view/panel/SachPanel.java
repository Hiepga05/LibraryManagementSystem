package com.library.view.panel;

import com.library.controller.NhaXuatBanController;
import com.library.controller.SachController;
import com.library.controller.TacGiaController;
import com.library.controller.TheLoaiController;
import com.library.exception.LoiKiemTra;
import com.library.model.NhaXuatBan;
import com.library.model.Sach;
import com.library.model.TacGia;
import com.library.model.TheLoai;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SachPanel extends JPanel {
    private final SachController controller = new SachController();
    private final TacGiaController tacGiaController = new TacGiaController();
    private final TheLoaiController theLoaiController = new TheLoaiController();
    private final NhaXuatBanController nxbController = new NhaXuatBanController();

    private JTextField txtMaSach, txtTenSach, txtNamXB, txtGia, txtSoLuong, txtViTri;
    private JComboBox<TacGia> cboTacGia;
    private JComboBox<TheLoai> cboTheLoai;
    private JComboBox<NhaXuatBan> cboNXB;
    private JTable table;
    private DefaultTableModel model;
    private int idDangChon = -1;
    private int soLuongConHienTai = 0;

    public SachPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel form = new JPanel(new GridLayout(9, 2, 8, 8));
        form.setBorder(BorderFactory.createTitledBorder("Thong tin sach"));
        txtMaSach = new JTextField(); txtTenSach = new JTextField();
        cboTacGia = new JComboBox<>(); cboTheLoai = new JComboBox<>(); cboNXB = new JComboBox<>();
        txtNamXB = new JTextField(); txtGia = new JTextField(); txtSoLuong = new JTextField(); txtViTri = new JTextField();

        form.add(new JLabel("Ma sach")); form.add(txtMaSach);
        form.add(new JLabel("Ten sach")); form.add(txtTenSach);
        form.add(new JLabel("Tac gia")); form.add(cboTacGia);
        form.add(new JLabel("The loai")); form.add(cboTheLoai);
        form.add(new JLabel("Nha xuat ban")); form.add(cboNXB);
        form.add(new JLabel("Nam xuat ban")); form.add(txtNamXB);
        form.add(new JLabel("Gia")); form.add(txtGia);
        form.add(new JLabel("So luong")); form.add(txtSoLuong);
        form.add(new JLabel("Vi tri")); form.add(txtViTri);

        JButton btnThem = new JButton("Them"), btnSua = new JButton("Sua"), btnXoa = new JButton("Xoa"), btnLamMoi = new JButton("Lam moi");
        JPanel buttons = new JPanel();
        buttons.add(btnThem); buttons.add(btnSua); buttons.add(btnXoa); buttons.add(btnLamMoi);
        JPanel top = new JPanel(new BorderLayout());
        top.add(form, BorderLayout.CENTER); top.add(buttons, BorderLayout.SOUTH);
        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"ID", "Ma sach", "Ten sach", "Tac gia", "The loai", "NXB", "Nam XB", "Gia", "So luong", "Con lai", "Vi tri"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(model); table.setRowHeight(26);
        add(new JScrollPane(table), BorderLayout.CENTER);

        napDanhMuc(); loadData();
        btnThem.addActionListener(e -> themSach());
        btnSua.addActionListener(e -> suaSach());
        btnXoa.addActionListener(e -> xoaSach());
        btnLamMoi.addActionListener(e -> lamMoi());
        table.getSelectionModel().addListSelectionListener(e -> { if (!e.getValueIsAdjusting()) hienThiDuLieu(); });
    }

    private void napDanhMuc() {
        cboTacGia.removeAllItems(); for (TacGia x : tacGiaController.layTatCa()) cboTacGia.addItem(x);
        cboTheLoai.removeAllItems(); for (TheLoai x : theLoaiController.layTatCa()) cboTheLoai.addItem(x);
        cboNXB.removeAllItems(); for (NhaXuatBan x : nxbController.layTatCa()) cboNXB.addItem(x);
        cboTacGia.setRenderer(renderer(v -> { TacGia x=(TacGia)v; return x.getMaTacGia()+" - "+x.getTenTacGia(); }));
        cboTheLoai.setRenderer(renderer(v -> { TheLoai x=(TheLoai)v; return x.getMaTheLoai()+" - "+x.getTenTheLoai(); }));
        cboNXB.setRenderer(renderer(v -> { NhaXuatBan x=(NhaXuatBan)v; return x.getMaNhaXuatBan()+" - "+x.getTenNhaXuatBan(); }));
    }

    private DefaultListCellRenderer renderer(java.util.function.Function<Object,String> fn) {
        return new DefaultListCellRenderer() {
            @Override public Component getListCellRendererComponent(JList<?> list,Object value,int index,boolean selected,boolean focus) {
                super.getListCellRendererComponent(list,value,index,selected,focus);
                if (value != null) setText(fn.apply(value)); return this;
            }
        };
    }

    private void loadData() {
        model.setRowCount(0);
        for (Sach s : controller.layTatCa()) {
            TacGia tg=tacGiaController.timTheoId(s.getIdTacGia());
            TheLoai tl=theLoaiController.timTheoId(s.getIdTheLoai());
            NhaXuatBan nxb=nxbController.timTheoId(s.getIdNXB());
            model.addRow(new Object[]{s.getId(),s.getMaSach(),s.getTenSach(),tg==null?"":tg.getTenTacGia(),tl==null?"":tl.getTenTheLoai(),nxb==null?"":nxb.getTenNhaXuatBan(),s.getNamXuatBan(),s.getGia(),s.getSoLuong(),s.getSoLuongCon(),s.getViTri()});
        }
    }

    private Sach layDuLieuForm(boolean dangSua) throws LoiKiemTra {
        TacGia tg=(TacGia)cboTacGia.getSelectedItem(); TheLoai tl=(TheLoai)cboTheLoai.getSelectedItem(); NhaXuatBan nxb=(NhaXuatBan)cboNXB.getSelectedItem();
        if (tg==null || tl==null || nxb==null) throw new LoiKiemTra("Can co tac gia, the loai va nha xuat ban truoc khi them sach.");
        try {
            Sach s=new Sach();
            s.setMaSach(txtMaSach.getText().trim()); s.setTenSach(txtTenSach.getText().trim());
            s.setIdTacGia(tg.getId()); s.setIdTheLoai(tl.getId()); s.setIdNXB(nxb.getId());
            s.setNamXuatBan(Integer.parseInt(txtNamXB.getText().trim())); s.setGia(Double.parseDouble(txtGia.getText().trim()));
            int soLuong=Integer.parseInt(txtSoLuong.getText().trim()); s.setSoLuong(soLuong);
            if (dangSua) {
                int daMuon = Math.max(0, Integer.parseInt(model.getValueAt(table.getSelectedRow(),8).toString()) - soLuongConHienTai);
                if (soLuong < daMuon) throw new LoiKiemTra("So luong moi khong duoc nho hon so sach dang duoc muon ("+daMuon+").");
                s.setSoLuongCon(soLuong-daMuon);
            } else s.setSoLuongCon(soLuong);
            s.setViTri(txtViTri.getText().trim()); return s;
        } catch (NumberFormatException e) { throw new LoiKiemTra("Nam xuat ban, gia va so luong phai la so hop le."); }
    }

    private void themSach() {
        try { if (controller.them(layDuLieuForm(false))) { JOptionPane.showMessageDialog(this,"Them thanh cong."); loadData(); lamMoi(); } }
        catch (Exception e) { JOptionPane.showMessageDialog(this,e.getMessage(),"Loi",JOptionPane.ERROR_MESSAGE); }
    }
    private void suaSach() {
        if (idDangChon<0) { JOptionPane.showMessageDialog(this,"Chon mot sach."); return; }
        try { Sach s=layDuLieuForm(true); s.setId(idDangChon); if(controller.sua(s)){JOptionPane.showMessageDialog(this,"Cap nhat thanh cong.");loadData();lamMoi();} }
        catch(Exception e){JOptionPane.showMessageDialog(this,e.getMessage(),"Loi",JOptionPane.ERROR_MESSAGE);}
    }
    private void xoaSach() {
        if(idDangChon<0){JOptionPane.showMessageDialog(this,"Chon mot sach.");return;}
        if(JOptionPane.showConfirmDialog(this,"Ban co chac muon xoa?","Xac nhan",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;
        try{if(controller.xoa(idDangChon)){loadData();lamMoi();}}catch(Exception e){JOptionPane.showMessageDialog(this,e.getMessage(),"Loi",JOptionPane.ERROR_MESSAGE);}
    }
    private void hienThiDuLieu() {
        int row=table.getSelectedRow(); if(row<0)return;
        idDangChon=Integer.parseInt(model.getValueAt(row,0).toString()); Sach s=controller.timTheoId(idDangChon); if(s==null)return;
        txtMaSach.setText(s.getMaSach()); txtTenSach.setText(s.getTenSach()); selectTacGia(s.getIdTacGia()); selectTheLoai(s.getIdTheLoai()); selectNXB(s.getIdNXB());
        txtNamXB.setText(String.valueOf(s.getNamXuatBan())); txtGia.setText(String.valueOf(s.getGia())); txtSoLuong.setText(String.valueOf(s.getSoLuong())); txtViTri.setText(s.getViTri()); soLuongConHienTai=s.getSoLuongCon();
    }
    private void selectTacGia(int id){for(int i=0;i<cboTacGia.getItemCount();i++)if(cboTacGia.getItemAt(i).getId()==id){cboTacGia.setSelectedIndex(i);break;}}
    private void selectTheLoai(int id){for(int i=0;i<cboTheLoai.getItemCount();i++)if(cboTheLoai.getItemAt(i).getId()==id){cboTheLoai.setSelectedIndex(i);break;}}
    private void selectNXB(int id){for(int i=0;i<cboNXB.getItemCount();i++)if(cboNXB.getItemAt(i).getId()==id){cboNXB.setSelectedIndex(i);break;}}
    private void lamMoi(){idDangChon=-1;soLuongConHienTai=0;txtMaSach.setText("");txtTenSach.setText("");txtNamXB.setText("");txtGia.setText("");txtSoLuong.setText("");txtViTri.setText("");table.clearSelection();napDanhMuc();}
}
