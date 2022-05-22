package ImageProcessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt4;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;

public class algorithmTester {

	/*
	 * @brief : entrer : path de fichier fonction 
	 * @return : retourne une liste d'images 
	 */
 
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
    
    /*
	 * @brief : entrer : path de fichier fonction 
	 * @return : retourne une liste des references d'images ( nom.png) 
	 */
 
    
    public static List<String>  loadImageReferences(String path) {
    	Vector<String> Labels=new Vector<String>();
    	File rootDir= new File(path);
        File[] files = rootDir.listFiles();
        for(File file :files) {
        	
            String imageRef = file.getName();
            String[] ref = imageRef.split("\\.");
            Labels.add('"'+ref[0]+".png"+'"');
        }
        return Labels;
    }
    
    
    /*
	 * @brief : entrer :none
	 * @return : retourne une liste d'images présentes sur arche 
	 */

	public static List<Mat> createDataSet(){
		
	     Vector<Mat> dataSet=new Vector<Mat>();
	     for(int i=1;i<=10;i++) {
	    	  Mat image = Utilities.readImage("Images/p"+Integer.toString(i)+".jpg");//"+Integer.toString(panels[i])+".
	    	  dataSet.add(image);
	      }
	     
		
		return dataSet;
	}
	
	
	
	/*
	 * @brief : entrer : dataset (liste d'images) , les labels (classes) et la méthode de reconnaissance
	 * pour les images présentes sur arche
	 * @return : retourne le taux de precision de la méthode 
	 */
	
	public static float test(List<Mat> dataSet, int[] labels,String method) {
		int classe=0;
		int index=0;
		float accuracy=0;
		int[] result = new int[labels.length];
		List<Mat> panels=Utilities.SignPanels();
		for(int i=0; i<dataSet.size();i++) {
			Mat image=dataSet.get(i);
			Mat hsvimage=Utilities.RGB2HSV(image);
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
			if(result[j]==labels[j])
				accuracy++;
		}
	return 100*((accuracy/labels.length));
	}

	
	/*
	 * @brief : entrer : dataset (liste d'images) , les labels (classes) et la méthode de reconnaissance
	 * pour les images utilisées pour la partie deep-learning
	 * @return : retourne le taux de precision de la méthode 
	 */
	
	public static float testDLDataset(List<Mat> dataSet,List<String> image_ref,String method) throws FileNotFoundException {
		int classe=0;
		int index=0;
		float accuracy=0;
		int[] result = new int[1264];
		int[] labels =new int[1264];
		List<Mat> panels=Utilities.SignPanels();
		
		for(int i=0; i<dataSet.size();i++) {
			final Scanner in = new Scanner(new FileReader("Database/DataMysql.data"));
			Mat image=dataSet.get(i);
		    while (in.hasNext()) {
		        final String[] columns = in.next().split(",");
		        //System.out.println(columns[0]);
		        //System.out.println(image_ref.get(i));
		        if(columns[0].equals(image_ref.get(i)) ) {
		        	labels[index]=Integer.parseInt(columns[5]);
		        	System.out.println(image_ref.get(i));
		        	System.out.println(labels[index]);
		        	int x_detectedDL_min=Integer.parseInt(columns[1]);
		    		int y_detectedDL_min=Integer.parseInt(columns[2]);
		    		int x_detectedDL_max=Integer.parseInt(columns[3]);
		    		int y_detectedDL_max=Integer.parseInt(columns[4]);
		        	
		        	Point p1=new Point(x_detectedDL_min,y_detectedDL_min);
		        	Point p2=new Point(x_detectedDL_max,y_detectedDL_max);
		        	System.out.println(p1.x);
		        	System.out.println(p1.y);
		        	System.out.println(p2.x);
		        	System.out.println(p2.y);
		        	
		        	if(p1.x <image.cols() && p2.x <image.cols() && p1.y<image.rows() && p2.y<image.rows()) {
		        		Mat subMat = image.submat((int) p1.y, (int) p2.y, (int) p1.x, (int) p2.x);
		        		classe=Utilities.Match(subMat,panels,method);
		        	}
		        	else 
		        		classe=-1;
		        	
		        	result[index]=classe;
	        		System.out.println(classe);
	        		index++;
		        }
			}
		
		}
	
		for(int j=0;j<labels.length;j++) {
			if(result[j]==labels[j])
				accuracy++;
		}
	System.out.println(accuracy);
	return 100*((accuracy/labels.length));
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
				//Utilities.imShow("contour rond", objetrond);
				int classe =Utilities.Match(objetrond,panels,Utilities.Matching_With_RGB);
				Scalar color = new Scalar(130,41,98);
				Imgproc.drawContours(img, listeContours, i, color,3,5,hierarchy,0, new Point());
				i++;
				//System.out.println(panels_name[classe]);
			  }
	}
		Utilities.imShow("Result", img);
		
}


  
/*
 * @brief : entrer : dataset (liste d'images) , les references des images et le ration de detection
 * @return : retourne le taux de precision de la méthode detection des paneaux 
 */

public static int  TestDetection(List<Mat> dataSet,List<String> image_ref, double ratio) throws FileNotFoundException {
	int[] coordinates = null;
	int detected=0;
	
	for(int i=0; i<dataSet.size();i++) {
		final Scanner in = new Scanner(new FileReader("Database/DataMysql.data"));
		Mat image=dataSet.get(i);
		while (in.hasNext()) {
	        final String[] columns = in.next().split(",");
	        if(columns[0].equals(image_ref.get(i))) {
				int x_detectedDL_min=Integer.parseInt(columns[1]);
				int y_detectedDL_min=Integer.parseInt(columns[2]);
				int x_detectedDL_max=Integer.parseInt(columns[3]);
				int y_detectedDL_max=Integer.parseInt(columns[4]);
				Mat hsvimage=Utilities.RGB2HSV(image);
			    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
			    if(listeContours!=null) {
				    for (MatOfPoint contour: listeContours ) {
				    	coordinates=Utilities.ContourCoordinates(contour);
				    	if(coordinates!=null) {
				    	int x_detected_min=coordinates[0];
				    	int y_detected_min=coordinates[1];
				    	int x_detected_max=coordinates[2];
				    	int y_detected_max=coordinates[3];
				    	//System.out.println(x_detected_min);
				    	int x1=Math.max(x_detected_min,x_detectedDL_min);
				    	int x2=Math.min(x_detected_max,x_detectedDL_max);
				    	int y=Math.min(y_detected_min,y_detectedDL_min);
				    	double area=Math.abs(x1-x2)*y;
					    float area_square_detected= (x_detected_max-x_detected_min)*(y_detected_max-y_detected_min);
					    float area_square_DL= (x_detectedDL_max-x_detectedDL_min)*(y_detectedDL_max-y_detectedDL_min);
					    double rationdetected=(area/(Math.max(area_square_detected,area_square_DL)));
					    if(rationdetected<=1 && rationdetected>=ratio)
					    	detected++; 	
						System.out.println(detected);

				    	}
				    }
			    }
	        }
		}
	}
			    
	        
    
	return detected;
}
	

	public static void main( String[] args ) throws FileNotFoundException  {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		String path="DataSet";
		List<Mat> DataSetDL= loadDataSet(path);
	    List<String> DataSetDlReferences=loadImageReferences(path);
		
	    // tester les images données sur arche
	
	    float accuracy=0;
		//"110km-h", "30km-h", "50km-h", "70km-h", "90km-h", "noEntry", "noOvertaking"};
		// label des images données sur arche
		int[] labels={3,3,0,1,0,1,2,2,4,1,1,6};
		List<Mat> dataSet=createDataSet();
		System.out.println("Test sur les images données sur arche:");
		accuracy = test(dataSet,labels,Utilities.Matching_With_RGB);
		System.out.println("méthode Matching_With_RGB:");
		System.out.println("accuracy="+accuracy+"%");
		List<Mat> dataSet1=createDataSet();
		accuracy = test(dataSet1,labels,Utilities.Matching_With_PPSimilarity);
		System.out.println("méthode Matching_With_PPSimilarity:");
		System.out.println("accuracy="+accuracy+"%");
	    
	    
		// Tester les algorithmes de reconnaissance sur des images ( plus difficile à reconnaitre les panneaux)
		
	    
	    float accuracyDL_Matching_With_RGB=testDLDataset(DataSetDL,DataSetDlReferences,Utilities.Matching_With_RGB);
	    float accuracyDL_Matching_With_PPSimilarity=testDLDataset(DataSetDL,DataSetDlReferences,Utilities.Matching_With_PPSimilarity);
	    System.out.println("accuracy de la methode Matching_With_RGB sur les données utilisées dans le DL:");
	    System.out.println(accuracyDL_Matching_With_RGB);
	    System.out.println("accuracy de la methode Matching_With_PPSimilarity sur les données utilisées dans le DL:");
	    System.out.println(accuracyDL_Matching_With_PPSimilarity);
	    
		// tester l'algorithme de detection 

		
		int accuracyDetection=0;
	    accuracyDetection=TestDetection(DataSetDL,DataSetDlReferences, 0.5);
		System.out.println("accuracy detection:");
		System.out.println(accuracyDetection);
	
	}
	
}
