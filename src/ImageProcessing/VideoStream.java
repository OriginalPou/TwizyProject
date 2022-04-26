package ImageProcessing;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import ImageProcessing.Interface;

public class VideoStream {
	public Interface window;
	public VideoCapture camera ;
	//Mat PanneauAAnalyser = null;
	ImageIcon image;
	Image image_90;
	Image image_70;
	Image image_50;
	Image image_30;
	Image image_110;
	
	
	//constructor
	public VideoStream(Interface window,String file_name) throws IOException {		
		File f = new File(file_name);
		this.camera = new VideoCapture(f.getAbsolutePath());
		this.window = window;
		this.initImage();
		
	}
 
	public void initImage() throws IOException {
		this.image_90=ImageIO.read(new File("images/ref90.jpg"));
		this.image_70=ImageIO.read(new File("images/ref70.jpg"));
		this.image_50=ImageIO.read(new File("images/ref50.jpg"));
		this.image_30=ImageIO.read(new File("images/ref30.jpg"));
		this.image_110=ImageIO.read(new File("images/ref110.jpg"));
	}
	
	//run the video processing algorithm
	public void VideoProcessing(Vector<Mat> panels) {
		Mat frame = new Mat();
		int speed=10;	// adjust this for faster stream
		int frame_index=0;
		
		while (camera.read(frame)) {
			
			if (frame_index % speed == 0) {
				//Mat HSV_image=Utilities.RGB2HSV(frame);
				List<MatOfPoint> ListContours= Utilities.detectContoursImproved(frame);
				
				Mat round_object = null;
		
				for (MatOfPoint contour: ListContours  ){
					round_object=Utilities.DetectForm(frame,contour);
					if (round_object!=null){
						//Utilities.imShow("contour", round_object);
						//Utilities.Match(round_object,panels);
						this.window.panel_plate_image.setImage(image_90);
					}
				}	
			}
				this.window.panel_plate_image.repaint();
				this.image = new ImageIcon(Utilities.Mat2bufferedImage(frame));
				this.window.panel_video.setImage(getFrame());
				this.window.panel_video.repaint();
			
				frame_index=frame_index+1;
		}	
	}
	
	public Image getFrame() {
		return this.image.getImage();
	}



}
