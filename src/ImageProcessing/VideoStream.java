package ImageProcessing;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.videoio.VideoCapture;

import ImageProcessing.Interface;

public class VideoStream {
	public Interface window;
	public VideoCapture camera ;
	//Mat PanneauAAnalyser = null;
	ImageIcon image;
	Image image_90;
	
	//constructor
	public VideoStream(Interface window) throws IOException {		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		this.camera = new VideoCapture("C:\\Users\\mahag\\Downloads\\GitHub_WorkSpace\\project_Twizzy\\TwizyProject\\Videos\\video1.mp4");
		this.window = window;
		this.initImage();
		
	}
	
	// TODO: must change the image to an empty image 
	public void initImage() throws IOException {
		this.image_90=ImageIO.read(new File("images/ref90.jpg"));
	}
	
	//run the video processing algorithm
	public void VideoProcessing() {
		Mat frame = new Mat();
		while (camera.read(frame)) {
			 
			Mat HSV_image=Utilities.RGB2HSV(frame);
			List<MatOfPoint> ListContours= Utilities.detectContoursImproved(HSV_image);
			
			Mat round_object = null;
		
			for (MatOfPoint contour: ListContours  ){
				round_object=Utilities.DetectForm(frame,contour);
				if (round_object!=null){
					Utilities.imShow("contour", round_object);
					this.window.panel_plate_image.setImage(image_90);
				}
			}
			
			//this.window.panel_plate_image.setImage(image_90);
			this.window.panel_plate_image.repaint();
		
			this.image = new ImageIcon(Utilities.Mat2bufferedImage(frame));
			this.window.panel_video.setImage(getFrame());
			this.window.panel_video.repaint();
		}	
	}
	
	public Image getFrame() {
		return this.image.getImage();
	}


}
