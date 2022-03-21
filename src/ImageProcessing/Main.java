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
      Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
      System.out.println("mat = " + mat.dump());
      Mat testFile = Utilities.readImage("Images/ref110.jpg");
      HighGui.imshow("110km/h", testFile);
      Mat hsvimage=Utilities.RGB2HSV(testFile);
      HighGui.imshow("hsv 110km/h", hsvimage);
      Mat testFileContours=testFile;
      List<MatOfPoint> contours = Utilities.detecterContoures(hsvimage);
      MatOfInt4 hierarchy = new MatOfInt4();
      for(int i=0;i<contours.size();i++) {
    	Scalar color = new Scalar(0,255,0);
		Imgproc.drawContours(testFileContours, contours, i, color,2,5,hierarchy,0, new Point());
      }
      HighGui.imshow("110km/h-c", testFileContours);
      HighGui.waitKey(0);
      
   }
}
