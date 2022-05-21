import socket
import torch
import time
import pandas

def load_model () :
	model = torch.hub.load('ultralytics/yolov5', 'custom', path='best.pt')
	return(model)

def runInference (model, image_path) :
	# run inference
	results = model(image_path)
	# return results (pandas dataframe)
	return(results.pandas().xyxy[0])


def prepareData(results):
	df=results #results is a pandas dataframe
	result=""
	for index in df.index:
		if index>0:
			#split between different signs detected
			result=result+";" 
		for col in ["xmin","ymin","xmax","ymax","class"]:
			result=result+ str(int(df.loc[index,col]))+" "
		result=result+ str(float("{:.2f}".format(df.loc[index,"confidence"])))
	return (result+"\n")

def server():
	model=load_model()
	print ("model loaded into memory")
	
	host = "localhost"   # get local machine name
	port = 9999  # Make sure it's within the > 1024 $$ <65535 range
	 
	s = socket.socket()
	s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
	s.bind((host, port))

	print ("SERVER READY")  
	s.listen(1)
	c, addr = s.accept()
	s.settimeout(0.00001)
	print("Connection from: " + str(addr))
	while True:
		image_path = c.recv(1024).decode('utf-8')
		if not image_path:
			continue
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
