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

import DeepLearning.*;


/*
 * Main class
 * We can use it to make sure opencv is installed correctly
 * for the time being
 */

public class Main {
	public static void main( String[] args ) throws IOException {
		Client yoloClient = new Client();
		yoloClient.startConnection("localhost", 9999);
		String result=yoloClient.sendMessage("../Images/p1.jpg");
		String[] signs =result.split(";");
		for (int i=0; i<signs.length;i++) {
			RoadSign sign = new RoadSign(signs[i]);
			System.out.println(sign);
		}
		//yoloClient.sendMessage("../Images/p10.jpg");
		yoloClient.stopConnection();
   }
}
