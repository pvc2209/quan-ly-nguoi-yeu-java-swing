/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author pvc
 */
public class NguoiYeu {
    public static int count = 0;
    
    private int id;
    private String hoTen;
    private String diaChi;
    private int namSinh;
    GioiTinh gioiTinh;

    public NguoiYeu(String hoTen, String diaChi, int namSinh, GioiTinh gioiTinh) {
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;
        
        ++count;
        this.id = count;
    }

    public NguoiYeu(int id, String hoTen, String diaChi, int namSinh, GioiTinh gioiTinh) {
        this.id = id;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public GioiTinh getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(GioiTinh gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
    
}
