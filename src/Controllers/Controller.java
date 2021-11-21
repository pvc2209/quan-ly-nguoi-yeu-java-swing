/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Database;
import Models.GioiTinh;
import Models.NguoiYeu;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author pvc
 */
public class Controller {
    private Database db = new Database();

    public List<NguoiYeu> getDanhSachNguoiYeu() {
        return db.getDanhSachNguoiYeu();
    }
    
    public void themNguoiYeu(NguoiYeu nguoiYeu) throws SQLException {
        db.themNguoiYeu(nguoiYeu);
    }
    
    public void xoaNguoiYeu(int index) throws SQLException {
        db.xoaNguoiYeu(index);
    }
    
    public void suaNguoiYeu(int index, String hoTen, String diaChi, int namSinh, GioiTinh gioiTinh) throws SQLException {
        db.suaNguoiYeu(index, hoTen, diaChi, namSinh, gioiTinh);
    }
    
    public void timKiem(String ten) throws SQLException {
        db.timKiem(ten);
    }
    
    public void connect() throws Exception {
        db.connect();
    }
    
    public void disconnect() {
        db.disconnect();
    }
    
    public void load() throws SQLException {
        db.load();
    }
}
