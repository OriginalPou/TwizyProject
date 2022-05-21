package DeepLearning;

public class RoadSign {
	
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;
	private int signClass;
	private float confidence;
	
	public RoadSign(String result) {
		String[] signData = result.split(" ");
		this.xmin = Integer.parseInt(signData[0]);
		this.ymin = Integer.parseInt(signData[1]);
		this.xmax = Integer.parseInt(signData[2]);
		this.ymax = Integer.parseInt(signData[3]);
		this.signClass = Integer.parseInt(signData[4]);
		this.confidence = Float.parseFloat(signData[5]);
	}
	
	public String toString() {
		return("xmin = "+xmin+" ymin = "+ymin+" xmax = "+xmax+" ymax = "+ymax+" class = "+signClass+ " conf = "+confidence);
	}
}
