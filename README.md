# 🏦 Console Bank

A simple **Java Console-Based Banking System** that allows users to manage bank accounts and perform common banking operations through an interactive command-line menu.

## 📌 Project Overview

Console Bank is a Java-based banking application designed to demonstrate basic banking operations using a service-based architecture.

The application provides an interactive console menu where users can:

* Open a new bank account
* Deposit money
* Withdraw money
* Transfer money between accounts
* View account statements
* List all accounts
* Search accounts by customer name

---

## ✨ Features

### 1. Open Account

Create a new bank account by providing:

* Customer name
* Customer email
* Account type
* Initial deposit

Supported account types:

```text
SAVING
CURRENT
```

### 2. Deposit

Users can deposit money into an existing account by providing:

* Account number
* Deposit amount

### 3. Withdraw

Users can withdraw money from their account by providing:

* Account number
* Withdrawal amount

### 4. Transfer

Money can be transferred between two accounts.

Required information:

* From account number
* To account number
* Transfer amount

### 5. Account Statement

Displays the transaction history of an account, including:

* Transaction timestamp
* Transaction type
* Amount
* Transaction note

Example:

```text
2026-10-01T10:30 | Deposit | 5000.0 | Deposit
2026-10-01T11:00 | Withdrawal | 1000.0 | withdrawal
```

### 6. List Accounts

Displays all available bank accounts with:

* Account number
* Account type
* Current balance

### 7. Search Account

Allows users to search accounts using a customer's name.

Example:

```text
Customer name contains: Aakash
```

---

## 🖥️ Console Menu

When the application starts, the following menu is displayed:

```text
$Welcome to Console Bank$

1) Open Account
2) Deposit
3) Withdraw
4) Transfer
5) Account Statement
6) List Accounts
7) Search Account by Customer Name
0) Exit
```

---


### Main Components

**Main.java**

Handles:

* Console input
* Menu display
* User choices
* Calling banking operations

**BankService.java**

Defines the banking operations that the application supports.

**BankServiceImpl.java**

Contains the implementation of the banking operations.

---

## 🛠️ Technologies Used

* Java
* Object-Oriented Programming
* Java Collections / Data Handling
* Interface and Implementation
* Service Layer Architecture
* `Scanner` for console input
* Java Lambda Expressions
* Switch Expressions

---

## ▶️ How to Run

### 1. Clone the Repository

```bash
git clone <repository-url>
```

### 2. Open the Project

Open the project in:

* IntelliJ IDEA
* Eclipse
* VS Code

### 3. Run the Application

Run:

```text
Main.java
```

The application will start in the console.

---

## 💡 Example Workflow

```text
$Welcome to Console Bank$

1) Open Account
2) Deposit
3) Withdraw
4) Transfer
5) Account Statement
6) List Accounts
7) Search Account by Customer Name
0) Exit

CHOOSE: 1

Customer name:
Mangal

Customer email:
Mangal@example.com

Account Type (SAVING/CURRENT):
SAVING

Initial deposit (optional,blank for 0):
5000

Account opened: ACC1001
```

After opening the account, users can use the account number for deposits, withdrawals, transfers, and statements.

---

## 🎯 Learning Objectives

This project demonstrates practical implementation of:

* Java classes and objects
* Interfaces
* Service implementation
* Method calling
* Encapsulation
* User input handling
* Conditional logic
* Switch expressions
* Lambda expressions
* Basic banking transaction flow

---

## 🚀 Future Enhancements

Possible improvements for the project include:

* Database integration using MySQL
* Spring Boot REST API
* User authentication and authorization
* Password/PIN-based account security
* Transaction validation
* Minimum balance rules
* Transaction history persistence
* Admin dashboard
* Web-based frontend
* Unit and integration testing

---

## 👨‍💻 Author

**Aakash**

Java Console Banking System developed as a practical Java project.



