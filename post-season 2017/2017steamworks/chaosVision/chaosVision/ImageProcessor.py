import numpy as np
import cv2
import threading
import time

class ImageProcessor(threading.Thread):
    def __init__(self, frame):
        print "ImageProcessor.__init__"
        #constructor
        self.exitFlag = 0
        self.frameHeight, self.frameWidth = frame.shape[:2]
        self.frame  = np.zeros((self.frameHeight,self.frameWidth,3),np.uint8)
        self.pFrame = np.zeros((self.frameHeight,self.frameWidth,3),np.uint8)
        self.processcount = 0
        self.lastcount = -1
        self.green_frame = np.zeros((self.frameHeight,self.frameWidth,3),np.uint8)
        self.green_frame[:,:] =(255,0,255)
        threading.Thread.__init__(self)
        #temp frames are created here to reduce the cost of memory allocation.
        #Do not assume they are clear before use or that they will persist between cycles
        #self.tempframeHSV = np.zeros((self.frameHeight,self.frameWidth,3),np.uint8)
        #self.tempframe = np.zeros((self.frameHeight,self.frameWidth,3),np.uint8)

    def run(self):
        print "ImageProcessor.run"
        while(self.exitFlag is 0): #run until a destructor runs
            if(self.processcount is not self.lastcount): #only process new images
                #print "ImageProcessor: New Image"
                self.lastcount = self.processcount #remember what image was processed las
                #origFrame = self.frame.copy()
                tempFrame = self.frame.copy()
                
                #put processing algorithm here
                tempframeHSV = cv2.cvtColor(tempFrame, cv2.COLOR_BGR2HSV)
                
                lower_green = np.array([47,80,230])#hsv
                upper_green = np.array([72,230,255])#hsv
                
                mask = cv2.inRange(tempframeHSV, lower_green, upper_green)
                maskinv = cv2.bitwise_not(mask)
                moment = cv2.moments(mask)
                
                #tempFrame = cv2.bitwise_and(tempFrame,tempFrame, mask= mask)

                self.targetSize = int(moment['m00'])
                
                if(self.targetSize > 700):
                    self.targetX = int( moment['m10']/moment['m00'])
                    self.targetY = int( moment['m01']/moment['m00'])
                else:
                    self.targetX = -1
                    self.targetY = -1

                tempFrame = cv2.add(cv2.bitwise_or(tempFrame,tempFrame, mask= maskinv),cv2.bitwise_or(self.green_frame,self.green_frame, mask= mask))

                #apply overlays after processing is complete
                self.tempFrame = cv2.circle(tempFrame,(int(self.frameWidth/2),int(self.frameHeight/2-(self.frameHeight*.04))),int(self.frameWidth/45),(0,0,255),2)
                if(self.targetSize > 0):
					
                    self.tempFrame = cv2.line(tempFrame,(self.targetX,0),(self.targetX,self.frameHeight),(255,0,0),2)
					
                    self.tempFrame = cv2.line(tempFrame,(0,self.targetY),(self.frameWidth,self.targetY),(255,0,0),2)

                #tempFrame = cv2.circle(tempFrame,(int(self.frameWidth/2),int(self.frameHeight/2)),int(self.frameWidth/20),(0,0,255),3)
                
                self.pFrame = tempFrame.copy()
            time.sleep(.001)
        print "ImageProcessor: closing"

    def getProcessedFrame(self):
        #print "ImageProcessor.getProcessedFrame"
        return self.pFrame.copy()
    
    def getX(self):
        print "ImageProcessor.getX"
        return self.targetX

    def getY(self):
        print "ImageProcessor.getY"
        return self.targetY

    def getSize(self):
        print "ImageProcessor.Size"
        return self.targetSize

    def setFrame(self, frame):
        #print "ImageProcessor.setFrame"
        self.frame = frame.copy()
        if( self.processcount is 10):
            self.processcount = 0
        else:
            self.processcount = self.processcount + 1

    def stop(self):
        print "ImageProcessor.stop"
        self.exitFlag = 1

    def __del__(self):
        print "ImageProcessor.__del__"
        #clean up as necessary
        exitFlag = 1

    def __exit__(self):
        print "ImageProcessor.__exit__"
        self.__del__()

        
