package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;

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
	
	
	
	
}
