package ImageProcessing;

import java.io.File;
import java.io.FileFilter;
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


/*
 * Main class
 * We can use it to make sure opencv is installed correctly
 * for the time being
 */

public class Main {
    public static void main( String[] args ) {
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
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
      //Mat testFile = Utilities.readImage("Images/data/detected_images/00006.png");
      
   }
}
