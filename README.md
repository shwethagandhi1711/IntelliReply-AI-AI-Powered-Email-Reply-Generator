# IntelliReply-AI AI-Powered-Email-Reply-Generator

IntelliReply-AI AI-Powered-Email-Reply-Generator is a full-stack application that automatically generates professional email replies using **Google Gemini AI**. It integrates a **Chrome Extension** with a **Spring Boot backend** to help users instantly reply to emails inside Gmail.

---

# 📌 Project Overview

The IntelliReply-AI AI-Powered-Email-Reply-Generator reads the email content from Gmail and generates a professional reply using Artificial Intelligence.

The system workflow:

• Chrome Extension captures email content
• Sends content to Spring Boot backend
• Backend calls Gemini AI API
• AI generates reply
• Reply inserted into Gmail compose box

This improves productivity and saves time.

---

# 🎯 Project Objectives

• Automate email reply writing
• Generate professional replies using AI
• Save user time
• Integrate Chrome Extension with Spring Boot
• Learn real-world AI integration

---

# 🛠 Technology Stack

## Frontend

• Chrome Extension
• JavaScript

## Backend

• Spring Boot
• Java
• WebClient

## AI Service

• Google Gemini API

## Tools

• IntelliJ 
•VS Code
• Chrome Browser

---

# 🧩 Core Modules

## 1. Chrome Extension

Responsibilities:

• Inject AI Reply button in Gmail
• Capture email content
• Call backend API
• Insert generated reply

Files:

content.js
manifest.json

---

## 2. Spring Boot Backend

Responsibilities:

• Receive email content
• Send request to Gemini API
• Process response
• Return reply

Files:

EmailGeneratorController.java
EmailGeneratorService.java

---

## 3. AI Integration Module

Responsibilities:

• Connect to Gemini API
• Generate intelligent reply

---

# 🔄 Workflow and Architecture

## Step-by-Step Workflow

1. User opens Gmail

2. Extension adds AI Reply button

3. User clicks AI Reply

4. Email sent to Spring Boot

5. Spring Boot calls Gemini API

6. AI generates reply

7. Reply inserted into Gmail

---

## Architecture Flow

Gmail
↓
Chrome Extension
↓
Spring Boot Backend
↓
Gemini API
↓
Backend
↓
Extension
↓
Gmail

---

# 🗄 Database Design

No database used.

Reason:

The system generates replies dynamically.

Future version may store:

• Reply history
• User preferences

---

# ⚙ Installation and Setup

---

## Step 1: Clone Repository

```bash
git clone https://github.com/yourusername/email-writer-assistant.git
```

---

## Step 2: Get Gemini API Key 🔑

### Go to Google AI Studio

https://aistudio.google.com/

Login with Google account

---

### Click

Get API Key

↓

Create API Key

↓

Copy API Key

Example:

```
AIzaSyXXXXXXXXXXXX
```

---

## Step 3: Configure API Key

Open:

application.properties

```
spring.application.name=email-writer-sb

gemini.api.url=https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=

gemini.api.key=YOUR_API_KEY
```

Example:

```
gemini.api.key=AIzaSyXXXX
```

---

## Recommended Secure Method 🔐

Use environment variable

Windows:

```
set GEMINI_KEY=your_key
```

Mac/Linux:

```
export GEMINI_KEY=your_key
```

application.properties:

```
gemini.api.key=${GEMINI_KEY}
```

---

## Step 4: Run Backend

Run:

EmailWriterSbApplication.java

Server starts:

```
http://localhost:8080
```

---

## Step 5: Install Chrome Extension

Open Chrome

Go to:

chrome://extensions/

Enable:

Developer Mode

Click:

Load unpacked

Select extension folder

---

# ▶ How to Use

1 Open Gmail

2 Click Compose

3 Click AI Reply

4 Reply generated automatically

---

# 🔌 REST API Endpoints

## Generate Reply

### Endpoint

```
POST /api/email/generate
```

---

### Request

```json
{
  "emailContent": "Meeting request email",
  "tone": "professional"
}
```

---

### Response

```
Generated email reply text
```

---

# ✅ Advantages

• Saves time

• Improves productivity

• Generates professional replies

• Easy to use

• Real-world AI integration

• No manual typing

---

# 🚀 Future Enhancements

• Multiple tones

Formal
Casual
Friendly

• Reply history

• User login

• Cloud deployment

• Outlook support

• Mobile support

---

# 🏁 Conclusion

AI Email Writer Assistant is a real-world full-stack AI project that integrates:

Chrome Extension
Spring Boot
Gemini AI

It helps users generate professional email replies instantly.

This project demonstrates strong:

• Full Stack Development
• AI Integration
• Backend Development
• Chrome Extension Skills

---

# 👩‍💻 Author

Shwetha T

---

# ⭐ Support

If you like this project:

Give it a ⭐ on GitHub
