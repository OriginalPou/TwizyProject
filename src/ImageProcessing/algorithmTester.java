package ImageProcessing;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.highgui.HighGui;

public class algorithmTester {
	
	/*
	 * public ArrayList<String> keys = new ArrayList<String>(); public
	 * ArrayList<Integer> values = new ArrayList<Integer>();
	 * 
	 * public void add(String key, int value){ keys.add((String) key);
	 * values.add((Integer) value); } // And a subsequent return function public
	 * Object getValue(Object key) { return values.get(keys.indexOf(key)); }
	 */
	
	public static List<Mat> createDataSet(){
		
	     Vector<Mat> dataSet=new Vector<Mat>();
	     for(int i=1;i<=10;i++) {
	    	  Mat image = Utilities.readImage("Images/p"+Integer.toString(i)+".jpg");//"+Integer.toString(panels[i])+".
	    	  dataSet.add(image);
	      }
	     Mat image = Utilities.readImage("Images/refdouble.jpg");
	     dataSet.add(image);
		
		
		return dataSet;
	}
	
	
	
	public static float test(List<Mat> dataSet, int[] labels,String method) {
		int classe=0;
		int index=0;
		float accuracy=0;
		int[] result = new int[15];
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
}
