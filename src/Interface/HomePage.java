package Interface;

import java.awt.Dimension;




import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import java.awt.Component;
import Panels.imageButton;
import Panels.imageButtonDL;
import Panels.videoButton;
import Panels.videoButtonDL;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class HomePage extends JFrame{
	
	public imageButton button_image; //button to run image processing
	public videoButton button_video;//button to run video processing
	public imageButtonDL button_image_DL; //button to run image processing
	public videoButtonDL button_video_DL;//button to run video processing
	private JPanel container_button = new JPanel(); //container for the buttons
	private JPanel container_button_DL = new JPanel(); //container for the DeepLearning buttons 
	private JLabel panel_text = new JLabel();//panel to display a text in the detected plate panel
	private JLabel panel_text_DL = new JLabel();
	public JSplitPane splitPane = new JSplitPane();
	
	public int width=1920;
	public int height=1080;
	
	public HomePage(){
		this.setTitle("Interface Twizzy: Page d'acceuil");
		this.setSize(width,height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void DisplayHomePage() {
		container_button.setLayout(new BoxLayout(container_button, BoxLayout.Y_AXIS));
		container_button_DL.setLayout(new BoxLayout(container_button_DL, BoxLayout.Y_AXIS));
		
		// Home page text label
		panel_text.setText("Traffic Signs Detection with OpenCv");
		panel_text.setFont(new Font(Font.SERIF, Font.PLAIN, 40));
		//panel_text.setForeground(new Color(255,255,255));
		panel_text.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// DeepLearning text label
		panel_text_DL.setText("Traffic Signs Detection with yoloV5 ");
		panel_text_DL.setFont(new Font(Font.SERIF, Font.PLAIN, 40));
		//panel_text.setForeground(new Color(255,255,255));
		panel_text_DL.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//buttons
		button_image=new imageButton(" Detection on images ");
		button_video=new videoButton(" Detection on videos ");
	
		//buttons DL
		button_image_DL=new imageButtonDL(" Detection on images ");
		button_video_DL=new videoButtonDL(" Detection on videos ");
		
		//add text
		container_button.add(Box.createRigidArea(new Dimension(80, 80)));
		container_button.add(panel_text);
		
		//add button 1
		container_button.add(Box.createRigidArea(new Dimension(80, 80)));
		button_image.setFont(new Font(Font.SERIF, Font.BOLD , 40));
		button_image.setAlignmentX(Component.CENTER_ALIGNMENT);
		container_button.add(button_image);
		
		//add button 2
		container_button.add(Box.createRigidArea(new Dimension(50, 50)));
		button_video.setFont(new Font(Font.SERIF, Font.BOLD, 40));
		button_video.setAlignmentX(Component.CENTER_ALIGNMENT);
		container_button.add(button_video);
		
		//change bkg color RGB
		container_button.setBackground (new Color(255,255,255)); 
		
		
		// add DL text
		container_button_DL.add(Box.createRigidArea(new Dimension(80, 80)));
		container_button_DL.add(panel_text_DL);
		
		//add DL button 1
		container_button_DL.add(Box.createRigidArea(new Dimension(80, 80)));
		button_image_DL.setFont(new Font(Font.SERIF, Font.BOLD , 40));
		button_image_DL.setAlignmentX(Component.CENTER_ALIGNMENT);
		container_button_DL.add(button_image_DL);
				
		//add button 2
		container_button_DL.add(Box.createRigidArea(new Dimension(50, 50)));
		button_video_DL.setFont(new Font(Font.SERIF, Font.BOLD, 40));
		button_video_DL.setAlignmentX(Component.CENTER_ALIGNMENT);
		container_button_DL.add(button_video_DL);
		
		//change bkg color RGB
		container_button.setBackground (new Color(255,255,255)); //change bkg color RGB

		//split the frame
        splitPane.setSize(width, height);
        splitPane.setDividerSize(0);
        splitPane.setDividerLocation(width);
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(container_button);
        splitPane.setRightComponent(container_button_DL);
		
        this.add(splitPane);
	
	}
	

}
