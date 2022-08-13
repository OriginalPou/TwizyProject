package Panels;

import java.awt.Color;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import ImageProcessing.Main;
import Interface.Filename;
import Interface.ImageStream;
import Interface.ImageStreamDL;
import Interface.InterfaceImage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class LeftButton extends JButton implements  MouseListener{
	
	public LeftButton (String name)  {
		
		this.addMouseListener(this);
		
		this.setText("  "+name+"  ");
		//this.setBorderPainted(false);
		this.setFocusPainted(false);
		//this.setContentAreaFilled(false);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		this.setBackground(Color.WHITE);
	    this.setForeground(Color.BLACK);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		// if working on basic part
		if (Main.getRunImage()==1) {
			Filename.getPreviousImage();
		}
		//if working on Deeplearning part
		if (Main.getRunImageDL()==1) {
			String file_name=ImageStreamDL.file.toString();
			int i= ImageStreamDL.getIndex(file_name);
			i--;
			if((i==0)&&(file_name.contains(".jpg"))) { 
				i=10;
			}
			if((i==0)&&(file_name.contains(".png"))) { 
				i=51;
			}
			file_name= ImageStreamDL.setIndex(i,file_name);
			//InterfaceImage.setFile( new File(file_name));
		
			
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
