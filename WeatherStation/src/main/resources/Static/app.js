//let ws = new WebSocket('ws://172.20.10.8:8080/name'); // daniel mobil??
let ws = new WebSocket('ws://192.168.0.132:8080/temp');

ws.onmessage = function(event) {
    let text = document.getElementById("text");
    let message = event.data;
    message += " C";
    text.innerHTML = message;
}