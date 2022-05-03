package Interface;

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

import ImageProcessing.Utilities;

public class VideoStream {
	public InterfaceVideo window;
	public VideoCapture camera ;
	//public static String file_name="Videos/video1.mp4";
	public static File file;
	//public static int stop=0;
	public static int filechanged=0;
	//Mat PanneauAAnalyser = null;
	ImageIcon image;
	Image image_90;
	Image image_70;
	Image image_50;
	Image image_30;
	Image image_110;
	Image image_double;
	
	
	//constructor
	public VideoStream(InterfaceVideo window) throws IOException {	
		//File f = new File(file_name);
		//this.camera = new VideoCapture(f.getAbsolutePath());
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
	
	//run the video processing algorithm
	public void VideoProcessing(Vector<Mat> panels) {
		
		initVideo();
		System.out.println(file);
		Mat frame = new Mat();
		int speed=10;	// adjust this for faster stream
		int frame_index=0;
		
		while (camera.read(frame)) {
			
			/*if (stop==1) {
				setVideoFile();
				stop=0;
			}*/
			if (filechanged==1) {
				initVideo();
				filechanged=0;
			}
			
			if (frame_index % speed == 0) {
				//Mat HSV_image=Utilities.RGB2HSV(frame);
				List<MatOfPoint> ListContours= Utilities.detectContoursImproved(frame);
				
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
	public void initVideo() {
		
		int done=0;
		while(done==0) {
			System.out.println(file);
			if (file!=null) {
				this.camera = new VideoCapture(file.getAbsolutePath());
				done=1;
			}
		}		
	}
	
	//change the file name to run another video
	public void setVideoFile() {
		//File f = new File(file_name);
		//this.camera = new VideoCapture(f.getAbsolutePath());
		this.camera = new VideoCapture(file.getAbsolutePath());
	}
	
	public Image getFrame() {
		return this.image.getImage();
	}



}
