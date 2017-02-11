import numpy as np
import cv2
import time
from ChaosCam import ChaosCam

count = 0
loops = 200
fps = 30

cam = ChaosCam(0,320,240,fps)
cam.start()
cam2 = ChaosCam(1,640,480,fps)
cam2.start()

#cv2.namedWindow("frame")

start = time.time()
while(count < loops):
    
    count = count +1
    frame = cam.read()
    frame2 = cam2.read()
    cv2.imshow('frame',frame)
    cv2.imshow('frame2',frame2)
    cv2.waitKey(1)

print "capture rate: " + str(loops/(time.time()-start))

cam.stop()
cam2.stop()

cam.printSettings()
cam2.printSettings()

