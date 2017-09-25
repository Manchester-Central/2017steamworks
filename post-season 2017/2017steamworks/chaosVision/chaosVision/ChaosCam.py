#!/usr/bin/env python
import numpy as np
import cv2
import threading
import time


class ChaosCam():
    def run(self):
        print "ChaosCam.run"
        while(self.exitFlag is 0):
            success, self.rawImage = self.camera.read()
            if(success is False):
                time.sleep(1)

    def readSingle(self):
        print "ChaosCam.readSingle"
        success, frame = self.camera.read()
        self.rawImage = frame
        return frame

    def __del__(self):
        print "ChaosCam.__del__"
        self.exitFlag = 1
        self.camera.release()

    def __exit__(self):
        print "ChaosCam.__exit__"
        self.exitFlag = 1
        self.camera.release()       

    def start (self):
        print "ChaosCam.start"
        self.exitFlag = 0
        self.camera.open(self.channel)
        
        self.camera.set(cv2.CAP_PROP_FRAME_WIDTH, self.x)# resolution in the X direction
        self.camera.set(cv2.CAP_PROP_FRAME_HEIGHT, self.y)# resolution in the Y direction
        self.camera.set(cv2.CAP_PROP_FPS, self.fps) # frame rate

        self.camThread = threading.Thread(target=self.run)
        self.camThread.start()
        time.sleep(0.5)
            
    def stop (self):
        print "ChaosCam.stop"
        self.exitFlag = 1

    def setResolution (self, width, height):
        print "ChaosCam.setResolution"
        self.x = width
        self.y = height
        self.camera.set(cv2.CAP_PROP_FRAME_WIDTH, x)# resolution in the X direction
        self.camera.set(cv2.CAP_PROP_FRAME_HEIGHT, y)# resolution in the Y direction
        
    def setFPS (self, framesPerSecond):
        print "ChaosCam.setFPS"
        self.fps = framesPerSecond
        self.camera.set(cv2.CAP_PROP_FPS, fps) # frame rate


    def read(self):
        "ChaosCam.readRaw"
        return self.rawImage

    def printSettings(self):
        print " "
        print "ChaosCam.printSettings"
        print "OpenCV Version: " + cv2.__version__
        print "Numpy Version: " + np.__version__

        print "webacm X: " + str(self.camera.get(cv2.CAP_PROP_FRAME_WIDTH))
        print "webacm Y: " + str(self.camera.get(cv2.CAP_PROP_FRAME_HEIGHT)) 
        print "webacm FPS: " + str(self.camera.get(cv2.CAP_PROP_FPS))
        print "webacm FOURCC: " + str(self.camera.get(cv2.CAP_PROP_FOURCC))#Invalid arg
        print "webcam FORMAT: " + str(self.camera.get(cv2.CAP_PROP_FORMAT))#Invalid arg
        print "webacm MODE: " + str(self.camera.get(cv2.CAP_PROP_MODE))
        print "webacm BRIGHTNESS: " + str(self.camera.get(cv2.CAP_PROP_BRIGHTNESS))
        print "webcam CONTRAST: " + str(self.camera.get(cv2.CAP_PROP_CONTRAST))
        print "webacm SATURATION: " + str(self.camera.get(cv2.CAP_PROP_SATURATION))
        print "webacm HUE: " + str(self.camera.get(cv2.CAP_PROP_HUE))
        print "webcam GAIN: " + str(self.camera.get(cv2.CAP_PROP_GAIN))
        print "webacm convert RGB flag: " + str(self.camera.get(cv2.CAP_PROP_CONVERT_RGB))#invalid arg
        print " "
        
    def __init__(self, channel,x,y,fps):
        print "ChaosCam.init"
        self.exitFlag = 0
        self.channel = channel
        self.x = x
        self.y = y
        self.fps = fps

        self.rawImage = np.zeros((y,x,3),np.uint8)
        self.exportImage = np.zeros((y,x,3),np.uint8)

        print "opening camera # " + str( channel )
        self.camera = cv2.VideoCapture(channel)      

