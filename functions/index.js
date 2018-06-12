const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

exports.pushTemperature = functions.database.ref('/Temperature').onWrite( (change,context) => {

    console.log('Push Temperature event triggered');
    console.log(change.after.val());

    if(change.before.val() > 30.0){
      return;
    }

    if(change.after.val() < 29.0){
      return;
    }

    const payload = {
      notification: {
        title: "무더위 주의!",
        body: "현재 기온은 " + change.after.val() + "도 입니다.",
      }
    }

    return admin.messaging().sendToTopic("Silver", payload)
    .then(function(response){
      return console.log("Success: ", response);
    })
    .catch(function(error){
      console.log("Fail: ",error);
    });

});

exports.pushDustdensity = functions.database.ref('/Dustdensity').onWrite( (change,context) => {

    console.log('Push Dustdensity event triggered');
    console.log(change.after.val());

    if(change.before.val() > 80.0){
      return;
    }

    if(change.after.val() < 80.0){
      return;
    }

    const payload = {
      notification: {
        title: "미세먼지 주의!",
        body: "현재 미세먼지 수치는 " + change.after.val() + "입니다.",
      }
    }

    return admin.messaging().sendToTopic("Silver", payload)
    .then(function(response){
      return console.log("Success: ", response);
    })
    .catch(function(error){
      console.log("Fail: ",error);
    });

});
