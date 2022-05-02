package ImageProcessing;

import org.opencv.core.Core;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.Scalar;
import org.opencv.core.Size;

import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.Features2d;

import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.MatOfInt4;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.opencv.photo.Photo;
import org.opencv.videoio.VideoCapture;

import static org.opencv.imgproc.Imgproc.*;
import static org.opencv.imgcodecs.Imgcodecs.imread;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.List;


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
		
		public static final String Matching_With_Difference   = "MatchingWithDifference";
		public static final String Matching_With_PPSimilarity = "MatchingWithPPSimilarity";
		public static final String Matching_With_RGB 		  = "Matching_With_RGB";
	
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
		
		
		/*
		 * @brief : method that display a matrix in a graphic window
		 * @input : string title of image and matrix of image
		 * @return : shows image in a graphic window
		 */
		
		public static void imShow(String title, Mat img) {
			MatOfByte matOfByte = new MatOfByte();
			Imgcodecs.imencode(".png", img, matOfByte);
			byte[] byteArray = matOfByte.toArray();
			BufferedImage bufImage = null;
			try {
				InputStream in = new ByteArrayInputStream(byteArray);
				bufImage =  ImageIO.read(in);
				JFrame frame = new JFrame();
				frame.setTitle(title);
				frame.getContentPane().add(new JLabel(new ImageIcon(bufImage)));
				frame.pack();
				frame.setVisible(true);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*
		 * @brief : method that display a matrix in grey scale mode
		 * @input : matrix of image
		 * @return : shows image in a grey scale mode
		 */
		
		public static void GreyMode(Mat img) {
			Vector<Mat> channels = new Vector<Mat>();
		    Core.split(img, channels);
		    // BGR order
		    for (int i=0; i<channels.size(); i++) {
		    	  Utilities.imShow(Integer.toString(i), channels.get(i));
		    }      
		}
		
		/*
		 * @brief : method that display a matrix in BGR mode
		 * @input : matrix of image
		 * @return : shows image in a BGR scale mode
		 */
		
		public static void BGRMode(Mat img) {
			Vector<Mat> channels = new Vector<Mat>();
		    Core.split(img, channels);
		    Mat dst = Mat.zeros(img.size(), img.type());
		    Vector<Mat> chans = new Vector<Mat>();
		    Mat empty = Mat.zeros(img.size(), CvType.CV_8UC1);
		    for (int i=0; i<channels.size(); i++) {
		    	  chans.removeAllElements();
		    	  for (int j=0; j<channels.size(); j++) {
		    		  if(j != i) {
		    			  chans.add(empty);
		    		  }else {
		    			  chans.add(channels.get(i));
		    		  }
		    	  }
		    	  Core.merge(chans, dst);
		    	  Utilities.imShow(Integer.toString(i), dst);
		    }      
		}
		
		
		/*
		* @brief : method that convert to HSV domain
		* @input : RGB image (Matrix) mxnx3
		* @return : converted image (Matrix) mxnx3
		*/
		public static Mat RGB2HSV(Mat m) {
			
			Mat convertedImage=Mat.zeros(m.size(), m.type());
			Imgproc.cvtColor(m, convertedImage, Imgproc.COLOR_BGR2HSV);
			return convertedImage;
		}
		
		/*
		 * @brief : method that detects colors with thresholds (seuillage)
		 * @input : matrix of the image, threshold for red-orange colors, threshold for red-violet colors, 
		 * 			threshold for saturation colors (to remove the grays)
		 * @return : matrix of the image after filtering the colors, in binary ( black & white)
		 */
		

		public static Mat multipleThreshhold(Mat input, int RedOrange, int RedViolet,int Saturation){
			//creating matrix
		Mat threshold_redviolet = new Mat();
		Mat threshold_redorange = new Mat();
		Mat threshold = new Mat();
			
			// check in range of redorange
		Scalar lower_redorange = new Scalar(0,Saturation,Saturation);
		Scalar upper_redorange = new Scalar(RedOrange,255,255);
		Core.inRange(input, lower_redorange, upper_redorange, threshold_redviolet);
			
			//check in range of redviolet
		Scalar lower_redviolet= new Scalar (RedViolet,Saturation,Saturation);
		Scalar upper_redviolet = new Scalar (179,255,255);
		Core.inRange(input, lower_redviolet, upper_redviolet, threshold_redorange);
		    
			//result
		Core.bitwise_or(threshold_redviolet, threshold_redorange, threshold);
		 
		return(threshold);
		
	}
		
		public static Mat rescale(Mat input) {
			int scale_percent = 60; //percent of original size
			int width = (int)input.size(1) * scale_percent / 100;
			int height = (int)input.size(0) * scale_percent / 100;
			Size newsize = new Size(width, height);
			Mat resized_image = new Mat();
			Imgproc.resize(input,resized_image,newsize,0, 0, INTER_AREA);
			return(resized_image);
		}
		
		/*
		 * @brief : method that detect all contours in a given hsv image
		 * @input : RGB image (Matrix) mxnx3
		 * @return : List of point of a specific contour
		 */
		

		/*
		 * @brief : method that detect all contours in a given hsv image
		 * @input : HSV image (Matrix) mxnx3
		 * @return : List of point of a specific contour
		 */
		public static List<MatOfPoint> detectContours(Mat mat) {
			Mat threshold_img=multipleThreshhold(mat, 6, 150, 80);
			int thresh =40;
			Mat canny_output=new Mat();
			List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
			MatOfInt4 hierarchy = new MatOfInt4();
			Imgproc.Canny(threshold_img, canny_output, thresh, thresh*2);
			Imgproc.findContours(canny_output, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
		    for(int i=0;i<contours.size();i++) {
		    	Scalar color = new Scalar(130,41,98);
				Imgproc.drawContours(threshold_img, contours, i, color,3,5,hierarchy,0, new Point());
		    }
		    //imShow("contours", threshold_img);
			return contours;
		
		}
		
		
		/*
		 * @brief : method that allows to detect and extract round contours
		 * @input : matrix of an image in RGB, MatOfPoint of the image contours
		 * @return : a rectangular image containing the round contour 
		 */
		public static Mat DetectForm(Mat img,MatOfPoint contour) {
			MatOfPoint2f matOfPoint2f = new MatOfPoint2f();
			float[] radius = new float[1];
			Point center = new Point();
			Rect rect = Imgproc.boundingRect(contour);
			double contourArea = Imgproc.contourArea(contour);


			matOfPoint2f.fromList(contour.toList());
			// We look for the smallest round object
			Imgproc.minEnclosingCircle(matOfPoint2f, center, radius);
			//System.out.println(contourArea+" "+Math.PI*radius[0]*radius[0]);
			/*
			 * We accept a contour as a circle if its minimum enclosing circle
			 * has more than 80% the area of a perfect circle 
			 */
			
			if ((contourArea / (Math.PI*radius[0]*radius[0])) >=0.86) {
				//System.out.println("Cercle");
				Imgproc.circle(img, center, (int)radius[0], new Scalar(255, 0, 0), 2);
				Imgproc.rectangle(img, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height), new Scalar (0, 255, 0), 5);
				Mat tmp = img.submat(rect.y,rect.y+rect.height,rect.x,rect.x+rect.width);
				Mat sign = Mat.zeros(tmp.size(),tmp.type());
				tmp.copyTo(sign);
				if(sign.rows()>40 && sign.cols()>40)
					return sign;
			}
			return null;
		}
		/*
		 * @brief : function that makes similar the size of an object and the size of the template
		 * @return : a resized matrix of the object extracted from an image
		 */
		public static Mat scale (Mat sign, Mat object) {
			Mat sObject = new Mat();
			Imgproc.resize(object, sObject, sign.size());
			return(sObject);
		}
		
		/*
		 * @brief : function that does the important job of turning BW an image before template matching
		 */
		
		public static Mat turnBW (Mat image) {
			Mat grayObject = new Mat(image.rows(),image.cols(), image.type());
			Imgproc.cvtColor(image, grayObject, Imgproc.COLOR_BGRA2GRAY);
			Core.normalize(grayObject, grayObject,0,255,Core.NORM_MINMAX);
			return(grayObject);
		}

	
		
		
		/*
		 * @brief  : compare 2 images: when a pixel1(i,j) is black we compare if the pixel2(i,j) is also black or not
		 * @input  : 2 matrix : panel and panel extracted from the image
		 * @return : similarity ratio
		 */
		public static float MatchingWithDifference(Mat object, Mat sObject) {
			float similarity=0;
			int numberOfZeros=0;
			// gray scale
			Mat grayObject=turnBW(object);
			Mat graySign=turnBW(sObject);
			// resize image
			grayObject=scale(graySign,grayObject);
			
			// total number of pixels
			int totalNumberOfPixels=0;
			for(int i=0;i<graySign.width();i++) {
				for(int j=0;j<graySign.height();j++) {
					double[] pixelSign=graySign.get(i, j);
					double[] pixelpanel=grayObject.get(i, j);
					if( ( (int)pixelSign[0]<80) || ((int)pixelpanel[0]<80) ) {
					totalNumberOfPixels++;
					int x=(int)pixelSign[0]-(int)pixelpanel[0];
					if(Math.abs(x)<10) {
						numberOfZeros++;
					}
					}
				}
			}
				similarity=(float)numberOfZeros/totalNumberOfPixels;
				return similarity;
			}
		
			
		
		/*
		 * @brief : function one image with all panels 
		 * @return : shows the panel that match
		 */
		
		public static int Match(Mat object,List<Mat> panels,String method) {
			float[] results=new float[panels.size()];
			float max=0;
			int index=0;
			for(int i=0;i<panels.size();i++) {
				switch (method) {
				case Matching_With_Difference :
					results[i]=MatchingWithDifference(object,panels.get(i));
					break;
				case Matching_With_PPSimilarity:
					results[i]=MatchingWithPPSimilarity(object,panels.get(i));
					break;
				case Matching_With_RGB:
					results[i]=MatchingWithRGB(object,panels.get(i));
					break;
				}
				
			}
			for(int i=0;i<panels.size();i++) {
			if(results[i]>max) {
				max=results[i];
				index=i;
				}
			}
			imShow("pannel detected",panels.get(index));
			return index;
		}
			
		
		/*
		 * @brief : create a vector of images  
		 * @return : vector of panel images
		 */
			
			public static Vector<Mat> SignPanels(){
				
			 int[] panels= {30,50,70,90,110};
		     Vector<Mat> Signs=new Vector<Mat>();
		     for(int i=0;i<panels.length;i++) {
		    	  Mat image = Utilities.readImage("Images/ref"+Integer.toString(panels[i])+".jpg");//"+Integer.toString(panels[i])+".
		    	  Signs.add(image);
		      }
		     Mat image = Utilities.readImage("Images/refdouble.jpg");
		     Signs.add(image);
		      return Signs;
			}
			
		
		
			/*
			 * @brief  : turn the 2 images into binary ( choose the best threshold to convert2binary the image)
			 * @input  : a matrix (image)
			 * @return : binary image
			 */
		public static Mat turnBinary (Mat image) {
			Mat binaryObject = new Mat(image.rows(),image.cols(), image.type());
			for(int i=0;i<image.width();i++) {
				for(int j=0;j<image.height();j++) {
					double[] pixel=image.get(i, j);
					if((int)pixel[0]>=200)
						binaryObject.put(i, j, 0);
					else
						binaryObject.put(i, j, 1);
					
				}
			}
			
			return(binaryObject);
		}
		
		/*
		 * @brief  : turn the 2 images into binary and compare each pixel one by one ( choose the best threshold to convert2binary the image)
		 * @input  : 2 matrix : panel and panel extracted from the image
		 * @return : similarity ratio
		 */
		public static float MatchingWithPPSimilarity(Mat object, Mat sObject) {
			float similarity=0;
			// gray scale
			Mat grayObject=turnBW(object);
			Mat graySign=turnBW(sObject);
			grayObject=scale(graySign,grayObject);
			Mat binarySign=turnBinary(graySign);
			Mat binaryObject=turnBinary(grayObject);

			// total number of pixels
			int totalNumberOfPixels=graySign.width()*graySign.height();//=graySign.height()*graySign.height();
			for(int i=0;i<graySign.width();i++) {
				for(int j=0;j<graySign.height();j++) {
					double[] xySign   = binarySign.get(i,j);
					double[] xyObject = binaryObject.get(i,j);
					int xy1= (int)xySign[0];
					int xy2= (int)xyObject[0];
					if(  xy1 == xy2 )
						similarity++;
					}
				}
				similarity=(float)similarity/totalNumberOfPixels;
				return similarity;
			}
		
		
		/*
		 * @brief  : method that compare 2 images based on the absolute difference between RGB values
		 * @input  : 2 matrix : panel and panel extracted from the image
		 * @return : similarity ratio
		 */
		
		public static float MatchingWithRGB(Mat object, Mat sObject) {
		
			Mat img1=scale(sObject,object);
			Mat img2=sObject;
			// in order to well recognise the panel the brightness should be around 0.5 ~ 0.6
			float brightness=getBrightness(img1);
			System.out.println("brightness="+brightness);
			if(brightness<0.45)
				img1.convertTo(img1, -1, 1, 55);
			if(brightness>0.95)
				img1.convertTo(img1, -1, 1, -85);
			float brightnessAfter=getBrightness(img1);
			System.out.println("brightness after="+brightnessAfter);
			int height=img1.height();
			int width =img1.width();
			double diff = 0;
	         for (int j = 0; j < height; j++) {
	            for (int i = 0; i < width; i++) {
	               //Getting the RGB values of a pixel
	               double pixel1[] = img1.get(i, j);
	               double r1 = pixel1[0];
	               double g1 = pixel1[1];
	               double b1 = pixel1[2];
	               double pixel2[] = img2.get(i, j);
	               double r2 = pixel2[0];
	               double g2 = pixel2[1];
	               double b2 = pixel2[2];
	               //sum of differences of RGB values of the two images
	               double data = Math.abs(r1-r2)+Math.abs(g1-g2)+ Math.abs(b1-b2);
	               diff = diff+data;
	            }
	         }
	         double avg = diff/(width*height*3);
	         double percentageOfSimilarity =100- (avg/255)*100;
	         return (float)percentageOfSimilarity;
	      }
	   
		
		
		/*
		 * @brief : method that allows to calculate the brightness of an image
		 * @input : matrix of an image 
		 * @return : float : brightness 
		 */
		
		public static float getBrightness(Mat image) {
			
			double[] color = image.get(image.width()/2, image.height()/2);
			// extract each color component
			// calc brightness in range 0.0 to 1.0; using SRGB luminance constants
			float brightness = ((float)color[0] * 0.2126f + (float)color[1] * 0.7152f + (float)color[2] * 0.0722f) / 255;
			return brightness;
			
		}


		/* @input: the title of the video ( or the path)
		 * @return: a Jframe window containing the video stream
		 * @brief : a function to run a video stream
		 */
		
		public static void streamVideo (String videoTitle) {
			JFrame jframe = new JFrame("Detection de panneaux sur un flux vidéo");
			jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JLabel vidpanel = new JLabel();
			jframe.setContentPane(vidpanel);
			jframe.setSize(720, 480);
			jframe.setVisible(true);

			Mat frame = new Mat();
			VideoCapture camera = new VideoCapture(videoTitle);
			Mat PanneauAAnalyser = null;

				while (camera.read(frame)) {
					 
					Mat HSV_image=Utilities.RGB2HSV(frame);
					List<MatOfPoint> ListContours= Utilities.detectContours(HSV_image);
					
					Mat round_object = null;
				
					for (MatOfPoint contour: ListContours  ){
						round_object=Utilities.DetectForm(frame,contour);
						if (round_object!=null){
							Utilities.imShow("contour", round_object);
						}
					}
				
				ImageIcon image = new ImageIcon(Mat2bufferedImage(frame));
				vidpanel.setIcon(image);
				vidpanel.repaint();
			}
		}
		
		//used for video steaming
		public static BufferedImage Mat2bufferedImage(Mat image) {
			MatOfByte bytemat = new MatOfByte();
			Imgcodecs.imencode(".jpg", image, bytemat);
			byte[] bytes = bytemat.toArray();
			InputStream in = new ByteArrayInputStream(bytes);
			BufferedImage img = null;
			try {
				img = ImageIO.read(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return img;
		}

	
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
			
			

		
		
		
		


