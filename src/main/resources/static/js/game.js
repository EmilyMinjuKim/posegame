
var constraints = {audio: false, video: true};
var video = document.getElementById("videoInput");

video.width = w;
video.height = h;

function successCallback(stream){
    video.srcObject = stream;
    video.play();
}
function errorCallback(error){
    console.log(error);
}
navigator.getUserMedia(constraints, successCallback, errorCallback);

var canvas = document.getElementById("videoOutput");
canvas.width = w;
canvas.height = h;
var ctx = canvas.getContext("2d");
function processImage(){
    ctx.drawImage(video, 0, 0, w, h);
    setTimeout(processImage, 1);
}
processImage();

function stream(){
    setInterval(sendImage, 30);
}

function sendImage(){
    var rawData = canvas.toDataURL("image/jpeg", 0.5);
    ws.send(rawData);
}

stream();