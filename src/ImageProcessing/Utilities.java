package ImageProcessing;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
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
	
	private static Mat dst;

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
	
	
	
}
