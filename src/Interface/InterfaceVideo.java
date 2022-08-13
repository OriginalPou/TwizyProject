package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ImageProcessing.Main;
import Panels.imagePanel;
import Panels.videoPanel;

/*
 * This class is used to create and initialize the user interface for video prcessing 
 */


public class InterfaceVideo extends JFrame  {
	public JPanel container_vid = new JPanel(); // container of the video panel
	public JPanel container_plate = new JPanel(); // container of the detected plate panel
	
	public videoPanel panel_video = new videoPanel(); // panel containing the video stream
	
	public JPanel panel_plate = new JPanel(); // panel to display the detected plate and info
	public imagePanel panel_plate_image_1 = new imagePanel(); // panel to display the image of the detected plate inside the plate panel
	public imagePanel panel_plate_image_2 = new imagePanel(); // panel to display the image2 of the detected plate inside the plate panel
	private JLabel panel_plate_text = new JLabel();//panel to display a text in the detected plate panel
	
	private JPanel panel_plate_text_container = new JPanel(); //container of the text
	
	private JMenuBar menu_Bar = new JMenuBar();
	private JMenu menu= new JMenu("File");	
	private JMenuItem open_file = new JMenuItem("Open File         ");
	
	public int width=1920;
	public int height=1080;
	public File File;

	public InterfaceVideo(){
		if (Main.getRunVideo()==1)
			this.setTitle("Interface Twizzy: Traitement des videos");
		else if (Main.getRunVideoDL()==1)
			this.setTitle("Interface Twizzy: Traitement des video - DeepLearning");
		this.setSize(width,height);
		this.setVisible(true);
		this.DisplayWindow();
		this.addWindowListener( new WindowAdapter() {
		      @Override
		      public void windowClosing(WindowEvent we) {
		    		  Main.setRunVideo(0);
		    		  Main.setRunVideoDL(0);
		      }
		});
	}
	 
	// Display the interface video JFrame
	private void DisplayWindow() {
		
		container_plate.setLayout(new BoxLayout(container_plate, BoxLayout.X_AXIS));
		container_vid.setLayout(new BoxLayout(container_vid, BoxLayout.X_AXIS));
		
		panel_video.setPreferredSize(new Dimension(this.getWidth()-250,this.getHeight()-20));
		//panel_video.setBorder(BorderFactory.createTitledBorder("Video Processing"));
		
		panel_plate.setLayout(new BoxLayout(panel_plate, BoxLayout.Y_AXIS));
	
		panel_plate_text_container.setMaximumSize(new Dimension(250,70));
		
		// plate for image detected 1
		panel_plate_image_1.setPreferredSize(new Dimension(250,250));
		panel_plate_image_1.setMaximumSize(new Dimension(250,250));
		panel_plate_image_1.setBorder( BorderFactory.createLineBorder(new Color (220,20,60), 1));
		
		// plate for image detected 2
		panel_plate_image_2.setPreferredSize(new Dimension(250,250));
		panel_plate_image_2.setMaximumSize(new Dimension(250,250));
		panel_plate_image_2.setBorder( BorderFactory.createLineBorder(new Color (220,20,60), 1));	
		
		//text
		panel_plate_text.setText("Signs Detected: ");
		panel_plate_text.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		panel_plate_text.setForeground(Color.BLACK);
		
	
		panel_plate_text_container.add(panel_plate_text);
		
		panel_plate.add(panel_plate_text_container);
		panel_plate.add(panel_plate_image_1);
		panel_plate.add(panel_plate_image_2);

		container_plate.add(panel_plate);
		
		container_vid.add(panel_video);
		container_plate.add(container_vid);
		
		//change bkg color RGB
		//panel_plate_text_container.setBackground (new Color(245,245,245)); 
		container_plate.setBackground (new Color(220,220,220)); 
		
		menu.add(open_file);
		menu_Bar.add(menu);
		setJMenuBar(menu_Bar);
		menu.addSeparator();
		
		open_file.addActionListener(
	            new ActionListener() {
	                private Component frame;
	                @Override
	                public void actionPerformed(ActionEvent enterPress) {
	                    JFileChooser chooser = new JFileChooser();
	                    File file;
	                    chooser.setCurrentDirectory(new java.io.File("."));
	                    chooser.setSelectedFile(new File(""));
	                    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	                    if (chooser.showOpenDialog(frame) == JFileChooser.OPEN_DIALOG) {
	                        file = chooser.getSelectedFile();
	                        try {
								Filename.initiateFilename(file);
							} catch (Exception e) {
								e.printStackTrace();
							}  
	                    }
	                    else {
	                    	System.out.println("error");
	                    }
	                }
	            });
		
		this.setContentPane(container_plate);
		pack();
	 }

}

