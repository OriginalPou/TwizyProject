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
		this.empty=ImageIO.read(new File("Interface_Images/white.png"));
	}
	
	public void ImageProcessing(Vector<Mat> panels) throws IOException {
		
		initData();
		Vector<Image> panelsImages;
		int panelIndex;
		panelsImages=Utilities.CreatePanels();
	
		this.window.panel_plate_image_1.setImage(empty);
		this.window.panel_plate_image_2.setImage(empty);
		int i=0;
		if (filechanged==1) {
			initData();
			filechanged=0;
		}
		
		//TO DO: traitement images
		Mat img = Utilities.readImage(file.getAbsolutePath());
		Mat HSV_image=Utilities.RGB2HSV(img);
		List<MatOfPoint> ListContours= Utilities.detectContours(HSV_image);
		
		Mat round_object = null;
		Vector<Image> panelsImagesToShow = new Vector<Image>();
		
		for (MatOfPoint contour: ListContours  ){
			round_object=Utilities.DetectForm(img,contour);
			if (round_object!=null){
				///Utilities.imShow("contour", round_object);
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

}
