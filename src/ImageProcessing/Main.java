package ImageProcessing;

import java.util.Vector;
import org.opencv.core.Mat;

import Interface.HomePage;
import Interface.ImageStream;
import Interface.ImageStreamDL;
import Interface.InterfaceImage;
import Interface.InterfaceVideo;
import Interface.VideoStream;
import Interface.VideoStreamDL;


/*
 * Main class
 * initiates the main GUI
 * and runs one of the following choices
 * 1- traffic sign detection on images using machine learning
 * 2- traffic sign detection on videos using machine learning
 * 3- traffic sign detection on images using a yolov5 model
 * 4- traffic sign detection on videos using a yolov5 model
 */

public class Main {
	
	//keep track of the user's choice
	private static int runImage=0;
	private static int runVideo=0;
	private static int runImageDL=0;
	private static int runVideoDL=0;
	
	
	public static void main( String[] args ) throws Exception {
		
		/*
		 * Load the libraries needed
		 */
		
		nu.pattern.OpenCV.loadShared();
    	//System.loadLibrary("/home/mahdi/opencv_build/opencv/build/lib/libopencv_java455.so");
    	
		
		HomePage window = new HomePage();
    	window.DisplayHomePage();
    	
    	while(true) {
    		printChoice();
    		// run image processing with ML
    		if (runImage==1) {
    			Vector<Mat> panels= Utilities.SignPanels();
	    		InterfaceImage windowProc= new InterfaceImage();
	    		ImageStream image_stream = new ImageStream(windowProc);
	    		image_stream.ImageProcessing(panels);
	    			
	    		
	    	}
    		//run Video Processing with ML
    		else if (runVideo==1) {
    			Vector<Mat> panels= Utilities.SignPanels();
 	    		InterfaceVideo windowProc= new InterfaceVideo();
 	    		while (runVideo==1) {
	    			VideoStream video_stream = new VideoStream(windowProc);
	    			video_stream.VideoProcessing(panels);
	    			VideoStream.setStop(0);
	    		}	
    		}
    		//run Image Processing with DL
    		else if (runImageDL==1) {
    			Vector<Mat> panels= Utilities.SignPanels();
	    		InterfaceImage windowProc= new InterfaceImage();
	    		ImageStreamDL image_stream = new ImageStreamDL(windowProc);
	    		image_stream.serverHandshake();
	    		while (runImageDL==1) {
	    			image_stream.ImageProcessing(panels);
	    		}
	    		
	    	}
    		//run Video Processing with DL
    		else if (runVideoDL==1) {
    			Vector<Mat> panels= Utilities.SignPanels();
 	    		InterfaceVideo windowProc= new InterfaceVideo();
 	    		while (runVideoDL==1) {
	    			VideoStreamDL video_stream = new VideoStreamDL(windowProc);
	    			video_stream.VideoProcessing(panels);
	    			video_stream.closeConnection();
	    			VideoStreamDL.setStop(0);
	    		}	
    		}
    	}
    	
 
    	
		
    	

   }
	
	
	public static void printChoice() {
		if (runImage==0 && runVideo==0 && runImageDL==0 && runVideoDL==0 ) {
			System.out.println("please choose one of the options");
		}
	}

	public static int getRunImage() {
		return runImage;
	}

	public static void setRunImage(int runImage) {
		Main.runImage = runImage;
	}

	public static int getRunVideo() {
		return runVideo;
	}

	public static void setRunVideo(int runVideo) {
		Main.runVideo = runVideo;
	}

	public static int getRunImageDL() {
		return runImageDL;
	}

	public static void setRunImageDL(int runImageDL) {
		Main.runImageDL = runImageDL;
	}

	public static int getRunVideoDL() {
		return runVideoDL;
	}

	public static void setRunVideoDL(int runVideoDL) {
		Main.runVideoDL = runVideoDL;
	}
	
}
