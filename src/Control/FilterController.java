package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import View.MarketWindow;

public class FilterController implements ActionListener
{
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equals("filter"))
		{
			filter();
		
		
		}
		else if(e.getActionCommand().equals("reset"))
		{
			
			reset();
			
		}
		else if(e.getActionCommand().equals("remove"))
		{
			remove();
		}
		else if(e.getActionCommand().equals("new"))
		{
			insert();
		}
		else if(e.getActionCommand().equals("update"))
		{
			updateRow();
		}
		else if(e.getActionCommand().equals("logout"))
		{
			logout();
		}
	
		
	}
	public void filter()
	{
		int count = 0;
		String query = "create or replace view v_customers as Select * from customer where ";
		
		if(!MarketWindow.idField.getText().equals(""))
		{
			count++;
			query = query + "id = "+MarketWindow.idField.getText();
		}
		if(!MarketWindow.usernameField.getText().equals(""))
		{
			
			if(count != 0)
			{
				query = query + " and username = '"+MarketWindow.usernameField.getText()+"'";
			}
			else
			{
				query = query + "username = '"+MarketWindow.usernameField.getText()+"'";
			}
			count++;
		}
		if(!MarketWindow.passwordField.getText().equals(""))
		{
			
			if(count != 0)
			{
				query = query + " and password = '"+MarketWindow.passwordField.getText()+"'";
			}
			else
			{
				query = query + "password = '"+MarketWindow.passwordField.getText()+"'";
			}
			count++;
		}
		if(!MarketWindow.sideField.getText().equals(""))
		{
			
			if(count != 0)
			{
				query = query + " and side = '"+MarketWindow.sideField.getText()+"'";
			}
			else
			{
				query = query + "side = '"+MarketWindow.sideField.getText()+"'";
			}
			count++;
		}
		if(!MarketWindow.fnameField.getText().equals(""))
		{
			
			if(count != 0)
			{
				query = query + " and first_name = '"+MarketWindow.fnameField.getText()+"'";
			}
			else
			{
				query = query + "first_name = '"+MarketWindow.fnameField.getText()+"'";
			}
			count++;
		}
		if(!MarketWindow.lnameField.getText().equals(""))
		{
			
			if(count != 0)
			{
				query = query + " and last_name = '"+MarketWindow.lnameField.getText()+"'";
			}
			else
			{
				query = query + "last_name = '"+MarketWindow.lnameField.getText()+"'";
			}
			count++;
		}
		if(!MarketWindow.ageField.getText().equals(""))
		{
			
			if(count != 0)
			{
				query = query + " and age = "+MarketWindow.ageField.getText();
			}
			else
			{
				query = query + "age = "+MarketWindow.ageField.getText();
			}
			count++;
		}
		if(!MarketWindow.snumberField.getText().equals(""))
		{
			
			if(count != 0)
			{
				query = query + " and spaceship_number = "+MarketWindow.snumberField.getText();
			}
			else
			{
				query = query + "spaceship_number = "+MarketWindow.snumberField.getText();
			}
			count++;
		}
		if(!MarketWindow.planetField.getText().equals(""))
		{
			
			if(count != 0)
			{
				query = query + " and planet = '"+MarketWindow.planetField.getText()+"'";
			}
			else
			{
				query = query + "planet = '"+MarketWindow.planetField.getText()+"'";
			}
			count++;
		}
		if(!MarketWindow.creditsField.getText().equals(""))
		{
			
			if(count != 0)
			{
				query = query + " and credits = "+MarketWindow.creditsField.getText();
			}
			else
			{
				query = query + "credits = "+MarketWindow.creditsField.getText();
			}
			count++;
		}
		
		
		
		try {
				
				MarketWindow.db = new DBConnection();
				Statement st = MarketWindow.db.getCon().createStatement();
				int i = st.executeUpdate(query);
				MarketWindow.filterModel.setRowCount(0);
				MarketWindow.initializeSearchTable();
				
		} catch (SQLException e1) 
		{
			// TODO Auto-generated catch block
			System.out.println("Leer");
		}
		
		
	}
	
	public void reset()
	{
		try
		{
		String query = "create or replace view v_customers as Select * from customer ";
		Statement st = MarketWindow.db.getCon().createStatement();
		int s = st.executeUpdate(query);
		MarketWindow.filterModel.setRowCount(0);
		MarketWindow.initializeSearchTable();
		}
		catch(SQLException er)
		{
			er.printStackTrace();
		}
	}
	
	public void remove()
	{
		try
		{
		String id = String.valueOf(MarketWindow.filterModel.getValueAt(MarketWindow.filterTable.getSelectedRow(),0));
		
		
			String query = "Delete from customer where id ="+id;
			
			Statement st = MarketWindow.db.getCon().createStatement();
			
			int rows = st.executeUpdate(query);
			
			reset();
			MarketWindow.filterMeldung.setText("Customer with ID "+id+" was removed");

	}
	 catch (SQLException e1) {
		// TODO Auto-generated catch block
		System.out.println("Leer");
	}
	catch (IndexOutOfBoundsException e1) 
	{
		// TODO Auto-generated catch block
		System.out.println("Leer");
	}
	}
	
	public void insert()
	{
		
		try
		{
	
		if(!MarketWindow.usernameField.getText().equals("") && !MarketWindow.passwordField.getText().equals("") &&
				!MarketWindow.sideField.getText().equals("") && !MarketWindow.fnameField.getText().equals("") &&
				!MarketWindow.lnameField.getText().equals("") && !MarketWindow.ageField.getText().equals("")&&
				!MarketWindow.snumberField.getText().equals("") && !MarketWindow.planetField.getText().equals("")
				&& !MarketWindow.creditsField.getText().equals(""))
		{
			
	
			String username = MarketWindow.usernameField.getText();
			String password = MarketWindow.passwordField.getText();
			String side = MarketWindow.sideField.getText();
			String fname = MarketWindow.fnameField.getText();
			String lname = MarketWindow.lnameField.getText();
			int age = Integer.valueOf(MarketWindow.ageField.getText());
			
			float snumberfield = Float.valueOf(MarketWindow.snumberField.getText());
			String planet = MarketWindow.planetField.getText();
			float credits = Float.valueOf(MarketWindow.creditsField.getText());
			
			
			String query = "insert into customer values(null,'"+username+"','"+password+"','"+side+"','"+fname+"','"+lname+"',"+age+","+snumberfield+",'"+planet+"',"+credits+")";
			
			
				
				Statement st = MarketWindow.db.con.createStatement();
				int rs = st.executeUpdate(query);
				reset();
			
			

			
			MarketWindow.filterMeldung.setText("New user has been added !");
			
		
		
		
		
	
		
		
		}
		else
		{
			MarketWindow.filterMeldung.setText("Please fill all fields with valid values");
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Leer");
		}
		catch (IndexOutOfBoundsException e1) 
		{
			// TODO Auto-generated catch block
			System.out.println("Leer");
		}
	}
	
	public void updateRow()
	{
		try {
		
		String getID = String.valueOf(MarketWindow.filterTable.getValueAt(MarketWindow.filterTable.getSelectedRow(), 0));
		
		int getRow = MarketWindow.filterTable.getSelectedRow();
		int getColumn = MarketWindow.filterTable.getSelectedColumn();
		String column = null;
		if(getColumn == 1)
		{
			column = "username";
		}
		else if(getColumn == 2)
		{
			column = "password";
		}
		else if(getColumn == 3)
		{
			column = "side";
		}
		else if(getColumn == 4)
		{
			column = "first_name";
		}
		else if(getColumn == 5)
		{
			column = "last_name";
		}
		else if(getColumn == 6)
		{
			column = "age";
		}
		else if(getColumn == 7)
		{
			column = "spaceship_number";
		}
		else if(getColumn == 8)
		{
			column = "planet";
		}
		else if(getColumn == 9)
		{
			column = "credits";
		}
		
		String changeValueString = null;
		float changeValueFloat = 0;
		
		if(getColumn != 7 && getColumn != 8)
		{
			changeValueString = (String) MarketWindow.filterModel.getValueAt(getRow,getColumn);
		}
		else
		{
			changeValueFloat = Float.valueOf((String) MarketWindow.filterModel.getValueAt(getRow,getColumn));
		}
		
		String updateQuery = null;
		
		if(getColumn != 7 && getColumn != 8)
		{
			updateQuery = "update customer set "+column+" = '"+changeValueString+"' where id = "+getID;
		}
		else
		{
			updateQuery = "update customer set "+column+" = "+changeValueFloat+" where id = "+getID;
		}
		
		//System.out.println(updateQuery);
		
		
			Statement st = MarketWindow.db.con.createStatement();
			st.executeUpdate(updateQuery);
			reset();
	
		
		MarketWindow.filterMeldung.setText("Customer with ID "+getID+" has been changed");
		
	
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("Leer");
	}
	catch (IndexOutOfBoundsException e1) 
	{
		// TODO Auto-generated catch block
		System.out.println("Leer");
	}
		
		
		
	}
	public void logout()
	{
				
		System.out.println("adsasdsa<");
	}

}








