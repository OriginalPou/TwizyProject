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

import ImageProcessing.Main;
import ImageProcessing.Utilities;

/*
* VideoStream is used to detect traffic panels in videos using machine learning coded with opencv
*/

public class VideoStream {
	private InterfaceVideo window;
	private VideoCapture camera ;
	private static File file;
	
	private ImageIcon image;
	private Image empty;
	
	
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
		
		Mat frame = new Mat();
		/*
		 * decrease nb_frames for faster stream
		 * increase nb_frames for slower stream
		 */
		int nb_frames=10;
		int frame_index=0;
		
		Vector<Image> panelsImages;
		int panelIndex;
		panelsImages=Utilities.CreatePanels();
		
		while(Main.getRunVideo()==1) {
			
			System.out.println("Please choose a video file");
			
			if (Filename.getFilechange()==1) {
				
				initVideo();
				initImage();
			
				while (camera.read(frame)) {

					if (frame_index % nb_frames == 0) {
				
						Mat HSV_image=Utilities.RGB2HSV(frame);
						List<MatOfPoint> ListContours= Utilities.detectContours(HSV_image);
						
						Mat round_object = null;
						Vector<Image> panelsImagesToShow = new Vector<Image>();
						
						int counter =0; 
						for (MatOfPoint contour: ListContours  ){
							round_object=Utilities.DetectForm(frame,contour);
							if (round_object!=null){
								
								panelIndex=Utilities.Match(round_object,panels,Utilities.Matching_With_RGB);
								panelsImagesToShow.insertElementAt(panelsImages.get(panelIndex), counter);						
								counter++;
							}
						}
						if(panelsImagesToShow!=null){
						
							if(panelsImagesToShow.size()==1) {
								this.window.panel_plate_image_1.setImage(panelsImagesToShow.get(0));
								this.window.panel_plate_image_2.setImage(empty);						
							}
							
							else if(panelsImagesToShow.size()==2) {
								this.window.panel_plate_image_1.setImage(panelsImagesToShow.get(0));
								this.window.panel_plate_image_2.setImage(panelsImagesToShow.get(1));
								}
						}
					}
				
					this.window.panel_plate_image_1.repaint();
					this.window.panel_plate_image_2.repaint();
					this.image = new ImageIcon(Utilities.Mat2bufferedImage(frame));
					this.window.panel_video.setImage(getFrame());
					this.window.panel_video.repaint();
					
					frame_index=frame_index+1;
				}
			}
		}
	}
	
	//load a video with its filename
	public void initVideo() {
		while(Filename.getVideoFile()==null) {
			System.out.println("please choose an video file");
		}
			
		System.out.println(Filename.getVideoFile());
		VideoStream.file=Filename.getVideoFile();
		this.camera = new VideoCapture(file.getAbsolutePath());		
	}
	
	//change the file name to run another video
	public void setVideoFile() {
		this.camera = new VideoCapture(file.getAbsolutePath());
	}
	
	public Image getFrame() {
		return this.image.getImage();
	}

}