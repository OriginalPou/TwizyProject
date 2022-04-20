package ImageProcessing;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;



public class Interface {

	 /*public void paint(Graphics g) {
	     Toolkit t=Toolkit.getDefaultToolkit();  
	     Image i=t.getImage("Interface_Images/background2.jpg");  
	    // g.drawImage(i, 0,0,getWidth(),getHeight(),this);
	  }  */
	 static {
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
				}

		static Mat imag = null;
		
	 public static void main(String[] args) {  
		 JFrame jframe = new JFrame("Twizzy_Video");
		 jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 JLabel vidpanel = new JLabel();
		 jframe.setContentPane(vidpanel);
		 jframe.setSize(720, 480);
		 jframe.setVisible(true);
		 
		 
		 VideoCapture camera = new VideoCapture("C:\\Users\\mahag\\Downloads\\GitHub_WorkSpace\\project_Twizzy\\TwizyProject\\Videos\\video1.mp4");
		 
		Mat frame = new Mat();
		
		 Mat PanneauAAnalyser = null;
		int i=0;
		//System.out.print(camera.read(frame));
		 while (camera.read(frame) && i<100) {
				//A completer
			 
			 	//Utilities.ImageDisplayPanel(frame);
				Mat HSV_image=Utilities.RGB2HSV(frame);
				if (i==70) {
				Utilities.imShow("HSV_image", HSV_image);
				}
				i++;
				//Mat threshold_image=Utilities.multipleThreshhold(HSV_image, 6, 170, 110);
				
				List<MatOfPoint> ListContours= Utilities.detectContours(HSV_image);
				Mat round_object = null;
				
				for (MatOfPoint contour: ListContours  ){
					round_object=Utilities.DetectForm(frame,contour);
					System.out.print(round_object);
					if (round_object!=null){
						Utilities.imShow("contour", round_object);
					}
				}
				
			 ImageIcon image = new ImageIcon(Utilities.Mat2bufferedImage(frame));
			 vidpanel.setIcon(image);
			 vidpanel.repaint();
		 }
		 
		 /*Interface m=new Interface();  
		 JFrame f=new JFrame();
		 f.setTitle("Detection des panneaux - TWIZZY");
		 
		 //button
		 JButton b=new JButton("Start");  
		 b.setBounds(50,100,95,30);  
		 f.add(b);
		
	     f.add(m);  
	     f.setSize(1920 ,1080);
	     f.setVisible(true); */
	     
	 }  
}

