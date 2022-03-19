package ImageProcessing;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;

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
}
