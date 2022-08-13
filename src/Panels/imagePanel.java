package Panels;

import java.awt.Graphics;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/*
 * This is the panel where the images are displayed on the user interface
 */

public class imagePanel extends JPanel {
	Image image;
	public imagePanel() {
		try {
			image = ImageIO.read(new File("Images/Interface_Images/white.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public imagePanel(Image image) throws IOException {
		this.image=image;
	}
	public void setImage(Image image) {
		this.image=image;
	}
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
	}

}
