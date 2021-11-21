/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.NguoiYeu;
import Models.NguoiYeuTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 *
 * @author pvc
 */
public class TablePanel extends JPanel{
    private JTable table;
    private NguoiYeuTableModel tableModel;
    
    private JButton themButton;
    private JButton suaButton;
    private JButton xoaButton;
    
    private JTextField timKiemField;
    private JButton timKiemButton;
    
    private TableListener tableListener;
    
    public TablePanel() {
        tableModel = new NguoiYeuTableModel();
        
        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setModel(tableModel);
        
        themButton = new JButton("Thêm");
        suaButton = new JButton("Sửa");
        xoaButton = new JButton("Xóa");
        
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(themButton);
        controlPanel.add(suaButton);
        controlPanel.add(xoaButton);
        
        timKiemField = new JTextField(30);
        timKiemButton = new JButton("Tìm kiếm");
        JPanel timKiemPanel = new JPanel();
        timKiemPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        timKiemPanel.add(timKiemField);
        timKiemPanel.add(timKiemButton);
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                int row = table.getSelectedRow();
                
                if (tableListener != null) {
                    tableListener.chonNguoiYeu(row);
                }
            }
        });
        
        themButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (tableListener != null) {
                    tableListener.themNguoiYeu();
                }
            }
        });
        
        xoaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int index = table.getSelectedRow();
                
                if (tableListener != null) {
                    tableListener.xoaNguoiYeu(index);
                }
            }
        });
        
        suaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int index = table.getSelectedRow();
                
                if (tableListener != null) {
                    tableListener.suaNguoiYeu(index);
                }
            }
        });
        
        timKiemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String ten = timKiemField.getText();
                if (tableListener != null) {
                    tableListener.timKiem(ten);
                }
            }
        });
        
        
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        add(timKiemPanel, BorderLayout.NORTH);
    }
    
    public void setData(List<NguoiYeu> db) {
        tableModel.setData(db);
    }
    
    public void setTableListener(TableListener tableListener) {
        this.tableListener = tableListener;
    }
    
    public void refresh() {
        tableModel.fireTableDataChanged();
    }
}
