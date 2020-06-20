/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codegui1;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.Statement;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class Frame1 extends JFrame {
    
    private JLabel lb1 = new JLabel("Mã Bệnh Nhân");
    private JLabel lb11 = new JLabel("Tên Bệnh Nhân"); 
    private JLabel lb12 = new JLabel("Ngày Sinh");
    private JLabel lb13 = new JLabel("Địa Chỉ");
    private JLabel lb14 = new JLabel("SĐT");
    private JLabel lb15 = new JLabel("GT");
    private JButton btn = new JButton("Thêm");
    private JTextField txtma = new JTextField("nv01");
    private JTextField txtten = new JTextField("tên bệnh nhân");
    private JTextField txtngaysinh = new JTextField("2000/10/10");
    private JTextField txtdiachi = new JTextField("địa chỉ");
    private JTextField txtsdt = new JTextField("0123");
    private JComboBox cbbgt = new JComboBox(new String[]{"Nam","Nữ"});
    public Frame1()
    {
        super("B1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container con = getContentPane();
        con.setLayout(new FlowLayout());
        con.add(lb1);
        con.add(txtma);
        con.add(lb11);
        con.add(txtten);
        con.add(lb12);
        con.add(txtngaysinh);
        con.add(lb13);
        con.add(txtdiachi);
        con.add(lb14);
        con.add(txtsdt);
        con.add(lb15);
        con.add(cbbgt);
        con.add(btn);
        setSize(900,100);
        setVisible(true);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                Connection con = ConnectData.GetConnection();
                String sql = "insert into benhnhan values('"+txtma.getText()+"','"+txtten.getText()+"','"+txtngaysinh.getText()+"','"+txtdiachi.getText()+"','"+txtsdt.getText()+"','"+cbbgt.getSelectedItem().toString()+"')";
                try {
                    Statement st = (Statement) con.createStatement();
                    int k =0;
                    k= st.executeUpdate(sql);
                    if(k > 0)
                    {
                        JOptionPane.showMessageDialog(null,"Insert Successful !", "Notifical", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Insert Fail !","Notifical", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Frame1.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }
}
