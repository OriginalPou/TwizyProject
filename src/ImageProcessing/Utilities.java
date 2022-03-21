package ImageProcessing;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt4;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
		 * @brief : method that convert to HSV domain
		 * @input : RGB image (Matrix) mxnx3
		 * @return : converted image (Matrix) mxnx3
		 */
	public static Mat RGB2HSV(Mat m) {
		
		Mat convertedImage=Mat.zeros(m.size(), m.type());
		Imgproc.cvtColor(m, convertedImage, Imgproc.COLOR_BGR2HSV);
		return convertedImage;
	}
	
	/*
	 * @brief : method that return a hsv image based on the inf and sup given // this's a private function used in detecterContoures(Mat)
	 * @input : HSV image (Matrix) mxnx3
	 * @return : converted HSV image (Matrix) mxnx3
	 */
	
	private static Mat threshImage(Mat hsv_image) {
		//set here the inf and sup
		Scalar inf=new Scalar(0,100,100);
	    Scalar sup=new Scalar(10,255,255);
		Mat threshold_img=new Mat();
		Mat threshold_img1=new Mat();
		Mat threshold_img2=new Mat();
		Core.inRange(hsv_image, inf, sup, threshold_img1);
		Core.inRange(hsv_image, new Scalar(90,120,100), new Scalar(179,255,255), threshold_img2);
		Core.bitwise_or(threshold_img1, threshold_img2, threshold_img);
		Imgproc.GaussianBlur(threshold_img, threshold_img,new Size(9,9), 2,2);
		
		return threshold_img;
	}
	
	/*
	 * @brief : method that detect all contours in a given hsv image
	 * @input : HSV image (Matrix) mxnx3
	 * @return : List of point of a specific contour
	 */
	public static List<MatOfPoint> detecterContoures(Mat mat) {
		Mat m=mat;
		Mat threshold_img=threshImage(m);
		int thresh =50;
		Mat canny_output=new Mat();
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		MatOfInt4 hierarchy = new MatOfInt4();
		Imgproc.Canny(threshold_img, canny_output, thresh, thresh*2);
		Imgproc.findContours(canny_output, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
		return contours;
	
	}
	
	   
	
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
}
