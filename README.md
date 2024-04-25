# Todo Application

## Description

This project is a simple Todo application that demonstrates the use of a `TodoService` to manage todo items. The application includes:

- Adding new todo items.
- Removing existing todo items.
- Marking todo items as completed.
- Retrieving all todo items.

## Project Structure

- **`TodoItem`:** A class representing a single todo item with a description and completion status.
- **`TodoRepository`:** An interface defining the methods for managing todo items.
- **`TodoRepositoryImpl`:** An implementation of `TodoRepository` using an in-memory list.
- **`TodoService`:** The main service class for managing todo items.
- **`tests`:** Contains tests for `TodoService` using JUnit 5 and Mockito.
