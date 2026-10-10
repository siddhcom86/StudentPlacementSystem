# 🎓 Student Placement Management System

### Manage Student Records. Track Academic Performance. Check Placement Eligibility.

A Java-based Student Placement Management System built using **Java, MySQL, and JDBC** to simplify student record management and placement eligibility checking.

<p align="center">
  <img src="https://img.shields.io/badge/Java-Programming-orange?style=for-the-badge&logo=openjdk" alt="Java"/>
  <img src="https://img.shields.io/badge/MySQL-Database-blue?style=for-the-badge&logo=mysql" alt="MySQL"/>
  <img src="https://img.shields.io/badge/JDBC-Connectivity-red?style=for-the-badge" alt="JDBC"/>
  <img src="https://img.shields.io/badge/Status-In%20Development-yellow?style=for-the-badge" alt="Project Status"/>
</p>

---

## 📌 About the Project

The **Student Placement Management System** is a console-based application designed to manage student information efficiently using a MySQL database.

It provides essential operations for maintaining student records, updating CGPA, searching for students, and checking whether students meet the minimum CGPA requirement for placement eligibility.

This project demonstrates practical knowledge of Java programming, object-oriented programming concepts, SQL queries, database connectivity, and CRUD operations.

## ✨ Key Features

| Feature | Description |
|---|---|
| ➕ Add Student | Register student details in the database |
| 📋 View All Students | Display all stored student records |
| 🔍 Search by ID | Find a student using their unique ID |
| ✏️ Update CGPA | Modify a student's academic performance |
| 🗑️ Delete Student | Remove student records |
| 🎯 Placement Eligibility | Check eligibility based on CGPA ≥ 7.0 |
| 📊 Sort by CGPA | Rank students by CGPA in descending order |

## 🛠️ Tech Stack

<p>
  <img src="https://skillicons.dev/icons?i=java,mysql,vscode" alt="Java, MySQL and VS Code"/>
</p>

- **Language:** Java
- **Database:** MySQL
- **Connectivity:** JDBC
- **Database Driver:** MySQL Connector/J
- **IDE:** Visual Studio Code

## 🏗️ Project Structure

```text
StudentPlacementSystem/
│
├── Student.java
│   └── Main application and student management operations
│
├── DBConnection.java
│   └── MySQL database connection
│
├── lib/
│   └── mysql-connector-j-9.4.0.jar
│
├── .vscode/
│   └── settings.json
│
├── .gitignore
└── README.md
```

## ⚙️ Getting Started

### 1. Prerequisites

Install the following:

- Java JDK
- MySQL Server
- Visual Studio Code or another Java IDE

The project includes the MySQL Connector/J library in the `lib` folder.

### 2. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/StudentPlacementSystem.git
cd StudentPlacementSystem
```

Replace `YOUR_USERNAME` with your GitHub username.

### 3. Create the Database

Open MySQL Workbench and execute:

```sql
CREATE DATABASE IF NOT EXISTS student_placement;

USE student_placement;

CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    cgpa DECIMAL(3,2) NOT NULL,
    course VARCHAR(100) NOT NULL
);
```

### 4. Configure the Database Password

The application reads the MySQL password from an environment variable, rather than storing it directly in the source code.

In Windows PowerShell, run:

```powershell
$env:DB_PASSWORD = Read-Host "Enter MySQL password"
```

Enter your MySQL password when prompted.

Make sure `DBConnection.java` uses the correct database URL, username, and `System.getenv("DB_PASSWORD")`.

> 🔐 **Security:** Never hardcode your actual database password in Java files or upload credentials to GitHub.

### 5. Compile and Run

Compile the application:

```powershell
javac -cp ".;lib/*" Student.java DBConnection.java
```

Run the application:

```powershell
java -cp ".;lib/*" Student
```

Follow the numbered menu to perform student management operations.

## 🎯 Placement Eligibility Logic

The application checks a student's CGPA against the minimum eligibility threshold of **7.0**.

```java
if (cgpa >= 7.0) {
    System.out.println("Eligible for placement");
} else {
    System.out.println("Not eligible for placement");
}
```

*Note: This is the project's configured eligibility rule, not a universal placement requirement.*

## 🧠 Concepts Demonstrated

- Object-Oriented Programming (OOP)
- Java methods and classes
- JDBC database connectivity
- SQL queries and CRUD operations
- Prepared statements for database operations
- Input handling and menu-driven programming
- Sorting and conditional logic
- Environment-based database configuration

## 🚀 Future Enhancements

- [ ] Add a graphical user interface
- [ ] Add student login and administrator authentication
- [ ] Generate placement reports
- [ ] Add company and job-drive management
- [ ] Improve input validation and error handling
- [ ] Add unit and database integration tests

## 👩‍💻 Author

**Student Placement Management System**

Built as a hands-on project to strengthen Java development and database management skills.

---

<p align="center">
  ⭐ If you find this project useful, consider giving the repository a star!
</p>
