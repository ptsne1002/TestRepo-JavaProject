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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author phams
 */
public class Frame3 extends JFrame {
    private JLabel lblbsk = new JLabel("Bác Sĩ Khám");
    private JLabel lbltbn = new JLabel("Tên Bệnh Nhân"); 
    private JLabel lblnk = new JLabel("Ngày Khám");
    private JLabel lblyck = new JLabel("Yêu Cầu Khám");
    private JLabel lblkl = new JLabel("Kết Luận");
    private JLabel lbldsdv = new JLabel("Danh Sách Dịch Vụ");
    private JLabel lblbsc = new JLabel("Danh Sách Bác Sĩ Chọn"); 
    private JButton btnThem = new JButton("Thêm");
    private JComboBox cbbbsk  = new JComboBox();
    private JComboBox cbbtbn = new JComboBox();
    private JTextField txtnk = new JTextField("2000/10/10");
    private JTextField txtyck = new JTextField("Yêu Cầu Khám");
    private JTextField txtkl = new JTextField("Kết Luận");
    private JTable tbldsdv = new JTable();
    private JTable tbldsc = new JTable();
    
    public Frame3()
    {
        super("Frame 3");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        txtyck.setEnabled(false);
        cbbtbn.setEnabled(false);
        // xử lí bên trong.
        
        String sql1 = "select * from bacsi";
        ArrayList<String> dsmbs = new ArrayList<>();
        
        MySQLConnection connect = (MySQLConnection) ConnectData.GetConnection();
        Statement st;
        try {
            st = (Statement) connect.createStatement();
            ResultSet rs = st.executeQuery(sql1);
            while(rs.next())
            {
                cbbbsk.addItem(rs.getString("tenbs").toString());
                dsmbs.add(rs.getString("mabs").toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Frame3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ArrayList<String> dsmbn = new ArrayList<>();
        
        txtnk.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e)
            {
                 int k = cbbbsk.getSelectedIndex();
                 
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    cbbtbn.removeAllItems();
                    String sql2 = "select mabn from khambenh where mabs = '"+dsmbs.get(k).toString()+"' and ngaykham = '"+txtnk.getText()+"'";
                    try {
                        dsmbn.clear();
                        Statement st2 = (Statement) connect.createStatement();
                        ResultSet rs2 = st2.executeQuery(sql2);
                        while(rs2.next())
                        {
                            dsmbn.add(rs2.getString("mabn"));
                            String sql3 = "select tenbn from benhnhan where mabn = '"+rs2.getString("mabn")+"'";
                            Statement st3 = (Statement) connect.createStatement();
                            ResultSet rs3 = st3.executeQuery(sql3);
                            while(rs3.next())
                            {
                                cbbtbn.addItem(rs3.getString("tenbn"));
                            }
                        }
                        cbbtbn.setEnabled(true);
                    } catch (SQLException ex) {
                        Logger.getLogger(Frame3.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        });
        
        cbbtbn.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int l = cbbtbn.getSelectedIndex();
                int k = cbbbsk.getSelectedIndex();
                if(l >-1)
                {
                String sql4 = "select yeucaukham from khambenh where mabs = '"+dsmbs.get(k)+"' and ngaykham = '"+txtnk.getText()+"' and mabn = '"+dsmbn.get(l)+"'";
                try {
                    Statement st4 = (Statement) connect.createStatement();
                    ResultSet rs4 = st4.executeQuery(sql4);
                    while(rs4.next())
                    {
                        txtyck.setText(rs4.getString("yeucaukham"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Frame3.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            };
        
            
        });
        
        
        Container con = getContentPane();
        con.setLayout(new FlowLayout());
        con.add(lblbsk);
        con.add(cbbbsk);
        con.add(lblnk);
        con.add(txtnk);
        con.add(lbltbn);
        con.add(txtnk);
        con.add(lbltbn);
        con.add(cbbtbn);
        con.add(lblyck);
        con.add(txtyck);
        con.add(lblkl);
        con.add(txtkl);
        
        String[] columnNames = {"Type"};
        String[] data = {"Buy"};
        DefaultTableModel tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(columnNames);
        tblModel.addRow(data);
        //tbldsdv = new JTable(tblModelds);
        
        tbldsdv.setModel(tblModel);
        tbldsc.setModel(tblModel);
        
        tbldsdv.setVisible(true);
        tbldsc.setVisible(true);
        

        con.add(lbldsdv);
        add(tbldsdv);
        con.add(lblbsc);
        con.add(tbldsc);
        
        setSize(400,600);
        setVisible(true);
                
    }
}
