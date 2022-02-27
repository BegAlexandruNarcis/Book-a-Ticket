import java.awt.EventQueue;

import java.awt.*;
import javax.swing.*;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class flight extends JInternalFrame {
	private JTextField aircraft;
	private JTextField flightid;
	private JTextField airline;
	private JTextField deptime;
	private JTextField date;
	private JTextField arrtime;
	private JTextField pilot;
	private JTextField copilot;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					flight frame = new flight();
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
	private JTextField gate;
	private JTextField price;
	
	
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
	public flight() {
		
		
		
		
		Connect();
		setClosable(true);
		setVisible(true);
		setBounds(100, 100, 861, 438);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 855, 411);
		getContentPane().add(panel);
		
		JComboBox txtcombo = new JComboBox();
		txtcombo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtcombo.setModel(new DefaultComboBoxModel(new String[] {"Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic (CAR)", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo, Democratic Republic of the", "Congo, Republic of the", "Costa Rica", "Cote d'Ivoire", "Croatia", "Cuba", "Cyprus", "Czechia", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "North Macedonia", "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates (UAE)", "United Kingdom (UK)", "United States of America (USA)", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City (Holy See)", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"}));
		txtcombo.setBounds(168, 248, 181, 21);
		panel.add(txtcombo);
		
		JComboBox arrival = new JComboBox();
		arrival.setFont(new Font("Tahoma", Font.PLAIN, 12));
		arrival.setModel(new DefaultComboBoxModel(new String[] {"Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic (CAR)", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo, Democratic Republic of the", "Congo, Republic of the", "Costa Rica", "Cote d'Ivoire", "Croatia", "Cuba", "Cyprus", "Czechia", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "North Macedonia", "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates (UAE)", "United Kingdom (UK)", "United States of America (USA)", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City (Holy See)", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"}));
		arrival.setBounds(168, 292, 181, 21);
		panel.add(arrival);
		
		JLabel sadasdad = new JLabel("Flight ID:");
		sadasdad.setFont(new Font("Tahoma", Font.BOLD, 12));
		sadasdad.setBounds(51, 100, 78, 13);
		panel.add(sadasdad);
		
		JLabel lblNewLabel_1 = new JLabel("Aircraft:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(51, 136, 102, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("Airline Name:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(51, 176, 95, 13);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Pilot:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(51, 213, 78, 13);
		panel.add(lblNewLabel_5);
		
		aircraft = new JTextField();
		aircraft.setColumns(10);
		aircraft.setBounds(168, 134, 181, 19);
		panel.add(aircraft);
		
		flightid = new JTextField();
		flightid.setColumns(10);
		flightid.setBounds(168, 98, 181, 19);
		panel.add(flightid);
		
		airline = new JTextField();
		airline.setColumns(10);
		airline.setBounds(168, 174, 181, 19);
		panel.add(airline);
		
		JLabel lblNewLabel_6 = new JLabel("Departure Time:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(432, 101, 108, 13);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_8 = new JLabel("Date:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_8.setBounds(432, 176, 63, 13);
		panel.add(lblNewLabel_8);
		
		deptime = new JTextField();
		deptime.setColumns(10);
		deptime.setBounds(545, 98, 181, 19);
		panel.add(deptime);
		
		date = new JTextField();
		date.setColumns(10);
		date.setBounds(545, 174, 181, 19);
		panel.add(date);
		
		JButton Add = new JButton("Add");
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
				String flightidd = flightid.getText();
				String aircraftt = aircraft.getText();
				String airlinee = airline.getText();
				String deptimee= deptime.getText();
				String datee = date.getText();
				String arrtimee = arrtime.getText();
				String pilott = pilot.getText();
				String departuree = txtcombo.getSelectedItem().toString();
				String arrivall = arrival.getSelectedItem().toString();
				String copilott = copilot.getText();
				String gatee = gate.getText();
				String pricee = price.getText();
				
				PreparedStatement pst = con.prepareStatement("insert into flights(flightno,gofrom,goto,aircrafttype,pilot,copilot,airline,date,departtime,arrtime,gate,price)values(?,?,?,?,?,?,?,?,?,?,?,?)");
				
				pst.setString(1, flightidd);
	            pst.setString(2, departuree);
	            pst.setString(3, arrivall);
	            pst.setString(4, aircraftt);
	            pst.setString(5, pilott);
	            pst.setString(6, copilott);
	            pst.setString(7, airline);
	            pst.setString(8, datee);
	            pst.setString(9, deptimee);
	            pst.setString(10, arrtimee);
	            pst.setString(11, gatee);
	            pst.setString(11, price);
	            pst.executeUpdate();
	            
	            /*Seteaza text-ul din casutele text pe null*/
	            flightid.setText("");
	            aircraft.setText("");
	            airline.setText("");
	            deptime.setText("");
	            date.setText("");
	            arrtime.setText("");
	            pilot.setText("");

	            copilot.setText("");
	            gate.setText("");
	            price.setText("");
	            copilot.requestFocus();
				
				} catch (SQLException ex) {
					ex.printStackTrace();
		        }
			}
		});
		Add.setFont(new Font("Tahoma", Font.BOLD, 12));
		Add.setBounds(441, 359, 100, 42);
		panel.add(Add);
		
		JButton clear = new JButton("Clear");
		clear.setFont(new Font("Tahoma", Font.BOLD, 12));
		clear.setBounds(610, 359, 100, 42);
		panel.add(clear);
		
		JLabel lblNewLabel_6_1 = new JLabel("Arrival Time:");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6_1.setBounds(432, 136, 95, 13);
		panel.add(lblNewLabel_6_1);
		
		arrtime = new JTextField();
		arrtime.setColumns(10);
		arrtime.setBounds(545, 136, 181, 19);
		panel.add(arrtime);
		
		pilot = new JTextField();
		pilot.setColumns(10);
		pilot.setBounds(168, 207, 181, 19);
		panel.add(pilot);
		
		JLabel lblNewLabel_5_1 = new JLabel("Departure:");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5_1.setBounds(51, 251, 78, 13);
		panel.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_2 = new JLabel("Arrival:");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5_2.setBounds(51, 295, 78, 13);
		panel.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_8_1 = new JLabel("Co-Pilot");
		lblNewLabel_8_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_8_1.setBounds(432, 213, 63, 13);
		panel.add(lblNewLabel_8_1);
		
		copilot = new JTextField();
		copilot.setColumns(10);
		copilot.setBounds(545, 211, 181, 19);
		panel.add(copilot);
		
		
		
		JLabel lblNewLabel_8_1_1 = new JLabel("Gate");
		lblNewLabel_8_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_8_1_1.setBounds(432, 247, 63, 13);
		panel.add(lblNewLabel_8_1_1);
		
		gate = new JTextField();
		gate.setColumns(10);
		gate.setBounds(545, 245, 181, 19);
		panel.add(gate);
		
		JLabel lblNewLabel_8_1_1_1 = new JLabel("Price");
		lblNewLabel_8_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_8_1_1_1.setBounds(432, 291, 63, 13);
		panel.add(lblNewLabel_8_1_1_1);
		
		price = new JTextField();
		price.setColumns(10);
		price.setBounds(545, 289, 181, 19);
		panel.add(price);

	}

}
