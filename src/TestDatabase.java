
import Models.Database;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pvc
 */
public class TestDatabase {
    public static void main(String[] args) {
        Database db = new Database();
        
        try {
            db.connect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            int maxId = db.getMaxId();
            
            System.out.println(maxId);
        } catch (SQLException ex) {
            Logger.getLogger(TestDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            db.load();
        } catch (SQLException ex) {
            Logger.getLogger(TestDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        db.disconnect();
    }
}
