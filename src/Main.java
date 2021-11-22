import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JFrame;

import View.LoginWindow;

public class Main {

	public static void main(String[] args) throws MalformedURLException, IOException
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	

	}

}
