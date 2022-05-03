package Interface;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import Panels.imagePanel;
import Panels.videoPanel;

public class InterfaceImage extends JFrame{
	public JPanel container_data = new JPanel(); // container of the panel of the image to process 
	public JPanel container_plate = new JPanel(); // container of the detected plate panel
	
	public imagePanel panel_data = new imagePanel(); // panel containing the data stream
	
	public JPanel panel_plate = new videoPanel(); // panel to display the detected plate and info
	public imagePanel panel_plate_image_1 = new imagePanel(); // panel to display the image of the detected plate inside the plate panel
	public imagePanel panel_plate_image_2 = new imagePanel(); // panel to display the image2 of the detected plate inside the plate panel
	private JLabel panel_plate_text = new JLabel();//panel to display a text in the detected plate panel
	
	private JPanel panel_plate_text_container = new JPanel(); //container of the text
	
	private JMenuBar menu_Bar = new JMenuBar();
	private JMenu menu= new JMenu("File");	
	private JMenuItem open_file = new JMenuItem("Open File ...");
	
	public int width=1300;
	public int height=600;
	public File File;


	public InterfaceImage(){
		this.setTitle("Interface Twizzy: Image processing");
		this.setSize(width,height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.DisplayWindow();
		
	}
	
private void DisplayWindow() {
		
		container_plate.setLayout(new BoxLayout(container_plate, BoxLayout.X_AXIS));
		container_data.setLayout(new BoxLayout(container_data, BoxLayout.X_AXIS));
	
		panel_data.setPreferredSize(new Dimension(this.getWidth()-250,this.getHeight()-20));
		
		panel_plate.setLayout(new BoxLayout(panel_plate, BoxLayout.Y_AXIS));
		//panel_plate.setBorder(BorderFactory.createTitledBorder("Panel Detected:"));
		
		// plate for image detected 1
		panel_plate_image_1.setPreferredSize(new Dimension(250,250));
		panel_plate_image_1.setMaximumSize(new Dimension(270,270));
		panel_plate_image_1.setBorder(BorderFactory.createTitledBorder("panel 1:"));
		// plate for image detected 2
		panel_plate_image_2.setPreferredSize(new Dimension(250,250));
		panel_plate_image_2.setMaximumSize(new Dimension(250,250));
		panel_plate_image_2.setBorder(BorderFactory.createTitledBorder("panel 2:"));
		
		panel_plate_text.setText("Panel Detected: ");
		panel_plate_text_container.setMaximumSize(new Dimension(250,50));
		
		panel_plate_text_container.add(panel_plate_text);
		panel_plate_text_container.add(panel_plate_image_1);
		panel_plate_text_container.add(panel_plate_image_2);
		panel_plate.add(panel_plate_text_container);
		panel_plate.add(panel_plate_image_1);
		panel_plate.add(panel_plate_image_2);
		panel_plate.add(new JPanel());
		//container_plate.add(panel_button);
		container_plate.add(panel_plate);
		
		container_data.add(panel_data);
		container_plate.add(container_data);

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
	public void setFile(File file) {
			ImageStream.file=file;	
			System.out.println(file);
			ImageStream.filechanged=1;
			//System.out.println(file);
	}
}
