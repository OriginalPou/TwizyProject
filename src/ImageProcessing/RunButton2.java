package ImageProcessing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JButton;

public class RunButton2 extends JButton implements MouseListener {
Icon pressed, rolled;

	
	public RunButton2(String video) {
		this.setText(video);
		this.addMouseListener(this);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		VideoStream.file_name="Videos/video2.mp4";
		VideoStream.stop=1;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
