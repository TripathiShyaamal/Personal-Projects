import cv2

cap=cv2.VideoCapture(0) # We specify 0 in the VideoCapture bracket because we want to open the webcam and for local system webcam,
# the value 0 should be specified. We can use 1 or 2 or other values if we have multiple webcams connected.

classifier=cv2.CascadeClassifier("haarcascade_frontalface_default.xml")# Introducing the classifier for detecting faces

while True:
    ret , frame =cap.read()# •	The read function returns 2 values that is the return value and the image itself. 
    
    if ret:
        faces=classifier.detectMultiScale(frame)# •	The detectMultiScale function detects the faces in the image.
        
        for face in faces:# For every face we draw a rectangle around it.
            x, y, w, h=face# All the four values for a face 
            frame = cv2.rectangle(frame,(x,y),(x+w,y+h),(0,0,255),4)
            
        cv2.imshow("My Window", frame) # The window should open on a condition that the ret value is true.
        
    key=cv2.waitKey(1)# Wait for the window to be closed for the specified time.
    
    if key==ord("q"):# Compare the key value with the ord function.
        break
    

cap.release()
cv2.destroyAllWindows()# Close all the windows.




