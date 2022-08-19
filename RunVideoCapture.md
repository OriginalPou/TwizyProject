### How To run detection and recognition on video

#### install OpenCv 4.5.5
Download and build on your Linux machine [OpenCv 4.5.5](https://opencv.org/releases/) 
#### Change the pom.xml file
You need to change the library dependency in the pom.xml file
```xml
  <dependencies>
	<!-- uncomment this dependecy -->
        <dependency>
		<groupId>io.github.dmitributorchin</groupId>
		<artifactId>opencv</artifactId>
		<version>4.5.5</version>
	<dependency>
	< comment this dependecy >
	<!--dependency>
		<groupId>org.openpnp</groupId>
		<artifactId>opencv</artifactId>
		<version>4.5.1-2</version>
	</dependency-->

  </dependencies>
```

#### Change the Main.java file
All you need to do is to comment the line that originally loads the library into memory and provide the path to `libopencv_java455.so` installed on your pc with the OpenCv package
```java
	//Comment this line
	//nu.pattern.OpenCV.loadShared();
	//Uncommment this line and enter the path to the following .so file
	System.load("path to libopencv_java455.so");
``` 

