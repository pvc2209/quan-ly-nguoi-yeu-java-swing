/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.GioiTinh;
import Models.NguoiYeu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author pvc
 */
public class NguoiYeuPanel extends JPanel {

    private JLabel idLabel;
    private JLabel hoTenLabel;
    private JLabel diaChiLabel;
    private JLabel namSinhLabel;
    private JLabel gioiTinhLabel;

    private JTextField idField;
    private JTextField hoTenField;
    private JTextField diaChiField;
    private JTextField namSinhField;
    private JRadioButton namRadio;
    private JRadioButton nuRadio;
    private ButtonGroup gioiTinhGroup;

    public NguoiYeuPanel() {
        setPreferredSize(new Dimension(300, 500));
        
//        setBackground(Color.RED);
        idLabel = new JLabel("ID:");
        hoTenLabel = new JLabel("Họ tên:");
        diaChiLabel = new JLabel("Địa chỉ:");
        namSinhLabel = new JLabel("Năm sinh:");
        gioiTinhLabel = new JLabel("Giới tính:");

        idField = new JTextField(10);
        idField.setEnabled(false);

        hoTenField = new JTextField(10);
        diaChiField = new JTextField(10);
        namSinhField = new JTextField(10);

        namRadio = new JRadioButton("Nam");
        nuRadio = new JRadioButton("Nữ");
        
        namRadio.setSelected(true);

        gioiTinhGroup = new ButtonGroup();
        gioiTinhGroup.add(namRadio);
        gioiTinhGroup.add(nuRadio);

        Border innerBorder = BorderFactory.createTitledBorder("Thông tin người yêu");

        Border outterBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        setBorder(BorderFactory.createCompoundBorder(outterBorder, innerBorder));

        layoutComponent();
    }

    private void layoutComponent() {
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;

        Insets rightPadding = new Insets(0, 0, 0, 15);
        Insets noPadding = new Insets(0, 0, 0, 0);

        // Hàng 1
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightPadding;
        add(idLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = noPadding;
        add(idField, gc);

        // Hàng 2
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightPadding;
        add(hoTenLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = noPadding;
        add(hoTenField, gc);

        // Hàng 3
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightPadding;
        add(diaChiLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = noPadding;
        add(diaChiField, gc);

        // Hàng 4
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightPadding;
        add(namSinhLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = noPadding;
        add(namSinhField, gc);

        // Hàng 5
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightPadding;
        add(gioiTinhLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = noPadding;
        add(namRadio, gc);

        // Hàng 6
        gc.gridy++;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = noPadding;
        add(nuRadio, gc);

        // Hàng 7
        gc.weighty = 1;
        gc.gridy++;
        gc.gridx = 0;
        add(new JPanel(), gc);
    }
    
    public String getHoTen() {
        return hoTenField.getText();
    }
    
    public String getDiaChi() {
        return diaChiField.getText();
    }
    
    public int getNamSinh(){
        return Integer.parseInt(namSinhField.getText());
    }
    
    public GioiTinh getGioiTinh() {
        if (namRadio.isSelected()) {
            return GioiTinh.nam;
        }
        
        return GioiTinh.nu;
    }
    
    public void setInfo(int id, String hoTen, String diaChi, int namSinh, GioiTinh gioiTinh) {
        idField.setText(String.valueOf(id));
        hoTenField.setText(hoTen);
        diaChiField.setText(diaChi);
        namSinhField.setText(String.valueOf(namSinh));
        
        if (gioiTinh == gioiTinh.nam) {
            namRadio.setSelected(true);
        } else {
            nuRadio.setSelected(false);
        }
    }
    
    public void resetInfo() {
        idField.setText("");
        hoTenField.setText("");
        diaChiField.setText("");
        namSinhField.setText("");
        namRadio.setSelected(true);
    }
}
