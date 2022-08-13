package Interface;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;


/*
 * This class is used to navigate through folders of images
 * It will be used by Panels.LeftButton and Panels.RightButton
 * in order to obtain the name of the next or previous image file in the current folder
 */

public class Filename {
	private static File imageFile;
	private static File videoFile;
	private static File folder;
	private static ArrayList<File> images;
	private static int imageIndex;
	private static int filechange = 0;
	
	public static void initiateFilename(File File) throws Exception {
		Filename.imageFile=File;
		Filename.videoFile=File;
		// navigating through files is only implemented for image files
		if(isImage(File.getAbsolutePath())) {
			getFolderPath();
			getImageFiles();
			getImageIndex();
		}
		filechange=1; // indicating to the other classes that the image files have changed
	}
	
	/*
	 * this method allows us to extract the folder path of our image
	 */
	public static void getFolderPath() throws Exception {
		folder = imageFile.getParentFile();
	}
	
	/*
	 * this method allows us to get the image files in our folder
	 */
	public static void getImageFiles() {
		
		File[] files = folder.listFiles();
		ArrayList<File> images = new ArrayList<File>();
		for (File file : files) {
		    if (file.isFile() && isImage(file.getAbsolutePath())) {
		        images.add(file);
		    }
		}
		// sorting filenames by ascending order
		Collections.sort(images);
		Filename.images=images;
		//System.out.println(images);
		
	}
	
	/*
	 * This method is used to retrieve the next File to be used once the right button is clicked
	 */
	public static void getNextImage() {
		if (imageIndex==images.size())
			imageIndex=-1;
		imageIndex++;
		if (!images.isEmpty()) {
			imageFile=images.get(imageIndex);
			filechange=1;
		}
		else 
			imageFile=null;
	}
	
	/*
	 * This method is used to retrieve the previous File to be used once the left button is clicked
	 */
	public static void getPreviousImage() {
		if (imageIndex==0)
			imageIndex=images.size();
		imageIndex--;
		if (!images.isEmpty()) {
			imageFile=images.get(imageIndex);
			filechange=1;
		}
		else
			imageFile=null;
	}
	
	/*
	 * This method is used to retrive the index of the image first chosen by the user
	 */
	public static void getImageIndex() throws Exception {
		try {
			imageIndex=images.indexOf(imageFile);}
		catch (Exception e) {
			throw(new Exception("Was not able to extract the index of the imageFile"));
		}
	}
	
	/*
	 * This method is used to check whether a file corresponds to an image
	 */
	private static boolean isImage(String filename) {
		
		if (filename.endsWith(".png") || filename.endsWith(".jpg"))
			return true;
		return false;	
	
	}

	public static File getImageFile() {
		filechange=0; // once another class reads the file it is no longer new
		return imageFile;
	}

	public static int getFilechange() {
		return filechange;
	}

	public static File getVideoFile() {
		filechange=0; // once another class reads the file it is no longer new
		return videoFile;
	}

	public static void setVideoFile(File videoFile) {
		Filename.videoFile = videoFile;
	}
	
	
	
	
}
