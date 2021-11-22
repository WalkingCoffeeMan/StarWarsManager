package View;
import java.awt.EventQueue;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;

import Control.DBConnection;
import Control.LoginController;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow {

	


	public static JFrame loginFrame;
	public static JTextField loginField;
	private JLabel lblWelcome;
	public static JPasswordField passwordField;
	public static AudioStream BGM;
	public static JLabel loginFehler ;
	
	
	
	
	public static DBConnection db;
	private JLabel lblNewLabel;
	public LoginWindow()
	{
		initialize();
		playBackgroundMusic();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		loginFrame = new JFrame();
		loginFrame.setTitle("Light Saber Market");
		loginFrame.setLocationRelativeTo(loginFrame);
		loginFrame.setSize(1000,1000);
		loginFrame.getContentPane().setLayout(null);
		loginFrame.getContentPane().setBackground(Color.BLACK);
		 
		loginField = new JTextField();
		loginField.setFont(new Font("Dialog", Font.PLAIN, 24));
		loginField.setBounds(392, 358, 222, 56);
		loginFrame.getContentPane().add(loginField);
		loginField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblUsername.setForeground(Color.YELLOW);
		lblUsername.setBounds(114, 354, 159, 56);
		loginFrame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblPassword.setForeground(Color.YELLOW);
		lblPassword.setBounds(117, 446, 177, 64);
		loginFrame.getContentPane().add(lblPassword);
		
		lblWelcome = new JLabel("Welcome");
		lblWelcome.setForeground(Color.YELLOW);
		lblWelcome.setBounds(377, 129, 512, 184);
		lblWelcome.setFont(new Font("Segoe UI Semilight", Font.BOLD, 53));
		loginFrame.getContentPane().add(lblWelcome);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(392, 458, 222, 56);
		loginFrame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 26));
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setForeground(Color.YELLOW);
		btnLogin.setBounds(337, 591, 113, 64);
		btnLogin.setActionCommand("Login");
		btnLogin.addActionListener(new LoginController());
		loginFrame.getContentPane().add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Segoe UI", Font.BOLD, 26));
		btnExit.setBackground(Color.BLACK);
		btnExit.setForeground(Color.YELLOW);
		btnExit.setActionCommand("Exit");
		btnExit.addActionListener(new LoginController()); 
		
		btnExit.setBounds(501, 591, 113, 64);
		loginFrame.getContentPane().add(btnExit);
		
		loginFehler = new JLabel("");

		 loginFehler.setFont(new Font("Segoe UI", Font.BOLD, 17));
		 loginFehler.setForeground(Color.YELLOW);
		loginFehler.setBounds(314, 693, 557, 119);
		loginFrame.getContentPane().add(loginFehler);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\z173551\\eclipse-workspace\\Project Star Wars\\back - Kopie.jpg"));
		lblNewLabel.setBounds(0, 0, 982, 953);
		loginFrame.getContentPane().add(lblNewLabel);
		//frame.setBounds(100, 100, 450, 300);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		loginFrame.setVisible(true);
		
	}
	
	
	public void playBackgroundMusic()
	{


        AudioPlayer MGP = AudioPlayer.player;
      
        AudioData MD;

        ContinuousAudioDataStream loop = null;

        try
        {
            InputStream test = new FileInputStream("./star.wav");
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
            MGP.start(loop);
            
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
       
    

	}
	
	public JFrame getFrame() {
		return loginFrame;
	}

	public void setFrame(JFrame frame) {
		this.loginFrame = frame;
	}
	
	public AudioStream getBGM()
	{
		return BGM;
		
	}
}
