[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
# Twizy Project

`Twizy project` is an Maven project that uses [OpenCv](https://opencv.org/) and [YOLOv5](https://github.com/ultralytics/yolov5) to detect and recognize traffic signs. `Twizy project` is inspired by the driver aid systems found in many modern cars that help the drivers keep the speed of their cars under the limit required by the law.

https://user-images.githubusercontent.com/72141519/170539305-4bd60749-9691-4d99-b806-ef5a321dba3d.mp4

## Features
This software allows the user to detect and recognize traffic signs in both images and videos. There are two options for detection and recognition:
1) Detection and recognition using ML algorithms coded in OpenCV
2) Detection and recognition using DL algorithms and Yolov5 models

## Installation
### Prerequisites
You need [Maven](https://maven.apache.org/) to run this software.

You need to clone the [YOLOv5 repo](https://github.com/ultralytics/yolov5) and install its requirements
```bash
git clone https://github.com/ultralytics/yolov5  # clone
cd yolov5
pip install -r requirements.txt  # install
```
## How to
### Run the GUI
```bash
mvn compile
mvn exec:java -Dexec.mainClass=ImageProcessing.Main
```
### Run the deep learning tool
Before using the traffic sign detection and recognition DL feature, you need to seperately run the server that loads the model into memory and waits for requests from Java to run inference on images.
```bash
cd DeepLeaning
python server.py
```
## OpenCv Algorithms
Our OpenCv algorithms detect and recognize traffic signs
1) Detection algorithms
- We convert the images from RGB to HSV
- We extract red pixels from the images and remove the rest
- We detect contours in the red image
- We assume that contours with a circular shapes are traffic signs and
2) Recogntion algorithms
- We use template matching to recognize the different traffic signs

## Creating a dataset
We used the [GTSDB](https://benchmark.ini.rub.de/gtsdb_news.html) and screenshots from [Google Maps Street View](https://www.google.com/maps) to create a traffic sign dataset.

We also used [Roboflow](https://roboflow-platform.firebaseapp.com/login) to annotate our Google maps screenshots.

The dataset can be found [here](https://drive.google.com/drive/folders/17c6ustrgv0kN02vaUFwNvjkrLV8vK9sg?usp=sharing).

## YOLOv5 Model
We decided to train a YOLOv5 model since our OpenCv algorithms were having trouble detecting signs under bad lighting.

To train our model we used [Custom Training with YOLOv5](https://colab.research.google.com/github/roboflow-ai/yolov5-custom-training-tutorial/blob/main/yolov5-custom-training.ipynb#scrollTo=hrsaDfdVHzxt) Jupyter notebook running on Google Colab.

![myimage](https://user-images.githubusercontent.com/72141519/170539263-08b4b8da-fe04-4d98-886d-2a8bd2d6e2d8.gif)


Our algorithms detect these types of signs :
- 30km/h
- 50km/h
- 70km/h
- 90km/h
- 110km/h
- no overtaking
- no entry

## Issues
### Running video
Unfortunately, the software in its current configuration is not capable of running videos because of a licencing issues with the library loaded by Maven. You can read more about the issue [here](https://github.com/openpnp/opencv/issues/55).

For the time being, we are not yet aware of a permanent fix. You can however follow these [steps](RunVideoCapture.md) in you wish to run video.

## Support
If you encounter a problem, want to ask for help, or want to suggest a feature, feel free to [open an issue](https://github.com/OriginalPou/TwizyProject/issues).

## Authors
This project was created by [Maha Gaied](https://github.com/mahagaied), [Oumnia Anouk](https://github.com/WishAnk), [Moad Benslimane](https://github.com/MoadBens), [Salma Maghraoui](https://github.com/Salmamagh01),  [Mahdi Chaari](https://github.com/OriginalPou) and [Ali Chouchene](https://github.com/ALICHOUCHENE) 

## License
Twizy Project is licensed under the [MIT license](LICENSE).
