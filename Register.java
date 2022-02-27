import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import java.awt.Color;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField txtusername;
    private JPasswordField txtpasswd;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		Connect();
		/*Creearea unui contentpane cu numele easy airline*/
		setTitle("Easy Airline");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblClock = new JLabel("New label");
		lblClock.setBounds(0, 0, 147, 29);
		contentPane.add(lblClock);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setLayout(null);
		panel.setBounds(52, 154, 475, 210);
		contentPane.add(panel);
		
		/*Label-ul username pentru vizualizare, langa se afla un jtextbox pentru inserare*/
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(106, 26, 100, 15);
		panel.add(lblNewLabel_1);
		
		/*Label-ul password pentru vizualizare, langa se afla un jtextbox pentru inserare*/
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(106, 72, 130, 15);
		panel.add(lblNewLabel);
		
		/*Creearea textfield-ului username in care se va introduce numele de utilizator*/
		txtusername = new JTextField();
		txtusername.setColumns(10);
		txtusername.setBounds(263, 22, 184, 19);
		panel.add(txtusername);
		
		/*Creearea textfield-ului password in care se va introduce parola*/
		txtpasswd = new JPasswordField();
		txtpasswd.setBounds(263, 72, 184, 19);
		panel.add(txtpasswd);
		
		/*Crearea butonului de register */
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					/*In baza de date se insereaza elementele din setstring-urile 1 si 2(username si password) */
		            pst = con.prepareStatement("insert into user(username, password)values(?,?)");

		            /*Se citeste din field-urile username si password text-ul si mai apoi se face un execute update care insereaza in baza
		             * de date actuala*/
		            pst.setString(1, txtusername.getText());
		            pst.setString(2, String.valueOf(txtpasswd.getPassword()));
		            pst.executeUpdate();
		            /*Se afiseaza text-ul Registered*/
		            JOptionPane.showMessageDialog(null, "Registered");
		            
		            /*Se seteaza forumul de register pentru a nu mai fi vazut si apare form-ul de login*/
		            setVisible(false);
		            Login l = new Login();
		            l.setVisible(true);
		           

		        } catch (SQLException ex) {
		            Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

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
		
		/*Butonul back ne trimite inapoi la form-ul de login pentru a ne putea inregistra prin ascunderea lui si 
		 * deschiderea form-ului de login*/
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login();
				l.setVisible(true);
				setVisible(false);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExit.setBounds(342, 147, 105, 35);
		panel.add(btnExit);
		
		/*Se creaza un jlabel, iar prin ajutorul functiei Image si setIcon, se introduce o poza cu un lacat din folderul img*/
		JLabel lblNewLabel_2 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/lock.png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(img));
		lblNewLabel_2.setBounds(10, 10, 72, 101);
		panel.add(lblNewLabel_2);
		
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
