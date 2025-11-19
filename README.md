RGPV Student Portal

A console-based Student Management System built with Java and SQLite.
This project demonstrates core concepts of JDBC (Java Database Connectivity), SQL, and CRUD operations.

Features

Persistent Storage: Uses SQLite to store student records permanently.

CRUD Operations:

Create: Add new student records.

Read: View all students in a formatted table.

Update: Modify CGPA for existing students.

Delete: Remove student records from the database.

Console Interface: Interactive menu-driven CLI.

Technologies Used

Language: Java

Database: SQLite

Connectivity: JDBC (Java Database Connectivity)

How to Run Locally

Clone the repository

git clone [CLICK ME](https://github.com/Karma-tic/RGPV-Student-Portal.git)
cd RGPV-Student-Portal


Compile the code
Ensure you have the sqlite-jdbc.jar file in the project root.

javac StudentPortal.java


Run the application

# On Mac/Linux
java -cp ".:sqlite-jdbc.jar" StudentPortal

# On Windows
java -cp ".;sqlite-jdbc.jar" StudentPortal


Usage

The application provides a menu to manage student data:

--- RGPV STUDENT PORTAL ---
1. Add Student
2. View All Students
3. Update CGPA
4. Delete Student
5. Exit

