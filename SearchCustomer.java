import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class SearchCustomer extends JInternalFrame {
	private JTextField txtphone;
	private JTextField txtname;
	private JTextField txtpassid;
	private JTextField dob;
	private JTextField txtcontact;
	private JTextField txtfind;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchCustomer frame = new SearchCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*Pregatim conectarea la baza de date cu connection si preparedstatement */
	Connection con;
	PreparedStatement pst;
	
	/*Functia connect "aprinde" baza de date pentru utilizare cu un drivermanager.getconnection inconjurata de un try-catch, utilizand
	 * numele bazei de date, root-ul si parola */
	public void Connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/airline", "root","");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
	
	/**
	 * Create the frame.
	 */
	public SearchCustomer() {
		
		Connect();
		setClosable(true);
		setVisible(true);
		setBounds(100, 100, 861, 439);
		getContentPane().setLayout(null);
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, 855, 411);
		getContentPane().add(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setBounds(0, 0, 855, 411);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Full Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(51, 100, 78, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Phone Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(51, 136, 102, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("Passport ID");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(51, 176, 95, 13);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Address");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(51, 227, 78, 13);
		panel.add(lblNewLabel_5);
		
		txtphone = new JTextField();
		txtphone.setColumns(10);
		txtphone.setBounds(168, 134, 181, 19);
		panel.add(txtphone);
		
		txtname = new JTextField();
		txtname.setColumns(10);
		txtname.setBounds(168, 98, 181, 19);
		panel.add(txtname);
		
		txtpassid = new JTextField();
		txtpassid.setColumns(10);
		txtpassid.setBounds(168, 174, 181, 19);
		panel.add(txtpassid);
		
		JTextPane txtaddress = new JTextPane();
		txtaddress.setBackground(SystemColor.controlHighlight);
		txtaddress.setForeground(Color.LIGHT_GRAY);
		txtaddress.setBounds(168, 227, 181, 86);
		panel.add(txtaddress);
		
		JLabel lblNewLabel_6 = new JLabel("Date of Birth");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(445, 101, 95, 13);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Gender");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7.setBounds(445, 137, 45, 13);
		panel.add(lblNewLabel_7);
		
		JCheckBox checkmale = new JCheckBox("Male");
		checkmale.setBackground(Color.WHITE);
		checkmale.setFont(new Font("Tahoma", Font.BOLD, 12));
		checkmale.setBounds(545, 137, 63, 21);
		panel.add(checkmale);
		
		JCheckBox checkfemale = new JCheckBox("Female");
		checkfemale.setBackground(Color.WHITE);
		checkfemale.setFont(new Font("Tahoma", Font.BOLD, 12));
		checkfemale.setBounds(610, 136, 93, 21);
		panel.add(checkfemale);
		
		JLabel lblNewLabel_8 = new JLabel("Contact");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_8.setBounds(445, 177, 63, 13);
		panel.add(lblNewLabel_8);
		
		dob = new JTextField();
		dob.setColumns(10);
		dob.setBounds(545, 98, 181, 19);
		panel.add(dob);
		
		txtcontact = new JTextField();
		txtcontact.setColumns(10);
		txtcontact.setBounds(545, 174, 181, 19);
		panel.add(txtcontact);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					/*Cream variabile care primesc datele din tabela produse*/
		            String name = txtname.getText();
		            String phone = txtphone.getText();
		            String passid = txtpassid.getText();
		            String address = txtaddress.getText();
		            String dateob = dob.getText();
		            String gender;
		            String contact = txtcontact.getText();
		            
		            if(checkmale.isSelected())
		            	gender = "Male";
		            else
		            	gender = "Female";
		            String pasidd = txtfind.getText();
		            /*Pregateste baza de date pentru editarea valorilor */
		            //name,phone,passid,address,dob,gender,contact
		            pst = con.prepareStatement("update adduser set name = ?, phone = ?, passid = ?, address = ?, dob = ?, gender = ?, contact = ? where passid = ?");
		            
		            /*Seteaza valorile care sunt introduse in baza de date cu valorile primite din adduser.java*/
		            pst.setString(1, name);
		            pst.setString(2, phone);
		            pst.setString(3, passid);
		            pst.setString(4, address);
		            pst.setString(5, dateob);
		            pst.setString(6, gender);
		            pst.setString(7, contact);
		            pst.setString(8, passid);
		            pst.executeUpdate();
		            
		            
		        } catch (SQLException ex) {
		        	ex.printStackTrace();
		        }
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(408, 271, 100, 42);
		panel.add(btnNewButton);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtname.setText("");
	            txtphone.setText("");
	            txtpassid.setText("");
	            txtaddress.setText("");
	            dob.setText("");
	            txtcontact.setText("");
	            checkmale.setSelected(false);
	            checkfemale.setSelected(false);
	            txtfind.setText("");
			}
		});
		clear.setFont(new Font("Tahoma", Font.BOLD, 12));
		clear.setBounds(545, 271, 100, 42);
		panel.add(clear);
		
		JLabel lblNewLabel_2 = new JLabel("Search By Passport ID: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_2.setBounds(52, 31, 226, 33);
		panel.add(lblNewLabel_2);
		
		txtfind = new JTextField();
		txtfind.setBounds(281, 41, 157, 19);
		panel.add(txtfind);
		txtfind.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Find");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pasidd = txtfind.getText();
				
				
		        try {
		            /*Selecteaza tot continutul bazei de date vanzator si executa query-ul*/
		            pst = con.prepareStatement("select * from adduser");
		            ResultSet rs = pst.executeQuery();
		            

		            while(rs.next())
		            {
		                    if((rs.getString("passid")).equals(pasidd))
		                    {
		                     txtname.setText(rs.getString("name"));
		                     txtphone.setText(rs.getString("phone"));
		                     txtpassid.setText(rs.getString("passid"));
		                     txtaddress.setText(rs.getString("address"));
		                     dob.setText(rs.getString("dob"));
		                     txtcontact.setText(rs.getString("contact"));
		                     if((rs.getString("gender")).equals("Male"))
		                     {
		                    	 checkfemale.setSelected(false);
		                    	 checkmale.setSelected(true);
		                     }
		                     else
		                     { 
		                    	 checkmale.setSelected(false);
		                    	 checkfemale.setSelected(true);
		                     
		                     }
		                    }
		            }       
		                    
		            
		            //aruncam erori
		        } catch (SQLException ex) {
		        	ex.printStackTrace();
		        }
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBounds(455, 41, 85, 21);
		panel.add(btnNewButton_1);

	}
}
