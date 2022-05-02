package ImageProcessing;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
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
	Image empty;
	
	
	//constructor
	public VideoStream(Interface window,String file_name) throws IOException {		
		File f = new File(file_name);
		this.camera = new VideoCapture(f.getAbsolutePath());
		this.window = window;
		this.initImage();
		
	}
	
	// TODO: must change the image to an empty image 
	public void initImage() throws IOException {
		this.empty=ImageIO.read(new File("Interface_Images/white.png"));
	}
	
	public Vector<Image> CreatePanels() throws IOException{
			
			int[] panels= {30,50,70,90,110};
		     Vector<Image> Signs=new Vector<Image>();
		     for(int i=0;i<panels.length;i++) {
		    	 Image panelimage=ImageIO.read(new File("Images/ref"+Integer.toString(panels[i])+".jpg"));
		    	 Signs.addElement(panelimage);
		      }
	    	 Image panelimage=ImageIO.read(new File("Images/refdouble.jpg"));
		     Signs.addElement(panelimage);
		     return Signs;
		}
		
	//run the video processing algorithm
	public void VideoProcessing(Vector<Mat> panels) throws IOException {
		Mat frame = new Mat();
		int speed=1;	// adjust this for faster stream
		int frame_index=0;
		Vector<Image> panelsImages;
		int panelIndex;
		panelsImages=CreatePanels();
		this.window.panel_plate_image_1.setImage(empty);
		this.window.panel_plate_image_2.setImage(empty);

		int i=0;
		while (camera.read(frame)) {
			
			if (frame_index % speed == 0) {
				Mat HSV_image=Utilities.RGB2HSV(frame);
				List<MatOfPoint> ListContours= Utilities.detectContours(HSV_image);
				
				Mat round_object = null;
				Vector<Image> panelsImagesToShow = new Vector<Image>();
				for (MatOfPoint contour: ListContours  ){
					round_object=Utilities.DetectForm(frame,contour);
					
					if (round_object!=null){
						//Utilities.imShow("contour", round_object);
						panelIndex=Utilities.Match(round_object,panels,Utilities.Matching_With_RGB);
						panelsImagesToShow.insertElementAt(panelsImages.get(panelIndex), i);
						//this.window.panel_plate_image_1.setImage(panelsImages.get(indexes[i])); // you have an array of index 
						
						i++;
					}
					//Arrays.fill(indexes,0);
					
					
				}
				if(panelsImagesToShow!=null)
				{
				
					
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
	
	public Image getFrame() {
		return this.image.getImage();
	}



}
