# Rental Property Management System

- A backend-focused application built using Java, Spring Boot, and MongoDB to manage rental property records. It supports full CRUD operations and custom filtering for property listings, suitable for property managers and rental platforms.
---

## Features

- **Property Management**: Add, update, delete, and retrieve rental properties with fields like property ID, rental amount, number of bedrooms, location, and city.
- **Search and Filtering**: Retrieve properties based on custom filters such as rental range, number of bedrooms, location, and city.
- **Tenant and Payment Handling** (for GUI version): Manage tenant records, lease agreements, and monthly rent payments using a desktop interface.
- **Data Validation**: Ensures only valid and consistent data is stored by applying validation rules to all inputs.
- **Unit Testing**: Includes test cases for DAO and service layers using JUnit to verify core functionality and data accuracy.
- **MongoDB Integration**: Stores and manages data in a flexible NoSQL database using the MongoDB Java driver or Spring Data MongoDB.
---

## Project Structure

- **RentalPropertyDAO**: Handles MongoDB operations
- **RentalPropertyService**: Contains business logic and filters
- **Main.java**: Entry point for testing and demonstrations

---

## Prerequisites

- **Java 11+**
- **MongoDB** installed and running on `localhost:27017`
- **JAR Driver** files

---
## Project Modules

- `Login.java` – Handles user authentication  
- `MainFrame.java` – Central dashboard  
- `AddTenant.java` – Add new tenant records  
- `UpdateTenant.java` – Edit existing data  
- `Payment.java` – Record and track payments  
- `DBConnection.java` – Connects to MongoDB

---

## Technologies Used

- **Language**: Java 11  
- **Framework**: Spring Boot  
- **Database**: MongoDB  
- **IDE**: Eclipse  
- **Testing**: JUnit

---
## What This Project Demonstrates

- Complete **CRUD functionality** using Java and MongoDB  
- Real-world software design with GUI and database integration  
- Experience in building modular Java applications  
- Understanding of data storage, retrieval, and secure login systems
---
## Output Example

![Dashboard Screenshot](https://github.com/user-attachments/assets/7f173774-af40-4df7-a1e8-8a7f7b5d97f3)

---

## 📌 Note

This project is built for demonstration and learning purposes. It can be extended into a full-fledged rental management system with multi-user support and cloud integration.

---
