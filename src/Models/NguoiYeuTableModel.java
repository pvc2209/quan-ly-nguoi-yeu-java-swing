/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author pvc
 */
public class NguoiYeuTableModel extends AbstractTableModel {
    private List<NguoiYeu> db;
    private String[] columnName = {"ID", "Họ tên", "Địa chỉ", "Năm sinh", "Giới tính"};
    
    public void setData(List<NguoiYeu> db) {
        this.db = db;
    }
    
    @Override
    public String getColumnName(int i) {
        return columnName[i];
    }
    
    @Override
    public int getRowCount() {
        return db.size();
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        NguoiYeu nguoiYeu = db.get(row);
        
        switch (col) {
            case 0:
                return nguoiYeu.getId();
            case 1:
                return nguoiYeu.getHoTen();
            case 2:
                return nguoiYeu.getDiaChi();
            case 3:
                return nguoiYeu.getNamSinh();
            case 4:
                return nguoiYeu.getGioiTinh() == GioiTinh.nam ? "Nam" : "Nữ";
        }

        return null;
    }
    
}
