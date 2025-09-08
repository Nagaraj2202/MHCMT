// Final Mental Health Chatbot Code with Fallback and Backend Integration

// Replace with actual DOM references
const userInput = document.getElementById('userInput');   // Your input field
const sendBtn = document.getElementById('sendBtn');       // Your send button
const userId = 'user123'; // Replace with actual dynamic user ID if available

// Function to add a chat message to the UI
function addMessage(message, sender) {
  const chatBox = document.getElementById('chatBox'); // The chat message container
  const msgDiv = document.createElement('div');
  msgDiv.className = sender === 'user' ? 'message user' : 'message bot';
  msgDiv.textContent = message;
  chatBox.appendChild(msgDiv);
  chatBox.scrollTop = chatBox.scrollHeight;
}

// Function to generate fallback reply
function getBotReply(message) {
  const msg = message.toLowerCase();

  if (msg.includes("i am feeling very bad") || msg.includes("i feel terrible")) {
    return "I'm really sorry you're feeling this way. Would you like to talk about it?";
  } else if (msg.includes("i am feeling very low") || msg.includes("i am down today")) {
    return "It's okay to have low days. I'm here with you. Let’s talk.";
  } else if (msg.includes("hi") || msg.includes("hello")) {
    return "hi, nag's what are you see today.";
  } else if (msg.includes("can you motivation quotes") || msg.includes("send me now")) {
    return "sure, nag's first you have success in your life then to go next step";
  } else if (msg.includes("i can't sleep") || msg.includes("i have trouble sleeping")) {
    return "Sleep troubles can be tough. Have you tried a calm night routine or breathing exercises?";
  } else if (msg.includes("i feel like crying") || msg.includes("i cried today")) {
    return "It’s okay to cry. Crying is a healthy way to release emotions.";
  } else if (msg.includes("i am feeling anxious") || msg.includes("my heart is racing")) {
    return "You’re not alone. Let’s take a moment to breathe together.";
  } else if (msg.includes("i want to give up") || msg.includes("i don't see a way out")) {
    return "Please don’t give up. Things can get better — you’re not alone, and help is available.";
  } else if (msg.includes("i feel lonely") || msg.includes("i have no one to talk to")) {
    return "You’re not alone anymore. I’m here for you, always.";
  } else if (msg.includes("i feel like a failure") || msg.includes("i always mess up")) {
    return "We all make mistakes. Failing doesn’t define your worth. You're trying — and that matters.";
  } else if (msg.includes("i am not good enough") || msg.includes("i feel worthless")) {
    return "You are enough, just as you are. Let’s talk about what’s making you feel this way.";
  } else if (msg.includes("no one understands me") || msg.includes("nobody gets it")) {
    return "I hear you. Sometimes it feels like that, but I want to understand. Share with me.";
  } else if (msg.includes("i feel stuck") || msg.includes("i'm not moving forward")) {
    return "Feeling stuck is frustrating. Let’s figure out what’s blocking you — together.";
  } else if (msg.includes("i'm scared of the future") || msg.includes("i'm afraid of what comes next")) {
    return "The future can be uncertain, but you don’t have to face it alone. I’m here for you.";
  } else if (msg.includes("i don't want to talk to anyone") || msg.includes("i want to be alone")) {
    return "That's okay. I'm here silently with you — whenever you're ready to talk.";
  } else if (msg.includes("i feel like nobody cares") || msg.includes("i feel ignored")) {
    return "You matter, and your feelings are valid. I'm here and I care about what you're going through.";
  } else if (msg.includes("i feel empty inside") || msg.includes("nothing makes me happy")) {
    return "Emptiness can be heavy. But you’re not alone in it. Let’s talk about it, step by step.";
  } else if (msg.includes("i want to hurt myself") || msg.includes("i feel like ending it")) {
    return "I'm really sorry you're feeling this way. You're not alone. Please reach out to someone you trust or a mental health professional.";
  } else if (msg.includes("i can't focus") || msg.includes("my mind is racing")) {
    return "That sounds overwhelming. Let’s pause and do a short grounding exercise together.";
  } else if (msg.includes("i feel overwhelmed with everything") || msg.includes("everything is too much")) {
    return "Take a deep breath. Let's break things down — one small step at a time.";
  } else if (msg.includes("i miss someone") || msg.includes("i feel heartbroken")) {
    return "Loss and longing can be painful. Let’s talk about your feelings. I'm listening.";
  } else if (msg.includes("i'm tired of pretending to be okay") || msg.includes("i wear a fake smile")) {
    return "Thank you for being honest. You don’t need to pretend here — I’m here for the real you.";
  } else if (msg.includes("i hate myself") || msg.includes("i don't like who i am")) {
    return "It’s hard feeling that way. But please know you have value — even when you can’t see it.";
  } else if (msg.includes("i feel trapped") || msg.includes("i have no escape")) {
    return "That sounds really difficult. You’re not stuck forever — there is always a way forward.";
  } else if (msg.includes("i have no motivation") || msg.includes("i can’t do anything")) {
    return "It’s okay to rest. You don’t have to do everything right now. Let’s take it one moment at a time.";
  } else if (msg.includes("i want to talk but i don’t know how") || msg.includes("i can’t explain what i feel")) {
    return "That’s perfectly okay. Just start anywhere — I’ll be here to listen without judgment.";
  } else if (msg.includes("i feel like i'm losing control") || msg.includes("everything is falling apart")) {
    return "That’s a scary feeling. I’m here to hold space for you — you are not alone.";
  } else if (msg.includes("i’m exhausted mentally") || msg.includes("my mind is tired")) {
    return "Mental fatigue is real. Take a moment to rest. You deserve peace.";
  } else if (msg.includes("i feel ashamed") || msg.includes("i can’t forgive myself")) {
    return "Shame is heavy, but you are human. It’s okay to make mistakes and learn. I’m here with compassion.";
  } else if (msg.includes("i feel like i don’t belong") || msg.includes("i’m an outsider")) {
    return "You belong here. Your voice, your thoughts, your existence — they matter.";
  } else if (msg.includes("i feel scared at night") || msg.includes("i hate being alone at night")) {
    return "Nighttime can bring out big feelings. Let’s talk through it together. You are safe here.";
  } else if (msg.includes("i feel better now") || msg.includes("thank you for listening")) {
    return "That’s wonderful to hear. I’ll always be here whenever you need someone to talk to.";
  } else {
    return "I'm here to listen. Tell me more about how you're feeling.";
  }
}

// Function to send message to servlet or fallback
function sendMessage() {
  const message = userInput.value.trim();
  if (!message) return;

  addMessage(message, 'user');
  userInput.value = '';
  userInput.disabled = true;
  sendBtn.disabled = true;

  fetch('ChatBotServlet', {
    method: 'POST',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    body: `user_id=${userId}&message=${encodeURIComponent(message)}`
  })
  .then(res => {
    if (!res.ok) throw new Error("Servlet failed");
    return res.text();
  })
  .then(reply => {
    addMessage(reply, 'bot');
  })
  .catch(err => {
    console.warn("Backend error, using fallback:", err);
    const fallback = getBotReply(message);
    addMessage(fallback, 'bot');
  })
  .finally(() => {
    userInput.disabled = false;
    sendBtn.disabled = false;
    userInput.focus();
  });
}

// Optional: Allow pressing "Enter" to send message
userInput.addEventListener("keydown", function (e) {
  if (e.key === "Enter") sendMessage();
});
sendBtn.addEventListener("click", sendMessage);
