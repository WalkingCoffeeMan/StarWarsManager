package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Panel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Control.DBConnection;
import Control.FilterController;
import Model.Customer;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class MarketWindow {

	 JFrame frame;
	private JTable productTable;
	private JTable marketTable;
	private DefaultTableModel productModel;
	public static DefaultTableModel marketModel;
	public static DefaultTableModel filterModel;
	public static DBConnection db;
	private Customer loggedCustomer;
	private String picPath;
	public static JTable filterTable;
	public static JTextField idField;
	public static JTextField usernameField;
	public static JTextField sideField;
	public static JTextField fnameField;
	public static JTextField lnameField;
	public static JTextField ageField;
	public static JTextField snumberField;
	public static JTextField planetField;
	private JLabel lblId;
	private JLabel lblUsername;
	private JLabel lblId_2;
	private JLabel lblId_3;
	private JLabel lblId_4;
	private JLabel lblId_5;
	private JLabel lblId_6;
	private JLabel lblId_7;
	private String searchQuery;
	public static JTextField passwordField;
	public static JTextField creditsField;
	private JButton removeButton;
	private JTabbedPane tabbedPane;
	private JLabel showCredits;
	public static JLabel filterMeldung;
	
	
	public MarketWindow(String path,Customer loggedCustomer, String picPath) 
	{
		if(path != null)
		{
			playBackgroundMusic(path);
		}
		
		
		if(loggedCustomer != null)
		{
			this.loggedCustomer = loggedCustomer;
		};
		
		this.picPath = picPath;
		initialize();
		
		initializeTableModels();
		initializeMyProducts();
		
//		initializeMarketPlace();
//		initializeFilterCustomers();
		
		if(loggedCustomer.getId()!=1)
		{
			initializeMarketPlace();
		}
		if(loggedCustomer.getId()==1)
		{
			initializeFilterCustomers();
		}
	
		
		
	}

	private void initialize() 
	{
		frame = new JFrame();

		frame.setSize(1000,300);
		frame.getContentPane().setLayout(null);
		
	   tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 982, 953);
		frame.getContentPane().add(tabbedPane);
	

		
				
				
	
		
		frame.setLocationRelativeTo(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnAusloggen = new JMenu("Logout");
		mnAusloggen.setActionCommand("logout");
		mnAusloggen.addActionListener(new FilterController());
		menuBar.add(mnAusloggen);
		frame.setVisible(true);
		}	
	
	
	
	
	
	public void playBackgroundMusic(String path)
	{


        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;

        ContinuousAudioDataStream loop = null;

        try
        {
            InputStream test = new FileInputStream(path);
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);

        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }
        catch(IOException error)
        {
            System.out.print(error.toString());
        }
        MGP.start(loop);
	}
	
	public void initializeProductsTable()
	{
		
		try
		{
			//normally prepared statements will be used
			String query = "Select product_id,product_name,product_price,product_amount from products where p_customer_id = "+loggedCustomer.getId();
			db = new DBConnection();
			Statement st = db.getCon().createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next())
			{
				productModel.addRow(new Object[] {
						
						rs.getInt(1),
						rs.getString(2),
						rs.getFloat(3),
						rs.getInt(4)
				});
				
			}
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void initializeMarketTable()
	{
		
		
		
		try
		{
			String query = "Select product_id,product_name,product_price,product_amount,picture from products where p_customer_id = 1";
			db = new DBConnection();
			Statement st = db.getCon().createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next())
			{
				marketModel.addRow(new Object[] {
						
						rs.getInt(1),
						rs.getString(2),
						rs.getFloat(3),
						rs.getInt(4),
						rs.getBlob(5)
				});
				
			}
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void initializeSearchTable()
	{
		try
		{
			
				String query = "select * from v_customers where id != 1";


		db = new DBConnection();
		Statement st = db.getCon().createStatement();
		ResultSet rs = st.executeQuery(query);
		System.out.println("Aloha");
		while(rs.next())
		{
			filterModel.addRow(new Object[] {
					
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getInt(7),
					rs.getInt(8),
					rs.getString(9),
					rs.getInt(10),
			});
			
		}
		
	} catch (SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public void initializeMyProducts()
	{

		
		Panel myproducts = new Panel();
		myproducts.setBackground(Color.BLACK);
		tabbedPane.addTab("My Products", null, myproducts, null);
		myproducts.setLayout(null);
		
		JScrollPane scrollProducts = new JScrollPane();
		scrollProducts.setBounds(0, 0, 977, 334);
		myproducts.add(scrollProducts);
		
		productTable = new JTable();
		scrollProducts.setViewportView(productTable);
		productTable.setModel(productModel);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(picPath));
		
		if(loggedCustomer.getId() != 1)
		{
			lblNewLabel.setBounds(90, 378, 659, 558);
		}
		else
		{
			lblNewLabel.setBounds(0,333,977,590);
		}
	
		myproducts.add(lblNewLabel);
		
		initializeProductsTable();
		
		
			
	}
	public void initializeMarketPlace()
	{
		
	
		
		
		Panel pMarketPlace = new Panel();
		pMarketPlace.setBackground(Color.BLACK);
		pMarketPlace.setLayout(null);
		tabbedPane.addTab("Marketplace", null, pMarketPlace, null);
		tabbedPane.setBackgroundAt(1, SystemColor.control);
		
		JScrollPane scrollMarket = new JScrollPane();
		scrollMarket.setBounds(0, 0, 977, 334);
		pMarketPlace.add(scrollMarket);
		
		marketTable = new JTable();
		marketTable.setBackground(SystemColor.window);
		scrollMarket.setViewportView(marketTable);
		marketTable.setModel(marketModel);
	
	
	JButton btnNewButton = new JButton("Buy");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
			
			
		
			String productName = (String.valueOf(marketModel.getValueAt(marketTable.getSelectedRow(),1)));
			float productPrice = (float)marketModel.getValueAt(marketTable.getSelectedRow(),2);
			String picture = String.valueOf(marketModel.getValueAt(marketTable.getSelectedRow(),3));
			
			
			
			
			try 
			{
				
				String getCredits = "select credits from customer where id = "+loggedCustomer.getId();
				
				Statement st1 = db.con.createStatement();
				ResultSet rs = st1.executeQuery(getCredits);
				
				float myCredits = 0;
				while(rs.next())
				{
					myCredits = rs.getFloat(1);
				}
				 
				
				float newCredits = Float.valueOf(myCredits) - productPrice;
				String creditLoss = String.valueOf(newCredits);
				
				String insertQuery = "insert into products values(null,'"+productName+"',"+productPrice+",1,"+loggedCustomer.getId()+",'"+picture+"')";
				
				String updateQuery = "update customer set credits = "+creditLoss+" where id = "+loggedCustomer.getId();
				
				Statement st2 = db.getCon().createStatement();
				
				st2.executeUpdate(insertQuery);
				
				Statement st3 = db.getCon().createStatement();
				
				st3.executeUpdate(updateQuery);
				
				
				
				productModel.setRowCount(0);
				
				initializeProductsTable();
				showCredits();
			
				
				
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	
		}
	});
	btnNewButton.setBackground(Color.BLACK);
	btnNewButton.setForeground(Color.YELLOW);
	btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 28));
	btnNewButton.setBounds(51, 378, 143, 54);
	pMarketPlace.add(btnNewButton);
	
	
	
	
	
	JLabel lblNewLabel_1 = new JLabel("");
	lblNewLabel_1.setIcon(new ImageIcon(picPath));
	lblNewLabel_1.setBounds(90, 378, 659, 558);
	pMarketPlace.add(lblNewLabel_1);
	
    showCredits = new JLabel("Credits:");
	showCredits.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	showCredits.setForeground(Color.YELLOW);
	showCredits.setBounds(761, 378, 204, 54);
	pMarketPlace.add(showCredits);
	
	initializeMarketTable();
	
	showCredits();
		

		
	}
	public void initializeFilterCustomers()
	{
		
	



	
	
	JPanel filterCustomers = new JPanel();
	tabbedPane.addTab("Customers", null, filterCustomers, null);
	filterCustomers.setLayout(null);
	
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(0, 0, 977, 394);
	filterCustomers.add(scrollPane);
	
	filterTable = new JTable();
	filterTable.setModel(filterModel);
	
	scrollPane.setViewportView(filterTable);
	
	idField = new JTextField();
	idField.setBounds(121, 438, 116, 22);
	filterCustomers.add(idField);
	idField.setColumns(10);
	
	usernameField = new JTextField();
	usernameField.setBounds(121, 493, 116, 22);
	filterCustomers.add(usernameField);
	usernameField.setColumns(10);
	
	sideField = new JTextField();
	sideField.setColumns(10);
	sideField.setBounds(121, 609, 116, 22);
	filterCustomers.add(sideField);
	
	fnameField = new JTextField();
	fnameField.setColumns(10);
	fnameField.setBounds(459, 438, 116, 22);
	filterCustomers.add(fnameField);
	
	lnameField = new JTextField();
	lnameField.setColumns(10);
	lnameField.setBounds(459, 493, 116, 22);
	filterCustomers.add(lnameField);
	
	ageField = new JTextField();
	ageField.setColumns(10);
	ageField.setBounds(459, 548, 116, 22);
	filterCustomers.add(ageField);
	
	snumberField = new JTextField();
	snumberField.setColumns(10);
	snumberField.setBounds(459, 609, 116, 22);
	filterCustomers.add(snumberField);
	
	planetField = new JTextField();
	planetField.setColumns(10);
	planetField.setBounds(756, 438, 116, 22);
	filterCustomers.add(planetField);
	
	JButton searchButton = new JButton("Search");
	searchButton.setActionCommand("filter");
	searchButton.setBounds(756, 547, 97, 25);
	filterCustomers.add(searchButton);
	searchButton.addActionListener(new FilterController());
	
	lblId = new JLabel("ID:");
	lblId.setBounds(30, 441, 56, 16);
	filterCustomers.add(lblId);
	
	lblUsername = new JLabel("Username:");
	lblUsername.setBounds(30, 496, 71, 16);
	filterCustomers.add(lblUsername);
	
	lblId_2 = new JLabel("Side:");
	lblId_2.setBounds(30, 612, 56, 16);
	filterCustomers.add(lblId_2);
	
	lblId_3 = new JLabel("First Name:");
	lblId_3.setBounds(310, 441, 78, 16);
	filterCustomers.add(lblId_3);
	
	lblId_4 = new JLabel("Last Name:");
	lblId_4.setBounds(310, 496, 78, 16);
	filterCustomers.add(lblId_4);
	
	lblId_5 = new JLabel("Age:");
	lblId_5.setBounds(310, 551, 56, 16);
	filterCustomers.add(lblId_5);
	
	lblId_6 = new JLabel("Planet:");
	lblId_6.setBounds(653, 441, 56, 16);
	filterCustomers.add(lblId_6);
	
	lblId_7 = new JLabel("Spaceship Number:");
	lblId_7.setBounds(310, 612, 116, 16);
	filterCustomers.add(lblId_7);
	
	passwordField = new JTextField();
	passwordField.setBounds(121, 548, 116, 22);
	filterCustomers.add(passwordField);
	passwordField.setColumns(10);
	
	JLabel lblPassword = new JLabel("Password:");
	lblPassword.setBounds(30, 551, 71, 16);
	filterCustomers.add(lblPassword);
	
	creditsField = new JTextField();
	creditsField.setBounds(756, 493, 116, 22);
	filterCustomers.add(creditsField);
	creditsField.setColumns(10);
	
    JLabel lCredits = new JLabel("Credits:");
	lCredits.setBounds(653, 496, 56, 16);
	filterCustomers.add(lCredits);
	
	JButton resetButton = new JButton("Reset");
	resetButton.setActionCommand("reset");
	resetButton.setBounds(756, 608, 97, 25);
	filterCustomers.add(resetButton);
	
	removeButton = new JButton("Remove");
	removeButton.setBounds(291, 672, 97, 25);
	filterCustomers.add(removeButton);
	removeButton.setActionCommand("remove");
	
	JButton newCustomerButton = new JButton("New Customer");
	newCustomerButton.setBounds(121, 672, 133, 25);
	
	newCustomerButton.setActionCommand("new");
	newCustomerButton.addActionListener(new FilterController());
	filterCustomers.add(newCustomerButton);
	
	JButton updateButton = new JButton("Update");
	updateButton.setBounds(459, 672, 97, 25);
	
	updateButton.addActionListener(new FilterController());
	updateButton.setActionCommand("update");
	filterCustomers.add(updateButton);
	
	filterMeldung = new JLabel("");
	filterMeldung.setFont(new Font("Tahoma", Font.PLAIN, 24));
	filterMeldung.setBounds(102, 750, 545, 135);
	filterCustomers.add(filterMeldung);
	removeButton.addActionListener(new FilterController());
	resetButton.addActionListener(new FilterController());
	
	initializeSearchTable();
	}
	
	public void initializeTableModels()
	{
		filterModel = new DefaultTableModel(
				null,
				new String[] {
					"ID", "Username","Password", "Side", "First Name", "Last Name", "Age", "Spaceship Number", "Planet","Credits"
				}
							);
		
		marketModel = new DefaultTableModel(
				null
			 ,
				new String[] {
						"Product_ID", "Product_Name", "Product_Price", "Product_amount", "Picture"
				});
		

		productModel = new DefaultTableModel(null,
				new String[] 
				{
					"Product_ID", "Product_Name", "Product_Price", "Product_amount"
				}
			);


	}
	public void showCredits()
	{
		String query = "select credits from customer where id ="+loggedCustomer.getId();
		
		try {
			Statement st = db.con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next())
			{
				//System.out.println(rs.getInt(1));
		
				showCredits.setText("Credits: "+rs.getString(1));
			
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
