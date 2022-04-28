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
		//INTERFACE RUNNING MAIN
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    		Interface window = new Interface();
    		Vector<Mat> panels= Utilities.SignPanels();
    		//String file_path="Videos/video2.mp4";
    		while(true) {
    			
    				VideoStream video_stream = new VideoStream(window);
    				video_stream.VideoProcessing(panels);
    	
    		}

      /*Mat img = Utilities.readImage("Images/ref90.jpg");
      Utilities.GreyMode(img);
      Utilities.BGRMode(img);
      Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
      System.out.println("mat = " + mat.dump());
      */
    /*  Mat testFile = Utilities.readImage("Images/p8.jpg");
      //Mat ref = Utilities.readImage("Images/ref70.jpg");
      Utilities.imShow("test file", testFile);
      Mat hsvimage=Utilities.RGB2HSV(testFile);
      //HighGui.imshow("hsv 110km/h", hsvimage);
     List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
     Mat objetrond = null;
		
		  for (MatOfPoint contour: listeContours ){
		  objetrond=Utilities.DetectForm(testFile,contour); if (objetrond!= null) {
		  Utilities.imShow("contour rond", objetrond);
		  Utilities.Match(objetrond,panels); }
		  }*/
	     // uncomment this if u are working on the images on drive ( we have some problems in seuillage )
		  
      //Utilities.Match(testFile,panels);
      //HighGui.waitKey(0);

    
    	/*System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    File imageFolder = new File("Images/data/detected_images/");
	    File[] listOfImages = imageFolder.listFiles();
	    Arrays.sort(listOfImages);
	    for (int i = 0; i < listOfImages.length; i++) {
	    	if (listOfImages[i].isFile()) {
	    		int nbOfObject =0;
	    		//System.out.println("File " + listOfImages[i].getName());
	    		String filename = listOfImages[i].getName();
		    	Mat testFile = Utilities.readImage("Images/data/detected_images/"+filename);
		    	//Mat rescaled_img = Utilities.rescale(testFile);
		    	Mat rescaled_img = testFile;
		    	//Utilities.imShow("original Image", rescaled_img);
		    	    
		    	List<MatOfPoint> listeContours = Utilities.detectContoursImproved(rescaled_img);
		    	Mat objetrond = null;
		    	for (MatOfPoint contour:  listeContours ){
		    		objetrond=Utilities.DetectForm(rescaled_img,contour);
		    	    if (objetrond!= null) {
		    	    	nbOfObject++;
		    	    	String sign = "Images/data/detected_images/signs/"+filename.substring(0,filename.length()-4)+"_"+Integer.toString(nbOfObject); 
		    	    	//System.out.println(sign);
		    	    	//Imgcodecs.imwrite(sign+".png", objetrond); 
		    	    	//Utilities.imShow(listOfImages[i].getName(), objetrond);
		    	    	//System.out.println(listOfImages[i].getName());
		    		}
		    	}
		    	if (nbOfObject==0) {
		    			System.out.println(filename);
		    	}
	    	}
      }
      //Mat testFile = Utilities.readImage("Images/data/detected_images/00006.png");*/
    	
    	
    	

      
   }
}
