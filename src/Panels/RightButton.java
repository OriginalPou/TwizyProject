package Panels;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import ImageProcessing.Main;
import Interface.ImageStream;
import Interface.ImageStreamDL;
import Interface.InterfaceImage;


public class RightButton extends JButton implements MouseListener{
	public RightButton (String name)  {
		this.setText("  "+name+"  ");
		this.addMouseListener(this);
		//this.setBorderPainted(false);
		this.setFocusPainted(false);
		//this.setContentAreaFilled(false);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		this.setBackground(Color.WHITE);
	    this.setForeground(Color.BLACK);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (Main.runImage==1) {
		String file_name=ImageStream.file.toString();
		int i= ImageStream.getIndex(file_name);
		i++;
		if ((i==11)&&(file_name.contains(".jpg"))) { // return to first image
			i=1;
		}
		if ((i==52)&&(file_name.contains(".png"))) { // return to first image
			i=1;
		}
		file_name= ImageStream.setIndex(i,file_name);
		InterfaceImage.setFile( new File(file_name));
		}
		//Deeplearning 
		if (Main.runImageDL==1) {
			String file_name=ImageStreamDL.file.toString();
			int i= ImageStreamDL.getIndex(file_name);
			i++;
			if ((i==11)&&(file_name.contains(".jpg"))) { // return to first image
				i=1;
			}
			if ((i==52)&&(file_name.contains(".png"))) { // return to first image
				i=1;
			}
			file_name= ImageStreamDL.setIndex(i,file_name);
			//file_name="Images/p10.jpg";
			InterfaceImage.setFile( new File(file_name));
			
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
