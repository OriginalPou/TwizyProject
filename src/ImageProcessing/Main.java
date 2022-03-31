package ImageProcessing;

import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import org.opencv.core.MatOfInt4;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;


/*
 * Main class
 * We can use it to make sure opencv is installed correctly
 * for the time being
 */

public class Main {
    public static void main( String[] args ) {
      System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
      Mat testFile = Utilities.readImage("Images/p5.jpg");
      Utilities.imShow("Original Image", testFile);
      Mat hsvimage=Utilities.RGB2HSV(testFile);
      List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
      Mat objetrond = null;
      for (MatOfPoint contour:  listeContours ){
			objetrond=Utilities.DetectForm(testFile,contour);
			if (objetrond!= null) {
				Utilities.imShow("contour rond", objetrond);
				break;
			}
      }
      Mat sign = Utilities.readImage("Images/ref90.jpg");
      objetrond= Utilities.scale(sign, objetrond);
      Mat result = new Mat();
      Core.bitwise_or(objetrond, sign, result);
      Utilities.imShow("result", result);

      
      //Utilities.streamVideo("Videos/video1.mp4");
      
      
      
   }
}
