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

import ImageProcessing.Main;
import ImageProcessing.Utilities;

/*
* ImageStream is used to detect traffic panels using machine learning coded with opencv
*/

public class ImageStream {
	private InterfaceImage window;
	private static File file;
	private ImageIcon image;
	private Image empty;
	
	
	public ImageStream(InterfaceImage window) throws IOException {	
		this.window = window;
		this.initImage();
	}
	public void initImage() throws IOException {
		this.empty=ImageIO.read(new File("Images/Interface_Images/white.png"));
		this.window.panel_plate_image_1.setImage(empty);
		this.window.panel_plate_image_2.setImage(empty);
	}
	
	public void ImageProcessing(Vector<Mat> panels) throws Exception {
		
		while (Main.getRunImage()==1) {
			if(Filename.getFilechange()==1) {
				initData();
				initImage();
				
				Vector<Image> panelsImages;
				int panelIndex;
				panelsImages=Utilities.CreatePanels();
				int i=0;
		
				//Processing
				Mat img = Utilities.readImage(file.getAbsolutePath());
				Mat HSV_image=Utilities.RGB2HSV(img);
				Mat threshold_img=Utilities.multipleThreshhold(HSV_image, 6, 150, 80);
				List<MatOfPoint> ListContours= Utilities.detectContours(HSV_image);
				
				
				//image display
				this.image = new ImageIcon(Utilities.Mat2bufferedImage(img));
				this.window.panel_data.setImage(getimg());
				this.window.panel_data.repaint();
				
				/*
						//HSV display
						this.image = new ImageIcon(Utilities.Mat2bufferedImage(HSV_image));
						this.window.panel_data.setImage(getimg());
						this.window.panel_data.repaint();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						//threshold display
						this.image = new ImageIcon(Utilities.Mat2bufferedImage(threshold_img));
						this.window.panel_data.setImage(getimg());
						this.window.panel_data.repaint();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}		
				*/
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
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				System.out.println("please choose an image file");
			}
		}
	}
	
	public void initData() throws Exception {
			
			while(Filename.getImageFile()==null) {
				System.out.println("please choose an image file");
			}
				
			System.out.println(Filename.getImageFile());
			ImageStream.file=Filename.getImageFile();
	}
	
	public Image getimg() {
		return this.image.getImage();
	}
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		ImageStream.file = file;
	}
	
	

}
