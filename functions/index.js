const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

// Listens for new messages added to messages/:pushId
exports.pushNotification = functions.database.ref('/Temperature').onWrite( (change,context) => {

    console.log('Push notification event triggered');

    if(change.after.val() < 30.0){
      return;
    }

    const payload = {
      notification: {
        title: "무더위 주의!",
        body: "현재 기온은" + change.after.val() + "도 입니다.",
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
