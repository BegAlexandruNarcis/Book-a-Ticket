import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.Box;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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

	
	public Main() {
		
		setLocation(0,0);
		/* Seteaza titlul cu numele Easy Airline si creeaza un contentPane*/
		setTitle("Easy Airline");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1607, 953);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*Creearea unui Jpanel */
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1592, 1050);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 1593, 852);
		panel.add(desktopPane);
		
		/*Meniul principal in care se aflta 4 butoane (customer,tickets,flight,user )*/
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 231, 26);
		desktopPane.add(menuBar);
		
		/*Item-ul Customer din Meniu */
		JMenu mnNewMenu = new JMenu("Customer");
		menuBar.add(mnNewMenu);
		
		/*Sub item-ul lui Customer care ne trimite la adaugarea unui cumparator pentru un ticket */
		JMenuItem mntmNewMenuItem = new JMenuItem("Add Customer");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer cust = new Customer();
				panel.add(cust);
				cust.setLocation(5,40);
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		/* Sub item-ul lui Customer care ne trimite la cautarea unui cumparator de ticket de zbor */
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Search Customer");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchCustomer cust2 = new SearchCustomer();
				panel.add(cust2);
				cust2.setLocation(5,40);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		menuBar.add(horizontalStrut);
		
		/*Item-ul Tickets din meniu care se creeaza un nou booking al unui ticket*/
		JMenu mnNewMenu_1 = new JMenu("Tickets");
		menuBar.add(mnNewMenu_1);
		
		/* Sub item-ul lui Ticket care ne trimite pentru a face book unui zbor*/
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Book Ticket");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ticket t = new Ticket();
				panel.add(t);
				t.setLocation(5,40);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		menuBar.add(horizontalStrut_1);
		
		/*Item-ul Flight din meniu in care se aflta zborurile actuale*/
		JMenu mnNewMenu_2 = new JMenu("Flight");
		menuBar.add(mnNewMenu_2);
		
		/*Sub item-ul lui Flight care ne trimite pentru a adauga un nou zbor in toate zborurile existente */
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Add Flight");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flight f = new flight();
				panel.add(f);
				f.setLocation(5,40);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_3);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		menuBar.add(horizontalStrut_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1542, 842);
		desktopPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblClock = new JLabel("New label");
		lblClock.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblClock.setForeground(Color.WHITE);
		lblClock.setBounds(1380, 0, 162, 40);
		panel_1.add(lblClock);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 1542, 842);
		panel_1.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Narcis\\Desktop\\poze P3\\plane-airfield-1.jpg"));
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
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
