package ImageProcessing;

import java.awt.Color;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;

import ImageProcessing.imagePanel;
import ImageProcessing.videoPanel;
import ImageProcessing.RunButton1;
import ImageProcessing.RunButton2;




public class Interface extends JFrame  {
	public JPanel container_vid = new JPanel(); // container of the video panel
	public JPanel container_plate = new JPanel(); // container of the detected plate panel
	private JPanel container_button = new JPanel(); //container for the buttons
	
	public videoPanel panel_video = new videoPanel(); // panel containing the video stream
	
	public JPanel panel_plate = new videoPanel(); // panel to display the detected plate and info
	public imagePanel panel_plate_image = new imagePanel(); // panel to display the image of the detected plate inside the plate panel
	private JLabel panel_plate_text = new JLabel();//panel to display a text in the detected plate panel
	
	private JPanel panel_plate_text_container = new JPanel(); //container of the text
	
	public JPanel panel_button = new JPanel();//panel for buttons
	
	public RunButton1 button_video1; //button to run video1 
	public RunButton2 button_video2;//button to run video2 
	
	public int width=1300;
	public int height=600;

	public Interface(){
		this.setTitle("Interface Twizzy");
		this.setSize(width,height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.DisplayWindow();
	}
	 
		
	private void DisplayWindow() {
		
		container_plate.setLayout(new BoxLayout(container_plate, BoxLayout.X_AXIS));
		container_vid.setLayout(new BoxLayout(container_vid, BoxLayout.X_AXIS));
		container_button.setLayout(new BoxLayout(container_button, BoxLayout.Y_AXIS));
		
		panel_video.setPreferredSize(new Dimension(this.getWidth()-250,this.getHeight()-10));
		//panel_video.setBorder(BorderFactory.createTitledBorder("Video Processing"));
		
		panel_plate.setLayout(new BoxLayout(panel_plate, BoxLayout.Y_AXIS));
		
		panel_plate_image.setPreferredSize(new Dimension(250,250));
		panel_plate_image.setMaximumSize(new Dimension(250,250));
		
		panel_plate_text.setText("Panel Detected: ");
		panel_plate_text_container.setMaximumSize(new Dimension(250,50));
		
		panel_button.setPreferredSize(new Dimension(110,this.getHeight()));
		panel_button.setMaximumSize(new Dimension(110,this.getHeight()));
		panel_button.setBorder(BorderFactory.createTitledBorder("Choose Data:"));
		//panel2.setBorder(BorderFactory.createRaisedBevelBorder());
		
		panel_plate_text_container.add(panel_plate_text);
		panel_plate_text_container.add(panel_plate_image);
		panel_plate.add(panel_plate_text_container);
		panel_plate.add(panel_plate_image);
		panel_plate.add(new JPanel());
		container_plate.add(panel_button);
		container_plate.add(panel_plate);
		
		container_vid.add(panel_video);
		container_plate.add(container_vid);
		
		
		button_video1=new RunButton1("video1");
		button_video2=new RunButton2("video2");
		
		container_button.add(Box.createRigidArea(new Dimension(20, 20)));
		container_button.add(button_video1);
		container_button.add(Box.createRigidArea(new Dimension(10, 10)));
		container_button.add(button_video2);
		
		panel_button.add(container_button);
		
		this.setContentPane(container_plate);
		pack();
		
	 }  
	
}

