/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codegui1;

import com.mysql.jdbc.MySQLConnection;
import java.sql.ResultSet;
import com.mysql.jdbc.Statement;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author phams
 */
public class Frame2 extends JFrame {
    private JLabel lb1 = new JLabel("Mã Bệnh Nhân");
    private JLabel lb11 = new JLabel("Tên Bệnh Nhân"); 
    private JLabel lb12 = new JLabel("Ngày Khám");
    private JLabel lb13 = new JLabel("Yêu Cầu Khám");
    private JLabel lb14 = new JLabel("Bác Sĩ Khám");
    private JTextField txtma = new JTextField("nv01");
    private JTextField txtten = new JTextField("tên bệnh nhân");
    private JTextField txtngaykham = new JTextField("2000/10/10");
    private JTextField txtyeucau = new JTextField("Yêu cầu khám");
    private JComboBox cbbbs = new JComboBox();
    private JButton btn = new JButton("Đặt Lịch Khám");
    
    public Frame2()
    {
        super("frame 2");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Container con = getContentPane();
        this.txtten.setEnabled(false);
        con.setLayout(new FlowLayout());
        
        con.add(lb1);
        con.add(txtma);
   
        con.add(lb11);
        con.add(txtten);
        
        con.add(lb12);
        con.add(txtngaykham);
        
        con.add(lb13);
        con.add(txtyeucau);
        
        con.add(lb14);
        con.add(cbbbs);
        con.add(btn);
        
        setSize(200,300);
        setVisible(true);
        
        ArrayList<String> dsbs = new ArrayList<>();
        MySQLConnection con2 = (MySQLConnection) ConnectData.GetConnection();
        Statement st2;
        try {
            st2 = (Statement) con2.createStatement();
            ResultSet rs2 = st2.executeQuery("select * from bacsi");
            
            while(rs2.next())
            {
                dsbs.add(rs2.getString("mabs"));
                cbbbs.addItem(rs2.getString("tenbs"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Frame2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        txtma.addKeyListener(new KeyAdapter() {
        
        @Override
            public void keyPressed(KeyEvent e ) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                String ma = txtma.getText();
                
                String sql = "select tenbn from benhnhan where mabn = '"+ma+"'";
                MySQLConnection con = (MySQLConnection) ConnectData.GetConnection();
                try {
                    Statement st = (Statement) con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    if(rs.next())
                    {
                        String ten = rs.getString("tenbn");
                        txtten.setText(ten);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Frame2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
            
        });
        
        btn.addActionListener(e ->{
            int k = cbbbs.getSelectedIndex();
            
            String sql = "insert into khambenh(mabn,mabs,ngaykham,yeucaukham) values('"+txtma.getText()+"','"+dsbs.get(k).toString()+"','"+txtngaykham.getText()+"','"+txtyeucau.getText()+"')";
            MySQLConnection con3 = (MySQLConnection) ConnectData.GetConnection();
            try {
                Statement st3 = (Statement) con3.createStatement();
                int z = st3.executeUpdate(sql);
                if(z > 0)
                    {
                        JOptionPane.showMessageDialog(null,"Insert Successful !", "Notifical", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Insert Fail !","Notifical", JOptionPane.WARNING_MESSAGE);
                    }
            } catch (SQLException ex) {
                Logger.getLogger(Frame2.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        
       
    }
    
    
}
