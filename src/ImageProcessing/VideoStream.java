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
	
	
	//constructor
	public VideoStream(Interface window,String file_name) throws IOException {		
		File f = new File(file_name);
		this.camera = new VideoCapture(f.getAbsolutePath());
		this.window = window;
		this.initImage();
		
	}
	
	// TODO: must change the image to an empty image 
	public void initImage() throws IOException {
		this.image_90=ImageIO.read(new File("images/ref90.jpg"));
	}
	
	//run the video processing algorithm
	public void VideoProcessing(Vector<Mat> panels) {
		Mat frame = new Mat();
		int speed=1;	// adjust this for faster stream
		int frame_index=0;
		
		while (camera.read(frame)) {
			
			if (frame_index % speed == 0) {
				Mat HSV_image=Utilities.RGB2HSV(frame);
				List<MatOfPoint> ListContours= Utilities.detectContours(HSV_image);
				
				Mat round_object = null;
		
				for (MatOfPoint contour: ListContours  ){
					round_object=Utilities.DetectForm(frame,contour);
					
					if (round_object!=null){
						//Utilities.imShow("contour", round_object);
						Utilities.Match(round_object,panels);
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
