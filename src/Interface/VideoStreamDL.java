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
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import DeepLearning.Client;
import DeepLearning.RoadSign;
import ImageProcessing.Main;
import ImageProcessing.Utilities;


/*
* this is the class for videos with deeplearning
* TODO: mahdi make changes necessary for detection 
*/
public class VideoStreamDL {
	public InterfaceVideo window;
	public VideoCapture camera ;
	public static File file;
	public static int filechanged=0;
	public static  int stop=0;
	//Mat PanneauAAnalyser = null;
	ImageIcon image;
	Image empty;
	Client yoloClient;
	
	private String[] signNames = {"110km-h", "30km-h", "50km-h", "70km-h", "90km-h", "noEntry", "noOvertake"};
	
	
	//constructor
	public VideoStreamDL(InterfaceVideo window) throws IOException {
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
		
		this.yoloClient = new Client();
		yoloClient.startConnection("localhost", 9999);
		initVideo();
		initImage();
		System.out.println(file);
		
		Mat frame = new Mat();
		int speed=15;	// adjust this for faster stream
		int frame_index=0;
		
		Vector<Image> panelsImages;
		int panelIndex;
		panelsImages=Utilities.createPanelsForDL();
		
		
		
		int font = Imgproc.FONT_HERSHEY_SIMPLEX;
	    int scale = 1;
	    int thickness = 5;
		
		while (camera.read(frame)&&(stop==0)) {
			int i=0;
		
			if (filechanged==1) {
				initVideo();
				initImage();
				filechanged=0;
			}
			
			if (frame_index % speed == 0) {
				
				String filename = "Images/temporaryFolder/"+Integer.toString(frame_index)+".jpg"; 

			      //Writing the image 
				Imgcodecs.imwrite(filename, frame); 
				Vector<Image> panelsImagesToShow = new Vector<Image>();
				
				String result=yoloClient.sendMessage("../"+filename);
				if(result.length()>2) {	
					String[] signs =result.split(";");
					//System.out.println(signs.length);
					for (int j=0; j<signs.length;j++) {
						RoadSign sign = new RoadSign(signs[i]);
						panelsImagesToShow.insertElementAt(panelsImages.get(sign.getSignClass()), i);
						Imgproc.rectangle(frame, new Point(sign.getXmin(),sign.getYmin()), new Point(sign.getXmax(),sign.getYmax()), new Scalar (0, 255, 0), 5);
						Imgproc.putText(frame, this.signNames[sign.getSignClass()],new Point(sign.getXmin(), sign.getYmin()) , font, scale,new Scalar (255, 0, 0),thickness );
						i++;
					}
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
	
	public void closeConnection() throws IOException {
		this.yoloClient.sendMessage("q"); // closes connection from server side
		this.yoloClient.stopConnection();
		Main.runVideoDL=0;
	}

}
