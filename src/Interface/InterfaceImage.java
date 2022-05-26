package Interface;

import java.awt.Color;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import ImageProcessing.Main;
import Panels.LeftButton;
import Panels.RightButton;
import Panels.imagePanel;
import Panels.videoPanel;

public class InterfaceImage extends JFrame{
	public JPanel container_data = new JPanel(); // container of the panel of the image to process 
	public JPanel container_plate = new JPanel(); // container of the detected plate panel
	
	public imagePanel panel_data = new imagePanel(); // panel containing the data stream
	
	public JPanel panel_plate = new JPanel(); // panel to display the detected plate and info
	public imagePanel panel_plate_image_1 = new imagePanel(); // panel to display the image of the detected plate inside the plate panel
	public imagePanel panel_plate_image_2 = new imagePanel(); // panel to display the image2 of the detected plate inside the plate panel
	private JLabel panel_plate_text = new JLabel();//panel to display a text in the detected plate panel
	
	private JPanel panel_plate_text_container = new JPanel(); //container of the text
	
	private JMenuBar menu_Bar = new JMenuBar();
	private JMenu menu= new JMenu("File");	
	private JMenuItem open_file = new JMenuItem("Open File         ");
	
	private LeftButton Left = new LeftButton("<");
	private RightButton Right = new RightButton(">");
	
	public int width=1920;
	public int height=1080;
	public File File;

	public InterfaceImage(){
		if (Main.runImage==1)
			this.setTitle("Interface Twizzy: OpenCv");
		else if(Main.runImageDL==1) 
			this.setTitle("Interface Twizzy: Yolov5");
		this.setSize(width,height);
		this.setVisible(true);
		this.DisplayWindow();
		this.setFocusable(true);
		this.requestFocus();
		this.addWindowListener( new WindowAdapter() {
		      @Override
		      public void windowClosing(WindowEvent we) {
		        if (Main.runImage==1) Main.runImage=0;
		        if (Main.runImageDL==1) Main.runImageDL=0;
		      }
		});
		
	}
	
private void DisplayWindow() {
		
		container_plate.setLayout(new BoxLayout(container_plate, BoxLayout.X_AXIS));
		container_data.setLayout(new BoxLayout(container_data, BoxLayout.X_AXIS));
	
		panel_data.setPreferredSize(new Dimension(this.getWidth()-250,this.getHeight()-20));
		panel_data.setMaximumSize(new Dimension(this.getWidth()-250,this.getHeight()-20));
		
		panel_plate.setLayout(new BoxLayout(panel_plate, BoxLayout.Y_AXIS));
		//panel_plate.setBorder(BorderFactory.createTitledBorder("Panel Detected:"));
		
		panel_plate_text_container.setMaximumSize(new Dimension(250,50));
		
		// plate for image detected 1
		panel_plate_image_1.setPreferredSize(new Dimension(250,250));
		panel_plate_image_1.setMaximumSize(new Dimension(250,250));
		panel_plate_image_1.setBorder( BorderFactory.createLineBorder(new Color (220,20,60), 1));
		// plate for image detected 2
		panel_plate_image_2.setPreferredSize(new Dimension(250,250));
		panel_plate_image_2.setMaximumSize(new Dimension(250,250));
		panel_plate_image_2.setBorder( BorderFactory.createLineBorder(new Color (220,20,60), 1));
		
		//plate for text
		panel_plate_text.setText("Signs Detected: ");
		panel_plate_text.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		panel_plate_text.setForeground(Color.BLACK);
		
		//add
		panel_plate_text_container.add(panel_plate_text);
		panel_plate.add(panel_plate_text_container);
		panel_plate.add(panel_plate_image_1);
		panel_plate.add(panel_plate_image_2);
		
		container_plate.add(panel_plate);
		
		container_data.add(Left);
		container_data.add(panel_data);
		container_data.add(Right);
		
		container_plate.add(container_data);
		
		//menu bar
		menu.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		menu.setForeground(Color.BLACK);
		menu.setBorderPainted(true);
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
	                        setFile(file);
	                    }
	                    else {
	                    	System.out.println("error");
	                    }
	                }
	                
	            });
		
		this.setContentPane(container_plate);
		pack();
		
	}
	//set file name
	public static void setFile(File file) {
		
		if (Main.runImage==1) {
			System.out.println("hey");
			ImageStream.file=file;	
			System.out.println(file);
			ImageStream.filechanged=1;
		}
		if (Main.runImageDL==1) {
			ImageStreamDL.file=file;	
			System.out.println(file);
			ImageStreamDL.filechanged=1;
			
		}
	}
}
