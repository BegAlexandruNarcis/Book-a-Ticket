import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import java.sql.*;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtusername;
	private JPasswordField txtpasswd;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					Login frame = new Login();
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
	
	/*Creeaza form-ul de login*/
	public Login() {
		
		
		Connect();
		/*Creearea unui contentpane cu numele easy airline*/
		setTitle("Easy Airline");
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblClock = new JLabel("New label");
		lblClock.setBounds(0, 0, 105, 37);
		contentPane.add(lblClock);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(53, 154, 467, 209);
		contentPane.add(panel);
		panel.setLayout(null);
		
		/*Label-ul username pentru vizualizare, langa se afla un jtextbox pentru inserare*/
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(106, 26, 100, 15);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblNewLabel_1);
		
		/*Label-ul password pentru vizualizare, langa se afla un jtextbox pentru inserare*/
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(106, 72, 130, 15);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblNewLabel);
		
		/*Creearea textfield-ului username in care se va introduce numele de utilizator*/
		txtusername = new JTextField();
		txtusername.setBounds(263, 22, 184, 19);
		panel.add(txtusername);
		txtusername.setColumns(10);
		
		/*Creearea textfield-ului password in care se va introduce parola*/
		txtpasswd = new JPasswordField();
		txtpasswd.setBounds(263, 72, 184, 19);
		panel.add(txtpasswd);
		
		
		/*Crearea butonului de login */
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        	
				/*variabilele username si password primesc din labeurile txtusername si txtpassword text-ul introdus in ele */
				String username = txtusername.getText();
		        String password = txtpasswd.getText();
		        
		        /*Daca variabilele sunt goale, adica nu a fost introdus nimic se afiseaza mesajul "UserName or Password Blank*/
		        if(username.isEmpty() ||  password.isEmpty())
		        {
		            JOptionPane.showMessageDialog(null, "UserName or Password Blank");
		        }
		        else
		        {
		        	
		            try {
		            	String user, pass;
		            	
		                
		                /*Din baza de date user se trimite aplicatiei utilizatorul si parola */
		                Statement pst = con.prepareStatement("select * from user where username = ? AND password = ?");
		                 user = txtusername.getText();
		                 pass = txtpasswd.getText();
		                 
		                 
		                 ResultSet rs = pst.executeQuery("select * from user where username = ? AND password = ?");
		                 
		                 /*Daca exista un utilizator si o parola care sa coincida cu username-ul si parola introduse de utilizator se 
		                  * conecteaza si ne trimite in aplicatie */
		                 if(rs.next())
		                 {
		                	 if(username.equals(user) && password.equals(pass))
		                 	{
		                	 	Main m = new Main();
		                    
		                     	m.setVisible(true);
		                     	setVisible(false);   
		                 	}
		                 }
		                 else
		                 {
		                	 /*Daca utilizatorul si parola sunt gresite se afiseaza textul "UserName or Password do not Match" 
		                	  * si apoi se seteaza textfield-urile pe null*/
		                       JOptionPane.showMessageDialog(null, "UserName or Password do not Match");
		                       txtusername.setText("");
		                       txtpasswd.setText("");
		                       txtusername.requestFocus();                   
		                 }	                	            
		            } catch (SQLException ex) {
		                Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		            }
		        }
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(90, 147, 105, 35);
		panel.add(btnNewButton);
		
		/*Butonul Clear seteaza text-ul din casute pe null pentru o rescriere a linilor*/
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					txtusername.setText("");
					txtpasswd.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnClear.setBounds(215, 147, 105, 35);
		panel.add(btnClear);
		
		/*Butonul Exit iese fortat din aplicatie*/
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setBounds(334, 147, 105, 35);
		panel.add(btnExit);
		
		/*Se creaza un jlabel, iar prin ajutorul functiei Image si setIcon, se introduce o poza cu un lacat din folderul img*/
		JLabel lblNewLabel_2 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/lock.png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(img));
		lblNewLabel_2.setBounds(10, 44, 72, 101);
		panel.add(lblNewLabel_2);
		
		/*Se creaza un jlabel care daca este apasat( mouse clicked) ne trimite in forumul pentru inregistrare in cazul in care 
		 * utilizatorul doreste sa isi creeze un cont nou.*/
		JLabel lblNewLabel_3 = new JLabel("Don't have an account? Register here");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Register r = new Register();
				r.setVisible(true);
				setVisible(false);
			}
		});
		lblNewLabel_3.setBounds(90, 110, 146, 13);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\Narcis\\Desktop\\poze P3\\flying_airplane_background_colorful_decoration_6827698.jpg"));
		lblNewLabel_4.setBounds(0, 0, 615, 485);
		contentPane.add(lblNewLabel_4);
		Thread CurrentDate = new Thread()
        {
            public void run()
        {
                try {
                    for(;;){
                       //Cu ajutorul functiei GregorianCalendar, putem obtine din sistemul calculatorului toate datele 
                       //necesare pentru luna,an,zi, minut, ora.
                     Calendar cal = new GregorianCalendar();
                     int month = cal.get(Calendar.MONTH);
                     int year = cal.get(Calendar.YEAR);
                     int day = cal.get(Calendar.DAY_OF_MONTH);
                     int minute = cal.get(Calendar.MINUTE);
                     int hour = cal.get(Calendar.HOUR_OF_DAY);
                     int second = cal.get(Calendar.SECOND);
					lblClock.setText(month+1 + "/" + day + "/" + year +"  " + hour + ":" + minute + ":" + second);

                    sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        };
  CurrentDate.start();
		
	}
}
