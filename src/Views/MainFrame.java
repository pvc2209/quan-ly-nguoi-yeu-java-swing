/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.Controller;
import Models.GioiTinh;
import Models.NguoiYeu;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author pvc
 */
public class MainFrame extends JFrame {
    
    private Controller controller;
    
    private NguoiYeuPanel nguoiYeuPanel;
    private TablePanel tablePanel;
    
    public MainFrame() {
        super("Quản lý người yêu");
        setLayout(new BorderLayout());
        
        controller = new Controller();
        
        connectDatabase();
        
        try {
            controller.load();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.this, "Không thể load dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        nguoiYeuPanel = new NguoiYeuPanel();
        tablePanel = new TablePanel();
        
        tablePanel.setData(controller.getDanhSachNguoiYeu());
        
        tablePanel.setTableListener(new TableListener() {
            @Override
            public void themNguoiYeu() {
                String hoTen = nguoiYeuPanel.getHoTen();
                String diaChi = nguoiYeuPanel.getDiaChi();
                
                try {
                    int namSinh = nguoiYeuPanel.getNamSinh();
                    
                    GioiTinh gioiTinh = nguoiYeuPanel.getGioiTinh();
                    
                    NguoiYeu nguoiYeu = new NguoiYeu(hoTen, diaChi, namSinh, gioiTinh);
                    
                    controller.themNguoiYeu(nguoiYeu);
                    tablePanel.refresh();
                    
                    nguoiYeuPanel.resetInfo();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Năm sinh không hợp lệ", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Không thể thêm vào database", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            @Override
            public void suaNguoiYeu(int index) {
                String hoTen = nguoiYeuPanel.getHoTen();
                String diaChi = nguoiYeuPanel.getDiaChi();
                
                try {
                    int namSinh = nguoiYeuPanel.getNamSinh();
                    
                    GioiTinh gioiTinh = nguoiYeuPanel.getGioiTinh();
                    
                    controller.suaNguoiYeu(index, hoTen, diaChi, namSinh, gioiTinh);
                    tablePanel.refresh();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Năm sinh không hợp lệ", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Vị trí sửa không hợp lệ", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Không thể sửa trong database", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
            
            @Override
            public void xoaNguoiYeu(int index) {
                if (index >= 0) {
                    try {
                        controller.xoaNguoiYeu(index);
                        tablePanel.refresh();
                        
                        nguoiYeuPanel.resetInfo();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Không thể xóa trong database", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(MainFrame.this, "Vị trí xóa không hơp lệ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            @Override
            public void chonNguoiYeu(int index) {
                NguoiYeu nguoiYeu = controller.getDanhSachNguoiYeu().get(index);
                
                nguoiYeuPanel.setInfo(nguoiYeu.getId(), nguoiYeu.getHoTen(), nguoiYeu.getDiaChi(), nguoiYeu.getNamSinh(), nguoiYeu.getGioiTinh());
            }
            
            @Override
            public void timKiem(String ten) {
                try {
                    controller.timKiem(ten);
                    tablePanel.refresh();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(MainFrame.this, "Không thể tìm kiếm trong database", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        add(nguoiYeuPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);
        
        setSize(new Dimension(800, 500));
        setVisible(true);
        setLocationRelativeTo(null);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                controller.disconnect();
                dispose();
            }
        });
    }
    
    private void connectDatabase() {
        try {
            controller.connect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(MainFrame.this, "Không thể kết nối tới database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
