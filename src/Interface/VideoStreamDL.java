package Interface;

import java.awt.Image;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.opencv.core.Mat;
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
* VideoStreamDl is used to detect traffic panels using a yolov5 model
* The idea is to send the video frames to a server.
* The server runs inference on them and returns the results to the GUI
* which then shows them to the user
*/


public class VideoStreamDL {
	private InterfaceVideo window;
	private VideoCapture camera ;
	private static File file;
	
	private ImageIcon image;
	private Image empty;
	private Client yoloClient;
	
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
	
	public void serverHandshake() throws UnknownHostException, IOException {
		this.yoloClient = new Client();
		yoloClient.startConnection("localhost", 9999);
	}

	//run the video processing algorithm
	public void VideoProcessing(Vector<Mat> panels) throws IOException {
		
		serverHandshake();
				
		Mat frame = new Mat();
		/*
		 * decrease nb_frames for faster stream
		 * increase nb_frames for slower stream
		 */
		int nb_frames=10;
		int frame_index=0;
				
		Vector<Image> panelsImages;
		panelsImages=Utilities.createPanelsForDL();

		int font = Imgproc.FONT_HERSHEY_SIMPLEX;
		int scale = 1;
		int thickness = 5;
		while(Main.getRunVideoDL()==1) {		
			System.out.println("Please choose a video file");	
			if (Filename.getFilechange()==1) {
				initVideo();
				initImage();
			
				while (camera.read(frame)) {
					
					if (frame_index % nb_frames == 0) {
						
						// saving the frame in a temporary .jpg file that gets deleted by the server after inference 
						String filename = "Images/temporaryFolder/"+Integer.toString(frame_index)+".jpg"; 
						Imgcodecs.imwrite(filename, frame); 
						Vector<Image> panelsImagesToShow = new Vector<Image>();
						// The server receives the name of the temporary .jpg file and sends back the result
						String result=yoloClient.sendMessage("../"+filename);
						
						// We then showcase the result on the GUI
						int counter=0;
						if(result.length()>2) {	
							String[] signs =result.split(";");
							//System.out.println(signs.length);
							for (int j=0; j<signs.length;j++) {
								RoadSign sign = new RoadSign(signs[counter]);
								panelsImagesToShow.insertElementAt(panelsImages.get(sign.getSignClass()), counter);
								Imgproc.rectangle(frame, new Point(sign.getXmin(),sign.getYmin()), new Point(sign.getXmax(),sign.getYmax()), new Scalar (0, 255, 0), 5);
								Imgproc.putText(frame, this.signNames[sign.getSignClass()],new Point(sign.getXmin(), sign.getYmin()) , font, scale,new Scalar (255, 0, 0),thickness );
								counter++;
							}
						}
								
						if(panelsImagesToShow!=null){
						
							if(panelsImagesToShow.size()==1) {
								this.window.panel_plate_image_1.setImage(panelsImagesToShow.get(0));
								this.window.panel_plate_image_2.setImage(empty);	
							}
				
									
							else if(panelsImagesToShow.size()>1) {
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
		closeConnection();
		
	}
	
	//load a video with its filename
	public void initVideo() {
		while(Filename.getVideoFile()==null) {
			System.out.println("please choose an video file");
		}
			
		System.out.println(Filename.getVideoFile());
		VideoStreamDL.file=Filename.getVideoFile();
		this.camera = new VideoCapture(file.getAbsolutePath());
	}
	
	public Image getFrame() {
		return this.image.getImage();
	}
	
	public void closeConnection() throws IOException {
		this.yoloClient.sendMessage("q"); // closes connection from server side
		this.yoloClient.stopConnection();
		Main.setRunVideoDL(0);
	}
	
}
