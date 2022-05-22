package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Vector;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;

import ImageProcessing.Utilities;

class recognition_Test_Matching_With_RGB {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	
	
	@Test
	void testRecognitionP1() {
	Vector<Mat> panels= Utilities.SignPanels();
	Mat testFile = Utilities.readImage("Images/p1.jpg");
    Mat hsvimage=Utilities.RGB2HSV(testFile);
    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
    Mat objetrond = null;
		  for (MatOfPoint contour: listeContours ){
		  objetrond=Utilities.DetectForm(testFile,contour);
		  if (objetrond!= null) 
			  assertEquals(Utilities.Match(objetrond,panels,Utilities.Matching_With_RGB),3);	  
		  }

	}
	@Test
	void testRecognitionP2() {
	Vector<Mat> panels= Utilities.SignPanels();
	Mat testFile = Utilities.readImage("Images/p2.jpg");
    Mat hsvimage=Utilities.RGB2HSV(testFile);
    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
    Mat objetrond = null;
		  for (MatOfPoint contour: listeContours ){
		  objetrond=Utilities.DetectForm(testFile,contour);
		  if (objetrond!= null) 
			  assertEquals(Utilities.Match(objetrond,panels,Utilities.Matching_With_RGB),3);	  
		  }

	}
	
	@Test
	void testRecognitionP3() {
	Vector<Mat> panels= Utilities.SignPanels();
	Mat testFile = Utilities.readImage("Images/p3.jpg");
    Mat hsvimage=Utilities.RGB2HSV(testFile);
    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
    Mat objetrond = null;
		  for (MatOfPoint contour: listeContours ){
		  objetrond=Utilities.DetectForm(testFile,contour);
		  if (objetrond!= null) 
			  assertEquals(Utilities.Match(objetrond,panels,Utilities.Matching_With_RGB),0);	  
		  }

	}
	
	@Test
	void testRecognitionP4() {
	Vector<Mat> panels= Utilities.SignPanels();
	Mat testFile = Utilities.readImage("Images/p4.jpg");
    Mat hsvimage=Utilities.RGB2HSV(testFile);
    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
    Mat objetrond = null;
		  for (MatOfPoint contour: listeContours ){
		  objetrond=Utilities.DetectForm(testFile,contour);
		  if (objetrond!= null) 
			  assertEquals(Utilities.Match(objetrond,panels,Utilities.Matching_With_RGB),1);	  
		  }

	}
	
	@Test
	void testRecognitionP5() {
	Vector<Mat> panels= Utilities.SignPanels();
	Mat testFile = Utilities.readImage("Images/p5.jpg");
    Mat hsvimage=Utilities.RGB2HSV(testFile);
    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
    Mat objetrond = null;
		  for (MatOfPoint contour: listeContours ){
		  objetrond=Utilities.DetectForm(testFile,contour);
		  if (objetrond!= null) 
			  assertEquals(Utilities.Match(objetrond,panels,Utilities.Matching_With_RGB),0);	  
		  }

	}
	
	@Test
	void testRecognitionP6() {
	Vector<Mat> panels= Utilities.SignPanels();
	Mat testFile = Utilities.readImage("Images/p6.jpg");
    Mat hsvimage=Utilities.RGB2HSV(testFile);
    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
    Mat objetrond = null;
		  for (MatOfPoint contour: listeContours ){
		  objetrond=Utilities.DetectForm(testFile,contour);
		  if (objetrond!= null) 
			  assertEquals(Utilities.Match(objetrond,panels,Utilities.Matching_With_RGB),1);	  
		  }

	}
	
	@Test
	void testRecognitionP7() {
	Vector<Mat> panels= Utilities.SignPanels();
	Mat testFile = Utilities.readImage("Images/p7.jpg");
    Mat hsvimage=Utilities.RGB2HSV(testFile);
    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
    Mat objetrond = null;
		  for (MatOfPoint contour: listeContours ){
		  objetrond=Utilities.DetectForm(testFile,contour);
		  if (objetrond!= null) 
			  assertEquals(Utilities.Match(objetrond,panels,Utilities.Matching_With_RGB),2);	  
		  }

	}
	
	@Test
	void testRecognitionP8() {
	Vector<Mat> panels= Utilities.SignPanels();
	Mat testFile = Utilities.readImage("Images/p8.jpg");
    Mat hsvimage=Utilities.RGB2HSV(testFile);
    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
    Mat objetrond = null;
		  for (MatOfPoint contour: listeContours ){
		  objetrond=Utilities.DetectForm(testFile,contour);
		  if (objetrond!= null) 
			  assertEquals(Utilities.Match(objetrond,panels,Utilities.Matching_With_RGB),4);	  
		  }

	}
	
	@Test
	void testRecognitionP9() {
	Vector<Mat> panels= Utilities.SignPanels();
	Mat testFile = Utilities.readImage("Images/p9.jpg");
    Mat hsvimage=Utilities.RGB2HSV(testFile);
    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
    Mat objetrond = null;
		  for (MatOfPoint contour: listeContours ){
		  objetrond=Utilities.DetectForm(testFile,contour);
		  if (objetrond!= null) 
			  assertEquals(Utilities.Match(objetrond,panels,Utilities.Matching_With_RGB),1);	  
		  }

	}
	
	@Test
	void testRecognitionP10() {
	Vector<Mat> panels= Utilities.SignPanels();
	int [] ref= {1,6};
	int i=0;
	Mat testFile = Utilities.readImage("Images/p10.jpg");
    Mat hsvimage=Utilities.RGB2HSV(testFile);
    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
    Mat objetrond = null;
		  for (MatOfPoint contour: listeContours ){
		  objetrond=Utilities.DetectForm(testFile,contour);
		  if (objetrond!= null) {
			  assertEquals(Utilities.Match(objetrond,panels,Utilities.Matching_With_RGB),ref[i]);
			  i++;
		  }
		  }

	}
	
	
	
	
	
	
	
	
	
	
}
