/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codegui1;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phams
 */
public class ConnectData {
    public static Connection GetConnection() 
    {
        Connection con = null;

        try {
            String url = "jdbc:mysql://localhost:3306/benhvien";
            String Username = "root";
            String Password = "";
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("loi1");
                Logger.getLogger(ConnectData.class.getName()).log(Level.SEVERE, null, ex);
            }
            con = (Connection) DriverManager.getConnection(url,Username,Password);
        } catch (SQLException ex) {
            System.out.println("loi2");
            Logger.getLogger(ConnectData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
