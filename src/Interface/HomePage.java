package Interface;

import java.awt.Dimension;




import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import Panels.imageButton;
import Panels.videoButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class HomePage extends JFrame{
	
	public JPanel panel_button = new JPanel();
	public imageButton button_image; //button to run image processing
	public videoButton button_video;//button to run video processing
	private JPanel container_button = new JPanel(); //container for the buttons
	private JLabel panel_text = new JLabel();//panel to display a text in the detected plate panel
	private JPanel panel_text_container = new JPanel(); //container of the text
	
	
	public int width=800;
	public int height=600;
	
	public HomePage(){
		this.setTitle("Interface Twizzy: Home Page");
		this.setSize(width,height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	
		
	}
	
	public void DisplayHomePage() {
		container_button.setLayout(new BoxLayout(container_button, BoxLayout.Y_AXIS));
		
		panel_button.setPreferredSize(new Dimension(100,this.getHeight()));
		panel_button.setMaximumSize(new Dimension(100,this.getHeight()));
		
		
		// Home page text label
		panel_text.setText("Road Sign Detection");
		panel_text.setFont(new Font(Font.SERIF, Font.PLAIN, 85));
		//panel_text.setForeground(new Color(255,255,255));
		panel_text.setAlignmentX(Component.CENTER_ALIGNMENT);
	
		//buttons
		button_image=new imageButton(" Image Processing ");
		button_video=new videoButton(" Video Processing ");
		
		//add text
		container_button.add(Box.createRigidArea(new Dimension(20, 20)));
		container_button.add(panel_text);
		
		//add button 1
		container_button.add(Box.createRigidArea(new Dimension(80, 80)));
		button_image.setFont(new Font(Font.SERIF, Font.BOLD , 40));
		button_image.setForeground(new Color(255,255,255));
		button_image.setAlignmentX(Component.CENTER_ALIGNMENT);
		container_button.add(button_image);
		
		//add button 2
		container_button.add(Box.createRigidArea(new Dimension(50, 50)));
		button_video.setFont(new Font(Font.SERIF, Font.BOLD, 40));
		button_video.setForeground(new Color(255,255,255));
		button_video.setAlignmentX(Component.CENTER_ALIGNMENT);
		container_button.add(button_video);
		container_button.setBackground (new Color(95,158,160)); //change bkg color RGB
		
		this.setContentPane(container_button);
	
	}
	

}
