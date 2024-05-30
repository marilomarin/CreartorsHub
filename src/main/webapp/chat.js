function sendMessage() {
    var messageInput = document.getElementById("message-input");
    var message = messageInput.value;
    if (message.trim() !== "") {
        addMessage("Tú", message);
        messageInput.value = "";
    }
}

function addMessage(sender, message) {
    var chatBody = document.getElementById("chat-body");
    var newRow = chatBody.insertRow();
    var senderCell = newRow.insertCell();
    var messageCell = newRow.insertCell();
    senderCell.textContent = sender;
    messageCell.textContent = message;
}