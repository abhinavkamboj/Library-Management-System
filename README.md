# Library Management System

Welcome to the Library Management System, a Java-based application for managing books, patrons, and library branches.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The Library Management System allows a library to manage books and patrons across multiple branches. Users can search for books, check out books, and manage their inventory in real-time across different library branches.

## Features
- **Branch Management**: Manage multiple library branches.
- **Book Inventory**: Add, remove, and check availability of books in different branches.
- **Patron Management**: Add patrons and track the books they borrow.
- **Search**: Search for books by author or ISBN within a branch.
- **Checkout/Return**: Patrons can check out and return books.

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/Library-Management-System.git
    ```

2. Navigate to the project directory:
    ```bash
    cd Library-Management-System
    ```

3. Compile the project:
    ```bash
    javac -d bin src/*.java
    ```

4. Run the application:
    ```bash
    java -cp bin Main
    ```

## Usage

The main entry point for the application is the `Main` class. You can modify the library, books, and patrons directly in the `Main.java` file. Here’s an overview of the default setup:

- Two library branches are created (`Branch 1` and `Branch 2`).
- Several books and patrons are added to the branches.
- You can search for books by author or ISBN and simulate book checkouts and returns.

Example of how to add books and patrons in `Main.java`:
```java
Book book1 = new Book("Two States", "Chetan Bhagat", "1", 1990);
libraryBranch1.addBookInInventory(book1);

Patron patron1 = new Patron("P1", "John Doe", "johndoe@example.com", 123456789, "123 Main St");
libraryBranch1.addPatronInMemberList(patron1);
