package Control;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import Model.Customer;

import View.LoginWindow;
import View.MarketWindow;
import sun.audio.AudioPlayer;

public class LoginController implements ActionListener
{

	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
	
		if(e.getActionCommand().equals("Exit"))
		{
			System.exit(0);
		}
		else if(e.getActionCommand().equals("Login"))
		{
			
			
			LoginWindow.db = new DBConnection();
			if(LoginWindow.db.login() == 1)
			{
				String picPath = null;
				String musicPath = null;
				LoginWindow.loginFrame.dispose();
				LoginWindow.db.createSession();
				
				if(DBConnection.currentUser.getSide().equals("Sith"))
				{
					musicPath = "./imperial.wav";
					picPath = "C:\\\\Users\\\\z173551\\\\eclipse-workspace\\\\Project Star Wars\\\\darthvaderback.jpg";
				}
				else if(DBConnection.currentUser.getSide().equals("Jedi"))
				{
					musicPath = "./light.wav";
					picPath = "C:\\\\Users\\\\z173551\\\\eclipse-workspace\\\\Project Star Wars\\\\jedi6.jpg";
				}
				else 
				{
					picPath = "C:\\\\Users\\\\z173551\\\\eclipse-workspace\\\\Project Star Wars\\\\back.jpg";
				}
				AudioPlayer.player.stop(LoginWindow.BGM);
				MarketWindow mw = new MarketWindow(musicPath,DBConnection.currentUser,picPath);
			}
			else
			{
				LoginWindow.loginFehler.setText("Username or Password is wrong");
			}
		}
		
	}
	
	

}
