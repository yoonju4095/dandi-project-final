const $usernamePage = document.getElementById("usernamePage");
const $chatPage = document.getElementById("chatPage");
const $usernameForm = document.getElementById("usernameForm");
const $messageForm = document.getElementById("messageForm");
const $message = document.getElementById("message");
const $messageArea = document.getElementById("messageArea");
const $connecting = document.querySelector(".connecting");

let stompClient = null;
let username = null;

const colors = [
  "#2196F3",
  "#32c787",
  "#00BCD4",
  "#ff5652",
  "#ffc107",
  "#ff85af",
  "#FF9800",
  "#39bbb0",
];

function connect(e) {
  username = document.getElementById("name").value.trim();

  if (username) {
    $usernamePage.classList.add("hidden");
    $chatPage.classList.remove("hidden");

    const socket = new SockJS("/ws");
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);
  }
  e.preventDefault();
}

function onConnected() {
  //구독 기능
  stompClient.subscribe("/topic/public", onMessageReceived);

  //서버에 사용자 이름 알림
  stompClient.send(
    "/app/chat.addUser",
    {},
    JSON.stringify({ sender: username, type: "JOIN" })
  );
  $connecting.classList.add("hidden");
}

function onError(error) {
  $connecting.textContent =
    "채팅 서버에 연결할 수 없습니다. 페이지를 새로고침 후 다시 시도해보세요!";
  $connecting.style.color = "red";
}

function sendMessage(e) {
  const messageContent = $message.value.trim();
  if (messageContent && stompClient) {
    const chatMessage = {
      sender: username,
      content: $message.value,
      type: "CHAT",
    };
    stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
    $message.value = "";
  }
  e.preventDefault();
}

/* 요소를 동적으로 조작하는 곳 */
function onMessageReceived(payload) {
  const message = JSON.parse(payload.body);
  const messageElement = document.createElement("li");

  if (message.type === "JOIN") {
    messageElement.classList.add("event-message");
    message.content = message.sender + "님 입장!";
  } else if (message.type === "LEAVE") {
    messageElement.classList.add("event-message");
    message.content = message.sender + " 퇴장!";
  } else {
    messageElement.classList.add("chat-message");

    const avatarElement = document.createElement("i");
    const avatarText = document.createTextNode(message.sender[0]);
    avatarElement.appendChild(avatarText);
    avatarElement.style["background-color"] = getAvatarColor(message.sender);

    messageElement.appendChild(avatarElement);

    const usernameElement = document.createElement("span");
    const usernameText = document.createTextNode(message.sender);
    usernameElement.appendChild(usernameText);
    messageElement.appendChild(usernameElement);
  }

  const textElement = document.createElement("p");
  const messageText = document.createTextNode(message.content);
  textElement.appendChild(messageText);

  messageElement.appendChild(textElement);

  $messageArea.appendChild(messageElement);
  $messageArea.scrollTop = $messageArea.scrollHeight;
}

/* 랜덤 프로필 이미지 색상 */
function getAvatarColor(messageSender) {
  let hash = 0;
  for (let i = 0; i < messageSender.length; i++) {
    hash = 31 * hash + messageSender.charCodeAt(i);
  }
  const index = Math.abs(hash % colors.length);
  return colors[index];
}

$usernameForm.addEventListener("submit", connect, true);
$messageForm.addEventListener("submit", sendMessage, true);
