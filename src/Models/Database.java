/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author pvc
 */
public class Database {

    private List<NguoiYeu> dsNguoiYeu;

    private Connection con;

    public Database() {
        dsNguoiYeu = new LinkedList<>();
    }

    public void connect() throws Exception {
        // Lưu ý 1 chút về cột là Auto Increment thì giá trị nhỏ nhất là 1
        // Nếu sửa giá trị cột đấy là 0 thì sẽ lỗi.
        if (con != null) {
            return;
        }

        try {
            // Check xem đã có thư viện com.mysql.jdbc.Driver hay chưa
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new Exception("Driver not found");
        }

        String url = "jdbc:mysql://localhost:3306/qlny";

        con = (Connection) DriverManager.getConnection(url, "root", "");

        System.out.println("Database connected");
    }

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Database closed");
            } catch (SQLException ex) {
                System.out.println("Can't close connection");
            }
        }
    }

    public void load() throws SQLException {
        dsNguoiYeu.clear();

        String sql = "SELECT * FROM nguoiyeu";

        Statement selectStatement = con.createStatement();

        ResultSet result = selectStatement.executeQuery(sql);

        while (result.next()) {
            int id = result.getInt("id");
            String hoTen = result.getString("hoten");
            String diaChi = result.getString("diachi");
            int namSinh = result.getInt("namsinh");
            GioiTinh gioiTinh = GioiTinh.valueOf(result.getString("gioitinh"));

            NguoiYeu nguoiYeu = new NguoiYeu(id, hoTen, diaChi, namSinh, gioiTinh);

            dsNguoiYeu.add(nguoiYeu);
        }

        NguoiYeu.count = getMaxId();
        
        // Reset auto increment vê max id
        resetAutoIncrement(NguoiYeu.count);

        result.close();
        selectStatement.close();
    }

    public int getMaxId() throws SQLException {
        String sql = "SELECT MAX(id) FROM nguoiyeu";
        Statement selectStatement = con.createStatement();

        ResultSet result = selectStatement.executeQuery(sql);

        int id = 0;
        if (result.next()) {
            id = result.getInt(1);
        }

        result.close();
        selectStatement.close();

        return id;
    }
    
    public void resetAutoIncrement(int maxId) throws SQLException {
        String sql = "ALTER TABLE nguoiyeu AUTO_INCREMENT = " + maxId;
        Statement statement = con.createStatement();
        statement.executeUpdate(sql);
    }

    public List<NguoiYeu> getDanhSachNguoiYeu() {
        return dsNguoiYeu;
    }

    public void themNguoiYeu(NguoiYeu nguoiYeu) throws SQLException {
        dsNguoiYeu.add(nguoiYeu);

        String sql = "INSERT INTO nguoiyeu (hoten, diachi, namsinh, gioitinh) VALUES (?, ?, ?, ?)";
        PreparedStatement insertStatement = con.prepareStatement(sql);

        insertStatement.setString(1, nguoiYeu.getHoTen());
        insertStatement.setString(2, nguoiYeu.getDiaChi());
        insertStatement.setInt(3, nguoiYeu.getNamSinh());
        insertStatement.setString(4, nguoiYeu.getGioiTinh().toString());

        insertStatement.executeUpdate();

        insertStatement.close();
    }

    public void suaNguoiYeu(int index, String hoTen, String diaChi, int namSinh, GioiTinh gioiTinh) throws SQLException {
        NguoiYeu nguoiYeu = dsNguoiYeu.get(index);

        nguoiYeu.setHoTen(hoTen);
        nguoiYeu.setDiaChi(diaChi);
        nguoiYeu.setNamSinh(namSinh);
        nguoiYeu.setGioiTinh(gioiTinh);

        String sql = "UPDATE nguoiyeu set hoTen=?, diachi=?, namsinh=?, gioitinh=? WHERE id=?";
        PreparedStatement updateStatement = con.prepareStatement(sql);

        updateStatement.setString(1, hoTen);
        updateStatement.setString(2, diaChi);
        updateStatement.setInt(3, namSinh);
        updateStatement.setString(4, gioiTinh.toString());
        updateStatement.setInt(5, nguoiYeu.getId());

        updateStatement.executeUpdate();

        updateStatement.close();
    }

    public void xoaNguoiYeu(int index) throws SQLException {
        int id = dsNguoiYeu.get(index).getId();

        dsNguoiYeu.remove(index);

        String sql = "DELETE FROM nguoiyeu WHERE id=?";
        PreparedStatement deleteStatement = con.prepareStatement(sql);

        deleteStatement.setInt(1, id);

        deleteStatement.executeUpdate();

        deleteStatement.close();
    }

    public void timKiem(String ten) throws SQLException {
        dsNguoiYeu.clear();

        String sql = "SELECT * FROM nguoiyeu WHERE hoten LIKE ?";

        PreparedStatement searchStatement = con.prepareStatement(sql);
        searchStatement.setString(1, "%" + ten + "%");

        System.out.println(searchStatement);

        ResultSet result = searchStatement.executeQuery();

        while (result.next()) {
            int id = result.getInt("id");
            String hoTen = result.getString("hoten");
            String diaChi = result.getString("diachi");
            int namSinh = result.getInt("namsinh");
            GioiTinh gioiTinh = GioiTinh.valueOf(result.getString("gioitinh"));

            NguoiYeu nguoiYeu = new NguoiYeu(id, hoTen, diaChi, namSinh, gioiTinh);

            dsNguoiYeu.add(nguoiYeu);
        }

        NguoiYeu.count = getMaxId();

        result.close();
        searchStatement.close();

        // Không làm theo cách return 1 list khác từ dsNguoiYeu để gán cho 
        // tableModel được vì lúc đó dữ liệu sẽ ko đồng nhất
        // và khi thêm, sửa, xóa sẽ lỗi
    }
}
