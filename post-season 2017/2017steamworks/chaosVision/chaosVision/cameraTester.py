import numpy as np
import cv2

print "OpenCV Version: " + cv2.__version__


cap = cv2.VideoCapture(0)
print "cap = cv2.VideoCapture"
print cap

while(True):
    #capture frame-by-frame
    ret, frame = cap.read()

    #Our operations ont hte frame come here


    #display the resulting frame
    if ret:
        cv2.imshow('frame', frame)
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break

#when done, release the capture
cap.release()
cv2.destroyAllWindows()
