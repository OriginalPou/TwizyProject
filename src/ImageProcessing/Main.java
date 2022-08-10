package ImageProcessing;

import java.io.File;
import java.net.URL;


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



import Interface.HomePage;
import Interface.ImageStream;
import Interface.ImageStreamDL;
import Interface.InterfaceImage;
import Interface.InterfaceVideo;
import Interface.VideoStream;
import Interface.VideoStreamDL;

import DeepLearning.*;



/*
 * Main class
 * We can use it to make sure opencv is installed correctly
 * for the time being
 */

public class Main {
	public static int runImage=0;
	public static int runVideo=0;
	public static int runImageDL=0;
	public static int runVideoDL=0;
	
	
	public static void main( String[] args ) throws IOException {
		
		loadLibrary();
		//yoloClient.sendMessage("../Images/p10.jpg");
		//yoloClient.stopConnection();
		
		//nu.pattern.OpenCV.loadShared();
		//System.loadLibrary("opencv_ffmpeg300_64");
    	//System.loadLibrary("/home/mahdi/opencv_build/opencv/build/java_test/bin");
    	
		
		
		HomePage window = new HomePage();
    	
    	
    	window.DisplayHomePage();
    	
    	while(true) {
    		//run Image Processing
    		/*System.out.println(runImage);
    		System.out.println(runVideo);
    		System.out.println(runVideoDL);
    		System.out.println(runImageDL);*/
    		if (runImage==1) {
    			Vector<Mat> panels= Utilities.SignPanels();
	    		InterfaceImage windowProc= new InterfaceImage();
	    		while (runImage==1) {
	    			ImageStream image_stream = new ImageStream(windowProc);
	    			image_stream.ImageProcessing(panels);
	    		}
	    	}
    		//run Video Processing
    		else if (runVideo==1) {
    			Vector<Mat> panels= Utilities.SignPanels();
 	    		InterfaceVideo windowProc= new InterfaceVideo();
 	    		while (runVideo==1) {
	    			VideoStream video_stream = new VideoStream(windowProc);
	    			video_stream.VideoProcessing(panels);
	    			video_stream.stop=0;
	    		}	
    		}
    		//  deeplearning stuff
    		else if (runImageDL==1) {
    			Vector<Mat> panels= Utilities.SignPanels();
	    		InterfaceImage windowProc= new InterfaceImage();
	    		ImageStreamDL image_stream = new ImageStreamDL(windowProc);
	    		image_stream.serverHandshake();
	    		while (runImageDL==1) {
	    			image_stream.ImageProcessing(panels);
	    		}
	    		
	    	}
    		else if (runVideoDL==1) {
    			Vector<Mat> panels= Utilities.SignPanels();
 	    		InterfaceVideo windowProc= new InterfaceVideo();
 	    		while (runVideoDL==1) {
	    			VideoStreamDL video_stream = new VideoStreamDL(windowProc);
	    			video_stream.VideoProcessing(panels);
	    			video_stream.closeConnection();
	    			video_stream.stop=0;
	    		}	
    		}
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
	
	public static void loadLibrary() {
		File f = new File("Images/Interface_Images/white.png");
		String filePath = f.getAbsolutePath();
		String githubRepoPath=filePath.substring(0, filePath.indexOf("Images/Interface_Images/white.png"));
		String libraryPath= githubRepoPath +"/Resources/libopencv_java455.so";
	
		System.load(libraryPath);
	}
}
