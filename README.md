# 🧠 BC Student Wellness Management System

## 📘 Overview

This is a Java-based application developed as part of the **PRG3781 Project (2025)** for **Belgium Campus**. The system assists with student wellness management by providing:

- A **web-based** login and registration portal.
- A **desktop** application for managing appointments, counselors, and student feedback.

> 🔹 Built with Core Java, JSP/Servlets, Swing, JDBC  
> 🔹 Databases: PostgreSQL (Web App) & JavaDB (Desktop App)

---

## 🛠️ Tech Stack

| Layer         | Technology         |
|---------------|--------------------|
| Backend       | Java SE, Servlets  |
| Frontend      | JSP (Web), Swing (Desktop GUI) |
| Web Server    | Apache Tomcat 9+   |
| Databases     | PostgreSQL, JavaDB (Derby) |
| Tools         | Git, GitHub, JDBC, NetBeans/IntelliJ |

---

## 📁 Project Structure

```bash
BC-Wellness-Management/
├── web-app/
│ ├── index.jsp
│ ├── login.jsp
│ ├── register.jsp
│ ├── dashboard.jsp
│ └── /servlets/
│ ├── LoginServlet.java
│ └── RegisterServlet.java
├── desktop-app/
│ ├── /src/
│ ├── model/
│ ├── view/
│ └── controller/
│ ├── MainApp.java
│ └── db/
│ └── JavaDBConnection.java
├── sql/
│ └── schema_postgresql.sql
├── README.md
└── LICENSE
```

---

## ✅ Features

### 🔐 Web Application (Milestone 1)

- User Registration & Login (JSP + Servlets)
- Validation for email, phone, and password
- PostgreSQL database integration
- Session tracking & redirection
- Personalized dashboard view

### 🖥️ Desktop Application (Milestone 2)

- GUI built with Java Swing
- Appointment Management (Book, View, Update, Cancel)
- Counselor Management (CRUD)
- Feedback System (Rating, Comment, Edit/Delete)
- JavaDB integration for local persistence
- MVC (Model-View-Controller) architecture
- Input validation and error handling

---

## 🧪 Setup Instructions

### 🔧 Prerequisites

- Java JDK 17+
- Apache Tomcat 9+
- PostgreSQL Server (for web app)
- JavaDB / Apache Derby (for desktop app)
- Git

---

### 💡 Web Application Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/your-group-name/BC-Wellness-Management.git
2. Import the web-app/ folder into your IDE (e.g., NetBeans).
3. Setup PostgreSQL:
- Run the script in sql/schema_postgresql.sql to create the users table.
4. Deploy on Tomcat.
- Start Tomcat and open http://localhost:8080/web-app/index.jsp.
