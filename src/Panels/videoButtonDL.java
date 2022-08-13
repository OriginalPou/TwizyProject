package Panels;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import ImageProcessing.Main;

public class videoButtonDL extends JButton implements MouseListener{
	
	public videoButtonDL (String name)  {
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
		Main.setRunVideoDL(1);
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
