# E-Hotel Database Management System

A relational database system designed to support hotel booking and management operations, including hotel chains, hotels, rooms, customers, employees, bookings, rentals, payments, and archived records.

This project was developed as a university database systems project and focuses on relational database design and SQL implementation.

## Technologies

- MySQL
- SQL
- Java
- JSP / Servlets
- Apache Tomcat

## Key Features

- Designed an ER model and relational database schema
- Implemented tables with primary and foreign key constraints
- Populated the database with sample hotel, room, customer, and booking data
- Created SQL queries using joins, aggregation, and nested queries
- Implemented database triggers for business rules
- Created indexes to improve query performance
- Created SQL views for room availability and hotel capacity
- Maintained booking and rental records

## Database Design

### ER Diagram

![ER Diagram](design/er-diagram.png)

### Relational Schema

![Relational Schema](design/relational-schema.png)

## Repository Structure

```text
e-hotel-database-system/
│
├── database/
│   ├── 01_schema.sql
│   ├── 02_sample_data.sql
│   ├── 03_queries.sql
│   ├── 04_triggers.sql
│   ├── 05_indexes.sql
│   └── 06_views.sql
│
├── design/
│   ├── er-diagram.png
│   └── relational-schema.png
│
├── web-app/
│   ├── src/
│   └── web/
│
└── README.md
