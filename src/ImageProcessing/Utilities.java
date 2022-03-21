package ImageProcessing;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.Vector;

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
	
	
	public static Mat Multiple_Threshhold(Mat input, int RedOrange, int RedViolet,int Saturation){
			//creating matrix
		Mat threshold_redviolet = new Mat();
		Mat threshold_redorange = new Mat();
		Mat threshold = new Mat();
			
			// check in range of redoraneg
		Scalar lower_redorange = new Scalar(0,Saturation,Saturation);
		Scalar upper_redorange = new Scalar(RedOrange,255,255);
		Core.inRange(input, lower_redorange, upper_redorange, threshold_redviolet);
			
			//check in range of redviolet
		Scalar lower_redviolet= new Scalar (RedViolet,Saturation,Saturation);
		Scalar upper_redviolet = new Scalar (179,255,255);
		Core.inRange(input, lower_redviolet, upper_redviolet, threshold_redorange);
		    
		Core.bitwise_or(threshold_redviolet, threshold_redorange, threshold);
		 
	
		return(threshold);
		
	}

}
