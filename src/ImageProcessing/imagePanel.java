package ImageProcessing;

import java.awt.Graphics;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class imagePanel extends JPanel {
	Image image;
	public imagePanel() {
		try {
			//must change the image to an empty one
			image = ImageIO.read(new File("images/ref90.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
