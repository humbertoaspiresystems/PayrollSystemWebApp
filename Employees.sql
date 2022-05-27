CREATE DATABASE IF NOT EXISTS Employees;
USE Employees;

CREATE TABLE IF NOT EXISTS Employees(
firstName CHAR(25),
lastName CHAR(25),
employeeId INT NOT NULL,
salaryType CHAR(25),
startYear INT NOT NULL,
salary INT NOT NULL,
paymentStatus CHAR(20),
username CHAR(25),
password CHAR(25)
);
/*INSERT IGNORE INTO Employees VALUE ('jose','bengochea',12,'full time',2022,18000,'Unpaid','jose.b','123');*/

SELECT * FROM Employees;

