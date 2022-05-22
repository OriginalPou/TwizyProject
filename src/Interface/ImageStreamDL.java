package Interface;

import java.awt.Image;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import DeepLearning.Client;
import DeepLearning.RoadSign;
import ImageProcessing.Main;
import ImageProcessing.Utilities;


/*
 * this is the class for procesing the new data base images with deeplearning
 * TODO: mahdi make changes necessary for detection 
 */
public class ImageStreamDL {
	public InterfaceImage window;
	public static File file;
	public static int filechanged=0;
	ImageIcon image;
	Image empty;
	public static  int CloseImageProc=0;
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
	
	public void ImageProcessing(Vector<Mat> panels) throws IOException {
		
		initData();
		
		Vector<Image> panelsImages;
		int panelIndex;
		panelsImages=Utilities.createPanelsForDL();
		int i=0;
		
		
			
		
		
		int font = Imgproc.FONT_HERSHEY_SIMPLEX;
	    int scale = 1;
	    int thickness = 5;
	    if (filechanged==1) {
	    	if (connectionSet==0) {
				yoloClient.startConnection("localhost", 9999);
			}
	    	initData();
			initImage();
			filechanged=0;
			//Processing
			Vector<Image> panelsImagesToShow = new Vector<Image>();
			
			Mat frame = Utilities.readImage(file.getAbsolutePath());
			
			
			String filename = "Images/temporaryFolder/"+Integer.toString(1)+".jpg"; 
	
		      //Writing the image 
			Imgcodecs.imwrite(filename, frame);
			
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
				
				else if (panelsImagesToShow.size()>1){
					this.window.panel_plate_image_1.setImage(panelsImagesToShow.get(0));
					this.window.panel_plate_image_2.setImage(panelsImagesToShow.get(1));
					}
			}
			i=0;
			
			this.image = new ImageIcon(Utilities.Mat2bufferedImage(frame));
			this.window.panel_data.setImage(getimg());
			this.window.panel_data.repaint();
	
			this.window.panel_plate_image_1.repaint();
			this.window.panel_plate_image_2.repaint();
			closeConnection();
	    }
		
	}
	
	public void initData() {
			int done=0;
			while(done==0) {
				System.out.println(file);
				if (file!=null) {
					this.file=file;
					done=1;
					System.out.println(done);
				}		
		}
	}
	
	public Image getimg() {
		return this.image.getImage();
	}
	
	// this only works for the .jpg pictures: 
	// TODO : change it when we have the new data pics
	// TODO : add:  left , from 1 goes to 9/ right, from 9 goes to 1
	public static String setIndex(int i, String file_name) {
		System.out.println(i);
		if (file_name.contains(".jpg")) {
			//System.out.println("ppp"+file_name);
			file_name="Images/Dataset/p";
			String number=Integer.toString(i);
			file_name=file_name+number+".jpg";
			
			//System.out.println("HIIII"+file_name);
		}
		else if(file_name.contains(".png")) {
			//file_name="Images/DL_Dataset/";
			file_name=file_name.substring(0, file_name.length()-9);
			System.out.println(file_name);
			
			String number=Integer.toString(i);
			
			while(number.length()<5) {
				number="0"+number;
			}
			file_name=file_name+number+".png";
		}
		return file_name;
		
	}
	
	public static int getIndex(String file_name) {
		int i=0;
		String number="";
		
		for(int c=file_name.length()-12; c<file_name.length(); c++){ 
			if (Character.isDigit(file_name.charAt(c))) {	
				number=number+file_name.charAt(c);
			}
		}
		//System.out.println("number: "+number);
		i=Integer.parseInt(number);
		//System.out.println("iii: "+i);
		return i;
	}
	
	public void closeConnection() throws IOException {
		this.yoloClient.sendMessage("q"); // closes connection from server side
		this.yoloClient.stopConnection();
		//Main.runVideoDL=0;
		connectionSet=0;
	}

}
