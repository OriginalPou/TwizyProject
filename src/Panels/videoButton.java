package Panels;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JButton;

import ImageProcessing.Main;
import Interface.VideoStream;

public class videoButton extends JButton implements MouseListener {
Icon pressed, rolled;

	
	public videoButton(String video) {
		this.setText(video);
		this.addMouseListener(this);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Main.runVideo=1;
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
