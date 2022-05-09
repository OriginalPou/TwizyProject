package Panels;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Dimension;



import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import org.opencv.core.Mat;

import ImageProcessing.Main;
import ImageProcessing.Utilities;
import Interface.HomePage;
import Interface.ImageStream;
import Interface.InterfaceImage;
import Interface.VideoStream;
import java.util.List;
import java.util.Vector;
import java.awt.Color;

import javax.swing.border.LineBorder;

import ImageProcessing.Main;

public class imageButtonDL extends JButton implements MouseListener{
	
	public imageButtonDL (String name)  {
		this.setText(name);
		this.addMouseListener(this);
		//this.setBorderPainted(false);
		this.setFocusPainted(false);
		//this.setContentAreaFilled(false);
		this.setBorder(new LineBorder(new Color (220,20,60)));
		this.setBackground(new Color (211,211,211));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		//TO DO : run deepL model for images
		//Main.runImage=1;	
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
