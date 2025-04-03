Bank Management System
This project is a Java-based application designed to manage customer bank accounts, transactions, and balance inquiries. It follows an Object-Oriented Programming (OOP) approach and uses JDBC (Java Database Connectivity) to interact with a MySQL database. Below is a comprehensive breakdown of the system, including its features and technology stack.

📌 Features of the Bank Management System
1️⃣ User Authentication & Login System
Customers log in using their PIN.

The system verifies credentials from the database (login table).

If credentials are correct, the user is granted access to banking functionalities.

2️⃣ ATM Functionalities
Mini Statement – Displays the last 5 transactions along with the overall balance.

Withdraw Money – Deducts balance based on PIN verification.

Deposit Money – Increases balance for the given PIN.

Check Balance – Fetches the current balance from all transactions.

Fund Transfer – Transfers money between accounts.

Change PIN – Allows users to update their PIN securely.

3️⃣ Database Connectivity using JDBC
The system interacts with a MySQL database to:

Store user login details.

Record every transaction (Deposit, Withdrawal).

Maintain account balances.

🛠️ Technology Stack
Frontend (User Interface)
Java Swing – Used for building the graphical user interface (GUI).

Backend (Business Logic & Database Interaction)
Java (JDK 8 or later) – Core programming language.

JDBC (Java Database Connectivity) – Manages database connections and queries.

MySQL – Stores user data, transactions, and balances.

Development Tools & Environment
Eclipse / IntelliJ IDEA – IDE for Java development.

MySQL Workbench – Database management and query execution.

Apache NetBeans (Optional) – Alternative IDE for UI design.
