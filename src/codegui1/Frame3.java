/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codegui1;

import java.awt.Container;
import java.awt.FlowLayout;
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
    private JTextField txtnk = new JTextField("Ngày Khám");
    private JTextField txtyck = new JTextField("Yêu Cầu Khám");
    private JTextField txtkl = new JTextField("Kết Luận");
    private JTable tbldsdv = new JTable();
    private JTable tbldsc = new JTable();
    
    public Frame3()
    {
        super("Frame 3");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        
        
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
