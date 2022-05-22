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
		
		
		//yoloClient.sendMessage("../Images/p10.jpg");
		//yoloClient.stopConnection();

	
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	HomePage window = new HomePage();
    	
    	window.DisplayHomePage();
    	
    	while(true) {
    		//run Image Processing
    		System.out.println(runImage);
    		System.out.println(runVideo);
    		System.out.println(runVideoDL);
    		System.out.println(runImageDL);
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
}
