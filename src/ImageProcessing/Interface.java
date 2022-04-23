package ImageProcessing;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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




public class Interface extends JFrame {
	public JPanel container_vid = new JPanel(); // container of the video panel
	public JPanel container_plate = new JPanel(); // container of the detected plate panel
	
	public videoPanel panel_video = new videoPanel(); // panel containing the video stream
	
	public JPanel panel_plate = new videoPanel(); // panel to display the detected plate and info
	public imagePanel panel_plate_image = new imagePanel(); // panel to display the image of the detected plate inside the plate panel
	private JLabel panel_plate_text = new JLabel();//panel to display a text in the detected plate panel
	
	private JPanel panel_plate_text_container = new JPanel(); //container of the text
	
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
		
		panel_video.setPreferredSize(new Dimension(this.getWidth()-250,this.getHeight()-10));
		
		panel_plate.setLayout(new BoxLayout(panel_plate, BoxLayout.Y_AXIS));
		panel_plate_image.setPreferredSize(new Dimension(250,250));
		panel_plate_image.setMaximumSize(new Dimension(250,250));
		
		panel_plate_text.setText("Detected: ");
		panel_plate_text_container.setMaximumSize(new Dimension(250,50));
		
		panel_plate_text_container.add(panel_plate_text);
		panel_plate_text_container.add(panel_plate_image);
		
		panel_plate.add(panel_plate_text_container);
		panel_plate.add(panel_plate_image);
		panel_plate.add(new JPanel());
		
		container_plate.add(panel_plate);
		container_vid.add(panel_video);
		container_plate.add(container_vid);
		
		
		this.setContentPane(container_plate);
		pack();
		
	 }  
}

