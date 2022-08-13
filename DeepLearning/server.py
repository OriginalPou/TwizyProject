''' This script is used to run a yolov5 model trained on traffic sign detection and recognition
The main idea is to communicate with a Java program through a socket communication point
The Java program is used to set up the GUI while this script runs inference on the images and video frames
and sends the inference results to the GUI which displays them to the user
'''


import socket
import torch
import time
import pandas

# this method loads the yolov5 model 
def load_model () :
	model = torch.hub.load('ultralytics/yolov5', 'custom', path='best.pt')
	return(model)

#This method runs inference on the images and video frames 
def runInference (model, image_path) :
	# run inference
	results = model(image_path)
	# return results (pandas dataframe)
	return(results.pandas().xyxy[0])

#This method prepares the data to be sent to the Java program using a clear protocol
def prepareData(results):
	df=results 			#results is a pandas dataframe
	result=""
	for index in df.index:
		if df.loc[index,"confidence"]>0.5:
			if index>0:
				#split between different signs detected
				result=result+";" 
			for col in ["xmin","ymin","xmax","ymax","class"]:
				result=result+ str(int(df.loc[index,col]))+" "
			result=result+ str(float("{:.2f}".format(df.loc[index,"confidence"])))
	return (result+"\n")

#
'''	This method is used to initiate the server using a socket
	and communicate back and forth with the client
'''
def server():
	model=load_model()
	print ("model loaded into memory")
	
	host = "localhost"   	
	port = 9999  		
	 
	s = socket.socket()
	s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
	s.bind((host, port))

	print ("SERVER READY")
	while True:
		print("Waiting for connection")  
		s.listen(1)
		c, addr = s.accept()
		print("Connection from: " + str(addr))
		while True:
			image_path = c.recv(1024).decode('utf-8')
			if not image_path:
				continue
			if image_path=="q\n":
				c.send(bytes('\n','UTF-8'))
				break
			print('image file to run inference on: ' + image_path)
			results = runInference(model,str(image_path[:-1]))
			result  = prepareData(results)
			if result=="\n":
				print ("no sign found")
			else:
				print(results)
			c.send(bytes(result,'UTF-8'))
	s.close()

if __name__ == '__main__':
	server()
