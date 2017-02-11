#!/usr/bin/env python
from flask import Flask, render_template, Response
import numpy as np
import cv2
from time import time
from time import sleep
from ChaosCam import ChaosCam
from ImageProcessor import ImageProcessor

# This was to see if the problem starting was because the camera wasn't
#initialized yet
#sleep(10)


# emulated camera
#from camera import Camera

print "OpenCV Version: " + cv2.__version__
print "Numpy Version: " + np.__version__

x=640
y=480
fps = 120
app = Flask(__name__)
codec = "./mjpg"

@app.route('/')
def index():
    print "in index()"
    """Video streaming home page."""
    return render_template('index.html')



#def gen(camera):
def gen():
    print "in gen()"
    """Video streaming generator function."""
    #cap = cv2.VideoCapture(0)
 
    
    count = 0
    loops = 500

    fps = 30

    cam = ChaosCam(0,640,480,fps)
    cam.start()
    
    ip = ImageProcessor(cam.readSingle())
    #ip.setFrame(sourceImage)
    ip.start()
    
    while(True):

        
        frame = cam.read()
        ip.setFrame(frame)
        processedImage = ip.getProcessedFrame()
        #cv2.imshow('frame',frame)
        #cv2.imshow('frame2', processedImage)
        #cv2.waitKey(1)
        
        #grab camera frame
        #ret, frame = cap.read()
        
        #do image processing here

        #frame = cv2.circle(frame,(int(x/2),int(y/2)),int(x/20),(0,0,255),3)


        #prep and send final image to user
        ret2, jpeg = cv2.imencode('frame.jpg',processedImage,[1,40])
        output = jpeg.tostring()

        if ret2:
            #cv2.imshow('frame', frame)
            yield (b'--frame\r\n'
                   b'Content-Type: image/jpeg\r\n\r\n' + output + b'\r\n')
          
          
@app.route('/video_feed')
def video_feed():
    print "in video_feed()"
    sleep(1)
    #ret, frame = cap.read()
    """Video streaming route. Put thcis in the src attribute of an img tag."""
#   return Response(gen(Camera()),
#                    mimetype='multipart/x-mixed-replace; boundary=frame')
    return Response(gen(),
                    mimetype='multipart/x-mixed-replace; boundary=frame')




def __del__(self):
    print "in __del__"
    cap.release()
    cv2.destroyAllWindows()
    app.shutdown_server()
    return 'flask is closing'

if __name__ == '__main__':
    print "in __main__"
    #gen(Camera())
##    if(True):
##        ret, frame = cap.read()
##        if ret:
##            cv2.imshow('frame', frame)
##            if cv2.waitKey(1) & 0xFF == ord('q'):
##                cv2.destroyAllWindows()
##               # break
#   app.run(host='192.168.10.107', debug=True, threaded=True)
    app.run(host='10.1.31.21', debug=True, threaded=True, port=80)
   #app.run(host='192.168.10.107',debug=True)
