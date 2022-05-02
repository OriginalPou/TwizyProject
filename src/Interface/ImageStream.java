package Interface;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.opencv.core.Mat;

public class ImageStream {
	public InterfaceImage window;
	public static File file;
	public static int filechanged=0;
	ImageIcon image;
	Image image_90;
	Image image_70;
	Image image_50;
	Image image_30;
	Image image_110;
	Image image_double;
	
	public ImageStream(InterfaceImage window) throws IOException {	
		this.window = window;
		this.initImage();
	}
	public void initImage() throws IOException {
		this.image_90=ImageIO.read(new File("images/ref90.jpg"));
		this.image_70=ImageIO.read(new File("images/ref70.jpg"));
		this.image_50=ImageIO.read(new File("images/ref50.jpg"));
		this.image_30=ImageIO.read(new File("images/ref30.jpg"));
		this.image_110=ImageIO.read(new File("images/ref110.jpg"));
		this.image_double=ImageIO.read(new File("images/ref110.jpg"));
	}
	
	public void ImageProcessing(Vector<Mat> panels) {
		
	}

}
