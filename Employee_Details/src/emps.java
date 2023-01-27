import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class emps extends JFrame {

	private JPanel contentPane;
	private JTextField txtempname;
	private JTextField txtrole;
	private JTextField txtcompanyname;
	private JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					emps frame = new emps();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
	}

	/**
	 * Create the frame.
	 */
	
		
	// Connecting jdbc
	
	public emps() {
		initialize();
		Connect();
		table_load();
		
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtbid;
	
	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/emps","root","root");
		} catch (ClassNotFoundException ex) {
			//Logger.getLogger(Student.class.getName()).log(level,SEVERE, null, ex);
			
		} catch (SQLException ex) {
			//Logger.getLogger(Student.class.getName()).log(level,SEVERE, null, ex);						
		}
	}
	
	
	public void table_load() {
		try {
			pst = con.prepareStatement("select * from emp_table");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e) {
              e.printStackTrace();			
		}
	}
	
	

	
	
	
	
	public void initialize(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 911, 539);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Registation");
		lblNewLabel.setBounds(293, 10, 320, 51);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(25, 102, 399, 261);
		panel.setFont(new Font("Tahoma", Font.ITALIC, 22));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registation", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setToolTipText("");
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Employee Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(26, 42, 96, 30);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Role");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(26, 100, 45, 26);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Company Name");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(26, 154, 96, 26);
		panel.add(lblNewLabel_3);
		
		txtempname = new JTextField();
		txtempname.setBounds(192, 43, 150, 29);
		panel.add(txtempname);
		txtempname.setColumns(10);
		
		txtrole = new JTextField();
		txtrole.setBounds(192, 97, 150, 29);
		panel.add(txtrole);
		txtrole.setColumns(10);
		
		txtcompanyname = new JTextField();
		txtcompanyname.setBounds(192, 151, 150, 29);
		panel.add(txtcompanyname);
		txtcompanyname.setColumns(10);
		
		// Add Method
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connect();
				String name,role,companyName;
				
				name = txtempname.getText();
				role = txtrole.getText();
				companyName = txtcompanyname.getText();
				
				try {
					pst = con.prepareStatement("insert into emp_table(name,role,companyName)values(?,?,?)");
					pst.setString(1,name);
					pst.setString(2, role);
					pst.setString(3, companyName);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null , "Record Added");
					table_load();
					
					txtempname.setText("");
					txtrole.setText("");
					txtcompanyname.setText("");
					txtempname.requestFocus();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(108, 221, 85, 30);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
               String name,role,companyName,bid;
				
				name = txtempname.getText();
				role = txtrole.getText();
				companyName = txtcompanyname.getText();
				bid = txtbid.getText();
				
				try {
					pst = con.prepareStatement("update emp_table set name=?,role=? , companyName=? where id=? ");
					pst.setString(1,name);
					pst.setString(2, role);
					pst.setString(3, companyName);
					pst.setString(4, bid);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null , "Record Update");
					table_load();
					
					txtempname.setText("");
					txtrole.setText("");
					txtcompanyname.setText("");
					txtempname.requestFocus();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(203, 221, 85, 30);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                String bid;
				
				bid = txtbid.getText();
				
				try {
					pst = con.prepareStatement("delete from emp_table where id=? ");
					
					pst.setString(1, bid);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null , "Record Delete");
					table_load();
					
					txtempname.setText("");
					txtrole.setText("");
					txtcompanyname.setText("");
					txtempname.requestFocus();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.setBounds(298, 221, 85, 30);
		panel.add(btnNewButton_2);
		
		JList list = new JList();
		list.setBounds(591, 246, 1, 1);
		contentPane.add(list);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(436, 89, 451, 403);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(35, 396, 389, 96);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Emp id");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(26, 36, 64, 22);
		panel_1.add(lblNewLabel_4);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				
				try {
					String id = txtbid.getText();
					
					pst = con.prepareStatement("select name,role,companyName from emp_table where id = ? ");
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next()== true) {
						
						String name = rs.getString(1);
						String role = rs.getString(2);
						String companyName = rs.getString(3);
						
						txtempname.setText(name);
						txtrole.setText(role);
						txtcompanyname.setText(companyName);
					}
					else {
						
						txtempname.setText("");
						txtrole.setText("");
						txtcompanyname.setText("");						
						
					}
					
				}
				
				catch (SQLException ex) {
					
				}
				
			}
		});
		txtbid.setBounds(109, 34, 124, 24);
		panel_1.add(txtbid);
		txtbid.setColumns(10);
	}
}
