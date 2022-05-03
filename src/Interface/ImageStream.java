package Interface;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.videoio.VideoCapture;

import ImageProcessing.Utilities;

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
		
		initData();
		
		if (filechanged==1) {
			initData();
			filechanged=0;
		}
		
		//TO DO: traitement images
		Mat img = Utilities.readImage(file.getAbsolutePath());
		List<MatOfPoint> ListContours= Utilities.detectContoursImproved(img);
		Mat round_object = null;
		
		for (MatOfPoint contour: ListContours  ){
			round_object=Utilities.DetectForm(img,contour);
			if (round_object!=null){
				//Utilities.imShow("contour", round_object);
				//Utilities.Match(round_object,panels);
				this.window.panel_plate_image.setImage(image_90);
			}
		}	
		this.window.panel_plate_image.repaint();
		this.image = new ImageIcon(Utilities.Mat2bufferedImage(img));
		this.window.panel_data.setImage(getimg());
		this.window.panel_data.repaint();
		
		
	}
	public void initData() {
			
			int done=0;
			while(done==0) {
				System.out.println(file);
				if (file!=null) {
					this.file=file;
					done=1;
				}
					
		}
		
	}
	public Image getimg() {
		return this.image.getImage();
	}

}
