let ws = new WebSocket('ws://172.20.10.8:8080/temp');

ws.onmessage = function(event) {
    let text = document.getElementById("text");
    let message = event.data;
    text.innerHTML = message;
}