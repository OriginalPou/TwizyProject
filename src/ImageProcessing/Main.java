package ImageProcessing;

import java.io.File;

import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import org.opencv.core.MatOfInt4;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.photo.Photo;

import ImageProcessing.VideoStream;
import ImageProcessing.Interface;


/*
 * Main class
 * We can use it to make sure opencv is installed correctly
 * for the time being
 */

public class Main {
	public static void main( String[] args ) throws IOException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		//int[] labels={2,2,4,0,4,0,1,1,3,0,0,5};
		//{30,50,70,90,110}
		//names: ['110km-h', '30km-h', '50km-h', '70km-h', '90km-h', 'noEntry', 'noOvertaking']
		//int[] labels={3,3,0,1,0,1,2,2,4,1,1,6};
	 Vector<Mat> panels= Utilities.SignPanels();
   
		//Vector<Mat> panels= Utilities.SignPanels();
		Mat testFile = Utilities.readImage("Images/p4.jpg");
	      //Mat ref = Utilities.readImage("Images/ref70.jpg");
	      Utilities.imShow("test file", testFile);
	      Mat hsvimage=Utilities.RGB2HSV(testFile);
	      //HighGui.imshow("hsv 110km/h", hsvimage);
	     List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
	     Mat objetrond = null;
			  
			  for (MatOfPoint contour: listeContours ){
			  objetrond=Utilities.DetectForm(testFile,contour);
			  if (objetrond!= null) {
			  Utilities.imShow("contour rond", objetrond);
			  Utilities.Match(objetrond,panels,Utilities.Matching_With_RGB); }
			  
			  
			  }
		

   }
}
