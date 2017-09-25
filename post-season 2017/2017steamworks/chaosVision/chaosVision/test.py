import numpy as np
import cv2
import time
from ChaosCam import ChaosCam
from ImageProcessor import ImageProcessor

#stablish frame positions
cv2.namedWindow('frame')
cv2.namedWindow('frame2')
cv2.moveWindow('frame', 0,100)
cv2.moveWindow('frame2',960,100)

##########################################################
print "Beginning camera input tests \r\n"

count = 0
loops = 10

fps = 30

cam = ChaosCam(0,640,480,fps)
cam.start()

start = time.time()
while(count < loops):
    
    count = count +1
    frame = cam.read()

    cv2.imshow('frame',frame)

    cv2.waitKey(1)

print "capture rate: " + str(loops/(time.time()-start))

cam.stop()

cam.printSettings()


##########################################################
print "\r\n\r\n Camera Read Test Complete."
print "Beginning Image processing tests \r\n"

count = 0
loops = 10

#sourceImage = cv2.resize(cv2.imread('GoalImage3.jpg'),(640,480))
sourceImage = cv2.resize(cv2.imread('rainbowPicture.jpg'),(640,480))

ip = ImageProcessor(sourceImage.copy())
ip.start()
ip.setFrame(sourceImage.copy())

start = time.time()
while(count < loops):
    count = count +1
    ip.setFrame(sourceImage.copy())
    processedImage = ip.getProcessedFrame()
    cv2.imshow('frame', processedImage)

    cv2.waitKey(1)

print "process rate: " + str(loops/(time.time()-start))
ip.stop()


##########################################################
print "\r\n\r\n Image Processing Test Complete."
print "Beginning Target Hunting test \r\n"
count = 0
loops = 600

cam.start()

ip = ImageProcessor(cam.readSingle())
ip.start()
ip.setFrame(sourceImage)

start = time.time()
while(count < loops): 
    count = count +1
    
    frame = cam.read()
    ip.setFrame(frame)
    processedImage = ip.getProcessedFrame()
    #cv2.imshow('frame',frame)
    cv2.imshow('frame2', processedImage)
    cv2.waitKey(1)
print "live rate: " + str(loops/(time.time()-start))

print "\r\n frame results: "
print "ip.targetX: " + str(ip.getX())
print "ip.targetY: " + str(ip.getY())
print "ip.getSize: " + str(ip.getSize()) + "\r\n"

ip.stop()
cam.stop()
