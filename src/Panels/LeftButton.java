package Panels;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import Interface.Filename;


/*
 * The left button allows the user to prompt the previous image file in the current folder
 */

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
		Filename.getPreviousImage();	
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
