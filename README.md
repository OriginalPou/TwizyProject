[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
# Twizy Project

`Twizy project` is an Eclipse project that uses [OpenCv](https://opencv.org/) and [YOLOv5](https://github.com/ultralytics/yolov5) to detect and recognize traffic signs.

https://user-images.githubusercontent.com/72141519/170539305-4bd60749-9691-4d99-b806-ef5a321dba3d.mp4

## Installation
### Prerequisites
You need [OpenCv 4.5.5](https://opencv.org/releases/) to build and run this project.
You need to clone the [YOLOv5 repo](https://github.com/ultralytics/yolov5) and install its requirements
```bash
git clone https://github.com/ultralytics/yolov5  # clone
cd yolov5
pip install -r requirements.txt  # install
```
## How to run the deep learning tool
In order to use the YOLOv5 model for detection and recognition you need to run the server that loads the model into memory and waits for requests from Java to run inference on images.
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


Our algorithms detect signs of the type :
- 30km/h
- 50km/h
- 70km/h
- 90km/h
- 110km/h
- no overtaking
- no entry

## Support
If you encounter a problem, want to ask for help, or want to suggest a feature, feel free to [open an issue](https://github.com/OriginalPou/TwizyProject/issues).

## Authors
This project was created by [Maha Gaied](https://github.com/mahagaied), [Oumnia Anouk](https://github.com/WishAnk), [Moad Benslimane](https://github.com/MoadBens), [Salma Maghraoui](https://github.com/Salmamagh01),  [Mahdi Chaari](https://github.com/OriginalPou) and [Ali Chouchene](https://github.com/ALICHOUCHENE) 

# License
Twizy Project is licensed under the [MIT license](LICENSE).
