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

import DeepLearning.Client;
import DeepLearning.RoadSign;
import ImageProcessing.Main;
import ImageProcessing.Utilities;


/*
* ImageStreamDl is used to detect traffic panels using a yolov5 model
* The idea is to send the frames to a server.
* The server runs inference on them and returns the results to the GUI
* which then shows them to the user
*/

public class ImageStreamDL {
	private InterfaceImage window;
	private static File file;
	private ImageIcon image;
	private Image empty;

	private int connectionSet=0;
	private Client yoloClient;
	
	private String[] signNames = {"110km-h", "30km-h", "50km-h", "70km-h", "90km-h", "noEntry", "noOvertake"};
	
	public ImageStreamDL(InterfaceImage window) throws IOException {	
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
		connectionSet=1;
	}
	
	public void ImageProcessing(Vector<Mat> panels) throws Exception {
		
		while (Main.getRunImageDL()==1) {
			if(Filename.getFilechange()==1) {
				initImage();
				initData();
				
				Vector<Image> panelsImages;
				panelsImages=Utilities.createPanelsForDL();

				int font = Imgproc.FONT_HERSHEY_SIMPLEX;
			    int scale = 1;
			    int thickness = 5;
			    
			    if (connectionSet==0) {
					yoloClient.startConnection("localhost", 9999);
				}
			    initData();
				initImage();
				
				//Processing
				Vector<Image> panelsImagesToShow = new Vector<Image>();
					
				Mat frame = Utilities.readImage(file.getAbsolutePath());
					
					
				String filename = "Images/temporaryFolder/"+Integer.toString(1)+".jpg"; 
			
				     //Writing the image in a temporary file to be used by the deep learning model
				Imgcodecs.imwrite(filename, frame);
					
				String result=yoloClient.sendMessage("../"+filename);
				if(result.length()>2) {	
					String[] signs =result.split(";");
					//System.out.println(signs.length);
					int counter=0;
					for (int j=0; j<signs.length;j++) {
						RoadSign sign = new RoadSign(signs[counter]);
						panelsImagesToShow.insertElementAt(panelsImages.get(sign.getSignClass()), counter);
						Imgproc.rectangle(frame, new Point(sign.getXmin(),sign.getYmin()), new Point(sign.getXmax(),sign.getYmax()), new Scalar (0, 255, 0), 5);
						Imgproc.putText(frame, this.signNames[sign.getSignClass()],new Point(sign.getXmin(), sign.getYmin()) , font, scale,new Scalar (255, 0, 0),thickness );
						counter++;
					}
				}
				if(panelsImagesToShow!=null){	
					if(panelsImagesToShow.size()==1) 	
						this.window.panel_plate_image_1.setImage(panelsImagesToShow.get(0));
						
					else if (panelsImagesToShow.size()>1){
						this.window.panel_plate_image_1.setImage(panelsImagesToShow.get(0));
						this.window.panel_plate_image_2.setImage(panelsImagesToShow.get(1));
					}
				}
					
				this.image = new ImageIcon(Utilities.Mat2bufferedImage(frame));
				this.window.panel_data.setImage(getimg());
				this.window.panel_data.repaint();
				this.window.panel_plate_image_1.repaint();
				this.window.panel_plate_image_2.repaint();
				
			}
			else {
				System.out.println("please choose an image file");
			}
		}
		closeConnection();
	}
		
	
	
	public void initData() throws Exception {
		
		while(Filename.getImageFile()==null) {
			System.out.println("please choose an image file");
		}
			
		System.out.println(Filename.getImageFile());
		ImageStreamDL.file=Filename.getImageFile();
	}
	
	public Image getimg() {
		return this.image.getImage();
	}
	
	public void closeConnection() throws IOException {
		this.yoloClient.sendMessage("q"); // closes connection from server side
		this.yoloClient.stopConnection();
		//Main.runVideoDL=0;
		connectionSet=0;
	}

}
