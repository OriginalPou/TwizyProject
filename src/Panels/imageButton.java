package Panels;

import java.awt.Dimension;



import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;

import ImageProcessing.Main;

/*
 * This class implements a mouse listener that starts the image interface
 * which uses opencv to detect and recognize traffic panels
 */

public class imageButton extends JButton implements MouseListener{

	public imageButton (String name)  {
		this.setText(name);
		this.addMouseListener(this);
		//this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.setBorder(new LineBorder(new Color (220,20,60)));
	}
	@Override
	public void mouseClicked(MouseEvent e) {	
		Main.setRunImage(1);	
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
