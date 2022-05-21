package ImageProcessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt4;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;

public class algorithmTester {
	
	/*
	 * public ArrayList<String> keys = new ArrayList<String>(); public
	 * ArrayList<Integer> values = new ArrayList<Integer>();
	 * 
	 * public void add(String key, int value){ keys.add((String) key);
	 * values.add((Integer) value); } // And a subsequent return function public
	 * Object getValue(Object key) { return values.get(keys.indexOf(key)); }
	 */
	   // File representing the folder that you select using a FileChooser
    static final File dir = new File("PATH_TO_YOUR_DIRECTORY");

    // array of supported extensions (use a List if you prefer)
    static final String[] EXTENSIONS = new String[]{
        "gif", "png", "bmp" // and other formats you need
    };
    // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };
    

 
    public static List<Mat>  loadDataSet(String path) {
    	Vector<Mat> dataSet=new Vector<Mat>();
    	File rootDir= new File(path);
        File[] files = rootDir.listFiles();

        for(File file :files) {
            String src = file.getAbsolutePath();
            Mat image;
            image = Utilities.readImage(src);
            dataSet.add(image);
        }
        return dataSet;
    }
    
    public static List<String>  loadLabels(String path) {
    	Vector<String> Labels=new Vector<String>();
    	File rootDir= new File(path);
        File[] files = rootDir.listFiles();
        for(File file :files) {
        	
            String imageRef = file.getName();
            String[] ref = imageRef.split("\\.");
            Labels.add(ref[0]);
        }
        return Labels;
    }
    
    
    
	public static List<Mat> createDataSet(){
		
	     Vector<Mat> dataSet=new Vector<Mat>();
	     for(int i=1;i<=10;i++) {
	    	  Mat image = Utilities.readImage("Images/p"+Integer.toString(i)+".jpg");//"+Integer.toString(panels[i])+".
	    	  dataSet.add(image);
	      }
	     
		
		return dataSet;
	}
	
	
	
	public static float test(List<Mat> dataSet, int[] labels,String method) {
		int classe=0;
		int index=0;
		float accuracy=0;
		int[] result = new int[labels.length];
		List<Mat> panels=Utilities.SignPanels();
		for(int i=0; i<dataSet.size();i++) {
			Mat image=dataSet.get(i);
			Mat hsvimage=Utilities.RGB2HSV(image);
			//HighGui.imshow("image", hsvimage);
		    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
		    Mat objetrond = null;
				  for (MatOfPoint contour: listeContours ){
					  objetrond=Utilities.DetectForm(image,contour);
					  if (objetrond!= null) {
						  //Utilities.imShow("contour rond", objetrond);
						  classe=Utilities.Match(objetrond,panels,method);
						  result[index]=classe;
						  index++;
						  }
				  }
		
	}
	
		for(int j=0;j<labels.length;j++) {
			//System.out.println(result[j]);
			if(result[j]==labels[j])
				accuracy++;
		}
	return 100*((accuracy/labels.length));
	//return accuracy;
	}
public static void displayResult(Mat img) {
		int i=0;
		String[] panels_name= {"110km-h", "30km-h", "50km-h", "70km-h", "90km-h", "noEntry", "noOvertaking"};
		Vector<Mat> panels= Utilities.SignPanels();
		Mat hsvimage=Utilities.RGB2HSV(img);
		List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
		Mat objetrond = null;
		MatOfInt4 hierarchy = new MatOfInt4();
		for (MatOfPoint contour: listeContours ){
		objetrond=Utilities.DetectForm(img,contour);
		if (objetrond!= null) {
		Utilities.imShow("contour rond", objetrond);
		int classe =Utilities.Match(objetrond,panels,Utilities.Matching_With_RGB);
		Scalar color = new Scalar(130,41,98);
		Imgproc.drawContours(img, listeContours, i, color,3,5,hierarchy,0, new Point());
		i++;
		System.out.println(panels_name[classe]);

		//Imgproc.putText(img, panels_name[classe],new Point(listeContours.get(i).width(),listeContours.get(i).height()), 1, 1, color, 2);
		  }
}
		Utilities.imShow("Result", img);
		
}



public static boolean TestDetection(Mat image,String image_ref, int ratio) {
	int[] coordinates = null;
	int x_detectedDL_min=0;
	int y_detectedDL_min=0;
	int x_detectedDL_max=0;
	int y_detectedDL_max=0;
	
	Mat hsvimage=Utilities.RGB2HSV(image);
    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
    for (MatOfPoint contour: listeContours )
    	coordinates=Utilities.ContourCoordinates(contour);
    
	int x_detected_min=coordinates[0];
	int y_detected_min=coordinates[1];
	int x_detected_max=coordinates[2];
	int y_detected_max=coordinates[3];
	


	
	
	return false;
}
	

	public static void main( String[] args ) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		float accuracy=0;
		//"110km-h", "30km-h", "50km-h", "70km-h", "90km-h", "noEntry", "noOvertaking"};
		int[] labels={3,3,0,1,0,1,2,2,4,1,1,6};
		List<Mat> dataSet=createDataSet();
		accuracy = test(dataSet,labels,Utilities.Matching_With_PPSimilarity);
		System.out.println("accuracy="+accuracy+"%");


		//String path="Images/test";
		/*List<Mat> dataSet=loadDataSet(path);
		for(int i=0;i<dataSet.size();i++) {
			displayResult(dataSet.get(i));
			
		}*/
		/*List<Mat> dataSet=loadDataSet(path);
		List<String> Labels=loadLabels(path);
		for(int i=0;i<Labels.size();i++) {
			System.out.println(Labels.get(i));
			
		}*/
	}
	
	
	
	
}
