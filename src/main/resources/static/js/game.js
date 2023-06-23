<!-- 유저 점수 -->
let score = 0;

<!-- 웹소켓 연결 -->
//var url = "ws://localhost:1111"
var url = "ws://13.59.44.233:9999"
var ws = new WebSocket(url);

ws.onopen = function(){
    console.log("Websocket is connected.");
    stream();
}

<!-- 이미지 일치 확인 -->
ws.onmessage = function(msg){

    // 파이썬에서 보내주는 메세지
    var classification = msg.data;
    console.log(classification);

    // 만약 파이썬 메시지와 그림 타이틀이 같으면 ajax 실행
    if(classification === now){
        now = "temp"
        console.log("equals");
        $('#img').after('<h1 id="success">S&nbsp;U&nbsp;C&nbsp;C&nbsp;E&nbsp;S&nbsp;S&nbsp;😆</h1>');
        $("#img").css({
            "opacity" : "0.5"
        });
        score += 10;
        $("#score").text(score);

        setTimeout(function (){
            // 다음 그림 받아오기
            $.ajax({
                url: "/game/ajax",
                type: "POST",
                data: {"gname" : gname},
                success: function (data) {
                    $("#success").remove();

                    // 다음 이미지로 바꾸고 title 정보 업데이트
                    document.getElementById("img").src = data.imgUrl;
                    $("#img").css({
                        "opacity" : "1"
                    });
                    document.getElementById("title").value = data.title;
                },
                error: function (error) {
                    console.log("error")
                    console.error(error);
                }
            });
        }, 2000)
    }
}


// legacy가 있는 브라우저에서의 사용을 위해
/*
navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia;

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
 */

var constraints = { audio: false, video: { width: w, height: h } };
var video = document.querySelector('video');

navigator.mediaDevices.getUserMedia(constraints)
    .then(function(stream) {

        // Older browsers may not have srcObject
        if ("srcObject" in video) {
            video.srcObject = stream;
        } else {
            // Avoid using this in new browsers, as it is going away.
            video.src = window.URL.createObjectURL(stream);
        }
        video.onloadedmetadata = function(e) {
            video.play();
        };
    })
    .catch(function(err) {
        console.log(err.name + ": " + err.message);
    });



var canvas = document.getElementById("videoOutput");
canvas.width = w;
canvas.height = h;
var ctx = canvas.getContext("2d");
function processImage(){
    ctx.drawImage(video, 0, 0, w, h);
    setTimeout(processImage, 30);
}
processImage();

function stream(){
    setInterval(sendImage, 1000);
}

function sendImage(){
    var rawData = canvas.toDataURL("image/jpeg", 0.5);
    ws.send(rawData);
}


// BGM
function playAudio(){
    console.log("audio");
    var audio = new Audio('/assets/bgm/fast.mp3');
    audio.play();
}


// game timeout 설정
var time = 50;

var x = setInterval(function (){
    document.getElementById("time").innerHTML = time + "초";

    if(time<=5){
        document.getElementById("time").innerHTML
            = "<p style='color: red'>" +
            time + "초" +
            "</p>"
        ;
    }

    if(time<=0){
        clearInterval(x);
        document.getElementById("last").innerHTML
            = "<p>G A M E  O V E R</p>";

        $("#last").css({
            "background" : "rgba(0,0,0,0.5)"
        });

        // 게임 끝나면 웹소켓 닫아주기
        ws.close();

        // 게임 결과 전송
        setTimeout(function (){
            document.getElementById("result").value = score;
            $("#scoreForm").submit();
        }, 2000);

    }

    time--;
}, 1000);