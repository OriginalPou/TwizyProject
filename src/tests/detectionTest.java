package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;

import ImageProcessing.Utilities;

class detectionTest {

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
	void testReadImage() {
		Mat image = Utilities.readImage("Images/p1.jpg");
		assertNotNull(image);
	}
	
	@Test
	void testDetectionP1() {
	Mat testFile = Utilities.readImage("Images/p1.jpg");
    Mat hsvimage=Utilities.RGB2HSV(testFile);
    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
	assertNotNull(listeContours);


	}
	@Test
	void testDetectionP2() {
	Mat testFile = Utilities.readImage("Images/p2.jpg");
    Mat hsvimage=Utilities.RGB2HSV(testFile);
    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
	assertNotNull(listeContours);

	}
	
	@Test
	void testDetectionP3() {
	Mat testFile = Utilities.readImage("Images/p3.jpg");
    Mat hsvimage=Utilities.RGB2HSV(testFile);
    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
	assertNotNull(listeContours);


	}
	
	@Test
	void testDetectionP4() {
	Mat testFile = Utilities.readImage("Images/p4.jpg");
    Mat hsvimage=Utilities.RGB2HSV(testFile);
    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
	assertNotNull(listeContours);


	}
	
	@Test
	void testDetectionP5() {
	Mat testFile = Utilities.readImage("Images/p5.jpg");
    Mat hsvimage=Utilities.RGB2HSV(testFile);
    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
	assertNotNull(listeContours);

	}
	
	@Test
	void testDetectionP6() {
	Mat testFile = Utilities.readImage("Images/p6.jpg");
    Mat hsvimage=Utilities.RGB2HSV(testFile);
    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
	assertNotNull(listeContours);


	}
	
	@Test
	void testDetectionP7() {
	Mat testFile = Utilities.readImage("Images/p7.jpg");
    Mat hsvimage=Utilities.RGB2HSV(testFile);
    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
	assertNotNull(listeContours);


	}
	
	@Test
	void testDetectionP8() {
	Mat testFile = Utilities.readImage("Images/p8.jpg");
    Mat hsvimage=Utilities.RGB2HSV(testFile);
    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
	assertNotNull(listeContours);


	}
	
	@Test
	void testDetectionP9() {
	Mat testFile = Utilities.readImage("Images/p9.jpg");
    Mat hsvimage=Utilities.RGB2HSV(testFile);
    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
	assertNotNull(listeContours);


	}
	
	@Test
	void testDetectionP10() {
	Mat testFile = Utilities.readImage("Images/p10.jpg");
    Mat hsvimage=Utilities.RGB2HSV(testFile);
    List<MatOfPoint> listeContours = Utilities.detectContours(hsvimage);
	assertNotNull(listeContours);


	}
}
