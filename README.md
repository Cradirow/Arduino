# Intro
Realtime forecast app with Arduino and Firebase

* Source code can be used with specific arduino modules and google firebase services.

# Available Services
All statistics are saved in firebase realtime DB.

If this statistics are higher than normal weather, app automatically send a caution push messages.

- Temperature & Humidity
- Dustdensity
- Gas Status(CO2, NO2, CO)

# Example
![silverwheel](/silverwheel.png)

# About Firebase Functions
This app using firebase functions which automatically send caution push messages.

> Check codes in here : [functions](https://github.com/Cradirow/Arduino/blob/master/functions/index.js)


# Used Tools
- Arduino mini d1 r1
- Android Studio 2.3.3
