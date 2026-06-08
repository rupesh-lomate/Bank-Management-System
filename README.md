# Bank Management System

## Overview

Bank Management System is a web-based banking application developed using Java, JSP, Servlets, JDBC, and Oracle Database. The application provides separate functionalities for customers and employees through role-based authentication and authorization.

## Features

### Authentication & Security

* Role-based login for Customers and Employees
* Session management to maintain user authentication
* Forgot Password functionality
* OTP verification during customer registration

### Customer Module

* Check Account Balance
* Transfer Money to Other Accounts
* View Transaction History
* Secure Login and Account Management

### Employee Module

* Create New Bank Accounts
* Deposit Money
* Withdraw Money
* Modify Account Details
* Manage Customer Accounts

### Customer Registration

* Customers can register only after their account has been created by an employee
* Registration requires Account Number and Registered Phone Number
* OTP verification is performed before account activation

## Technologies Used

* Java
* JSP
* Servlets
* JDBC
* Oracle Database
* Apache Tomcat
* HTML/CSS
* Eclipse IDE

## Key Concepts Implemented

* MVC-based application structure
* Session Tracking
* Role-Based Access Control (RBAC)
* JDBC Database Connectivity
* Transaction Management
* OTP-Based User Verification
* CRUD Operations

## Project Workflow

1. Employee creates a customer bank account.
2. Customer registers using Account Number and Phone Number.
3. OTP verification is completed.
4. Customer can log in and access banking services.
5. Employees can manage customer accounts and perform banking operations.

## Future Enhancements

* Email Notifications
* Account Statement Download
* Online Bill Payments
* Spring Boot Migration
* REST API Integration
