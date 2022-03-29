package ImageProcessing;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * TO DO: add the different methods needed for 
 * the project
 */

/*
 * each method start with @brief followed by a small explanation 
 * then @ input followed by the input of the method
 * finally @return followed by what the method returns
 */

/*
 * the method readImage is an example of the protocol to follow
 */

public class Utilities {
	

	/*
	 * @brief : method that reads images
	 * @input : string name of file
	 * @return : matrix of the image
	 */
	public static Mat readImage(String fichier) {
		File f = new File(fichier);
		Mat m = Imgcodecs.imread(f.getAbsolutePath());
		return m;
	}
	
	/*
	 * @brief : method that display a matrix in a graphic window
	 * @input : string title of image and matrix of image
	 * @return : shows image in a graphic window
	 */
	
	public static void imShow(String title, Mat img) {
		MatOfByte matOfByte = new MatOfByte();
		Imgcodecs.imencode(".png", img, matOfByte);
		byte[] byteArray = matOfByte.toArray();
		BufferedImage bufImage = null;
		try {
			InputStream in = new ByteArrayInputStream(byteArray);
			bufImage =  ImageIO.read(in);
			JFrame frame = new JFrame();
			frame.setTitle(title);
			frame.getContentPane().add(new JLabel(new ImageIcon(bufImage)));
			frame.pack();
			frame.setVisible(true);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * @brief : method that display a matrix in grey scale mode
	 * @input : matrix of image
	 * @return : shows image in a grey scale mode
	 */
	
	public static void GreyMode(Mat img) {
		Vector<Mat> channels = new Vector<Mat>();
	    Core.split(img, channels);
	    // BGR order
	    for (int i=0; i<channels.size(); i++) {
	    	  Utilities.imShow(Integer.toString(i), channels.get(i));
	    }      
	}
	
	/*
	 * @brief : method that display a matrix in BGR mode
	 * @input : matrix of image
	 * @return : shows image in a BGR scale mode
	 */
	
	public static void BGRMode(Mat img) {
		Vector<Mat> channels = new Vector<Mat>();
	    Core.split(img, channels);
	    Mat dst = Mat.zeros(img.size(), img.type());
	    Vector<Mat> chans = new Vector<Mat>();
	    Mat empty = Mat.zeros(img.size(), CvType.CV_8UC1);
	    for (int i=0; i<channels.size(); i++) {
	    	  chans.removeAllElements();
	    	  for (int j=0; j<channels.size(); j++) {
	    		  if(j != i) {
	    			  chans.add(empty);
	    		  }else {
	    			  chans.add(channels.get(i));
	    		  }
	    	  }
	    	  Core.merge(chans, dst);
	    	  Utilities.imShow(Integer.toString(i), dst);
	    }      
	}
	
	
	
	
	
	/*
	 * @brief : method that detects colors with thresholds (seuillage)
	 * @input : matrix of the image, threshold for red-orange colors, threshold for red-violet colors, 
	 * 			threshold for saturation colors (to remove the grays)
	 * @return : matrix of the image after filtering the colors, in binary ( black & white)
	 */
	public static Mat multipleThreshhold(Mat input, int RedOrange, int RedViolet,int Saturation){
			//creating matrix
		Mat threshold_redviolet = new Mat();
		Mat threshold_redorange = new Mat();
		Mat threshold = new Mat();
			
			// check in range of redorange
		Scalar lower_redorange = new Scalar(0,Saturation,Saturation);
		Scalar upper_redorange = new Scalar(RedOrange,255,255);
		Core.inRange(input, lower_redorange, upper_redorange, threshold_redviolet);
			
			//check in range of redviolet
		Scalar lower_redviolet= new Scalar (RedViolet,Saturation,Saturation);
		Scalar upper_redviolet = new Scalar (179,255,255);
		Core.inRange(input, lower_redviolet, upper_redviolet, threshold_redorange);
		    
			//result
		Core.bitwise_or(threshold_redviolet, threshold_redorange, threshold);
		 
		return(threshold);
		
	}

}
