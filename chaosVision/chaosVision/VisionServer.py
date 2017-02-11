import numpy as np
import cv2
import time
from ChaosCam import ChaosCam
from ImageProcessor import ImageProcessor

count = 0
loops = 3000

#stablish frame positions
cv2.namedWindow('frame')
#cv2cnamedWindow('frame2')
cv2.moveWindow('frame', 0,100)
#cv2.moveWindow('frame2',960,100)

cam = ChaosCam(0,640,480,30)
cam.start()

ip = ImageProcessor(cam.readSingle())
ip.start()

start = time.time()
while(count < loops): 
    count = count +1
    
    frame = cam.read()
    ip.setFrame(frame)
    processedImage = ip.getProcessedFrame()
    #cv2.imshow('frame',frame)
    cv2.imshow('frame', processedImage)
    cv2.waitKey(1)
print "live rate: " + str(loops/(time.time()-start))

print "\r\n frame results: "
print "ip.targetX: " + str(ip.getX())
print "ip.targetY: " + str(ip.getY())
print "ip.getSize: " + str(ip.getSize()) + "\r\n"

ip.stop()
cam.stop()
