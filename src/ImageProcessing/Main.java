package ImageProcessing;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;


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
      HighGui.waitKey(0);
      
   }
}
