package Control;

import java.sql.*;

import com.mysql.cj.protocol.Resultset;

import Model.Customer;
import View.LoginWindow;


public class DBConnection 
{
	

	public String url = "jdbc:mysql://localhost:3306/starwars?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin";
	public String user = "root";
	public String password = "root";
	public Connection con;
	
	public static Customer currentUser;
	
	public String enteredUsername;      
	
	
	public DBConnection()
	{
		establishConnection();
		
	}
	
	
	public void establishConnection()
	{

		try
		{
			
			  con = DriverManager.getConnection(url,user,password);
		
			 System.out.println("Datenbankzugriff");
			
						
		}
		catch(SQLException er)
		{
			er.printStackTrace();
		}
	}
	
	public int login()
	{
		 enteredUsername = LoginWindow.loginField.getText();
		String enteredPassword = String.copyValueOf(LoginWindow.passwordField.getPassword());
		
		
		try 
		{
			String query = "Select count(*) from customer where username = '"+enteredUsername+"' and password = '"+enteredPassword+"'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				return rs.getInt(1);
				
			}
			
			rs.close();
			stmt.close();
			
			
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public void createSession()
	{
		
		try 
		{
			String data = "Select id,first_name,last_name,side from customer where username ='"+enteredUsername+"'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(data);
			
			while(rs.next())
			{
				currentUser = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
			}
			
			
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Connection getCon() {
		return con;
	}


	public void setCon(Connection con) {
		this.con = con;
	}


	public static Customer getCurrentUser() {
		return currentUser;
	}


	public static void setCurrentUser(Customer currentUser) {
		DBConnection.currentUser = currentUser;
	}


	public String getEnteredUsername() {
		return enteredUsername;
	}


	public void setEnteredUsername(String enteredUsername) {
		this.enteredUsername = enteredUsername;
	}
	
	
	
	

}
