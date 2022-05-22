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

import ImageProcessing.Utilities;

public class ImageStream {
	public InterfaceImage window;
	public static File file;
	public static int filechanged=0;
	ImageIcon image;
	Image empty;
	
	
	public ImageStream(InterfaceImage window) throws IOException {	
		this.window = window;
		this.initImage();
	}
	public void initImage() throws IOException {
		this.empty=ImageIO.read(new File("Images/Interface_Images/white.png"));
		this.window.panel_plate_image_1.setImage(empty);
		this.window.panel_plate_image_2.setImage(empty);
	}
	
	public void ImageProcessing(Vector<Mat> panels) throws IOException {
		
		initData();
		
		Vector<Image> panelsImages;
		int panelIndex;
		panelsImages=Utilities.CreatePanels();
		int i=0;
		
		if (filechanged==1) {
			initData();
			initImage();
			filechanged=0;
		}
		
		//Processing
		Mat img = Utilities.readImage(file.getAbsolutePath());
		Mat HSV_image=Utilities.RGB2HSV(img);
		List<MatOfPoint> ListContours= Utilities.detectContours(HSV_image);
		
		Mat round_object = null;
		Vector<Image> panelsImagesToShow = new Vector<Image>();
		
		for (MatOfPoint contour: ListContours  ){
			round_object=Utilities.DetectForm(img,contour);
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
	
		this.window.panel_plate_image_1.repaint();
		this.window.panel_plate_image_2.repaint();
		this.image = new ImageIcon(Utilities.Mat2bufferedImage(img));
		this.window.panel_data.setImage(getimg());
		this.window.panel_data.repaint();
	}
	
	public void initData() {
			int done=0;
			while(done==0) {
				System.out.println(file);
				if (file!=null) {
					this.file=file;
					done=1;
				}		
		}
	}
	
	public Image getimg() {
		return this.image.getImage();
	}
	

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

}
