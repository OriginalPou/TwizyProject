# This python script is used to run inference using a yolov5 model
import torch
import time
import pandas


def runInference (image_path) :
	# load model
	model = torch.hub.load('ultralytics/yolov5', 'custom', path='best.pt')
	# run inference
	results = model(image_path)
	# print results
	print(results.pandas().xyxy[0])
	
	
if __name__ == "__main__":
    	# Start timer
	t0 = time.time()
	# run inference
	runInference('../Images/00003.ppm' )
	# how long did the inference take?
	print(time.time()-t0)
