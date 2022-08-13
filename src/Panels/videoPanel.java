package Panels;

import java.awt.Graphics;

import java.awt.Image;


import javax.swing.JPanel;

/*
 * This is the panel where the videos are displayed on the user interface
 */

public class videoPanel extends JPanel {
	public Image image;
	
	public videoPanel() {
	}
	public videoPanel(Image image) {
		this.image=image;
	}
	public void setImage(Image image) {
		this.image=image;
	}
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	
}
