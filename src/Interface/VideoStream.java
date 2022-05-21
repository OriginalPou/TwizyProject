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

import ImageProcessing.Main;
import ImageProcessing.Utilities;

public class VideoStream {
	public InterfaceVideo window;
	public VideoCapture camera ;
	public static File file;
	public static int filechanged=0;
	public static  int stop=0;
	//Mat PanneauAAnalyser = null;
	ImageIcon image;
	Image empty;
	
	
	//constructor
	public VideoStream(InterfaceVideo window) throws IOException {	
		this.window = window;
		this.initImage();	
	}
 
	public void initImage() throws IOException {
		this.empty=ImageIO.read(new File("Images/Interface_Images/white.png"));
		this.window.panel_plate_image_1.setImage(empty);
		this.window.panel_plate_image_2.setImage(empty);
	}

	//run the video processing algorithm
	public void VideoProcessing(Vector<Mat> panels) throws IOException {
		
		initVideo();
		initImage();
		System.out.println(file);
		
		Mat frame = new Mat();
		int speed=20;	// adjust this for faster stream
		int frame_index=0;
		
		Vector<Image> panelsImages;
		int panelIndex;
		panelsImages=Utilities.CreatePanels();
		
		int i=0;
		
		while (camera.read(frame)&&(stop==0)) {
		
			if (filechanged==1) {
				initVideo();
				initImage();
				filechanged=0;
			}
			
			if (frame_index % speed == 0) {
				
				Mat HSV_image=Utilities.RGB2HSV(frame);
				List<MatOfPoint> ListContours= Utilities.detectContours(HSV_image);
				
				Mat round_object = null;
				Vector<Image> panelsImagesToShow = new Vector<Image>();
				for (MatOfPoint contour: ListContours  ){
					round_object=Utilities.DetectForm(frame,contour);
					if (round_object!=null){

						panelIndex=Utilities.Match(round_object,panels,Utilities.Matching_With_RGB);
						panelsImagesToShow.insertElementAt(panelsImages.get(panelIndex), i);
						//this.window.panel_plate_image_1.setImage(panelsImages.get(indexes[i])); // you have an array of index 
						
						i++;
					}
					//Arrays.fill(indexes,0);
				}
				if(panelsImagesToShow!=null){
				
					if(panelsImagesToShow.size()==1) 
						this.window.panel_plate_image_1.setImage(panelsImagesToShow.get(0));
					
					else if(panelsImagesToShow.size()==2) {
						this.window.panel_plate_image_1.setImage(panelsImagesToShow.get(0));
						this.window.panel_plate_image_2.setImage(panelsImagesToShow.get(1));
						}
				}
				i=0;
				// here call the setImage function for each index in the array indexes4
			}
			this.window.panel_plate_image_1.repaint();
			this.window.panel_plate_image_2.repaint();
			this.image = new ImageIcon(Utilities.Mat2bufferedImage(frame));
			this.window.panel_video.setImage(getFrame());
			this.window.panel_video.repaint();
			
			frame_index=frame_index+1;
		}		
	}
	
	//load a video with its filename
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
		this.camera = new VideoCapture(file.getAbsolutePath());
	}
	
	public Image getFrame() {
		return this.image.getImage();
	}

}
