package com.andersen.testing.todo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class TodoService {
    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public void addTodoItem(String description) {
        TodoItem item = new TodoItem(description, false);
        repository.addTodoItem(item);
    }

    public void removeTodoItem(String description) {
        Optional<TodoItem> optionalItem = repository.findTodoItemByDescription(description);

        if (optionalItem.isPresent()) {
            repository.removeTodoItem(optionalItem.get());
        } else {
            throw new NoSuchElementException("To-do item not found with description: " + description);
        }
    }

    public void markItemAsCompleted(String description) {
        Optional<TodoItem> optionalItem = repository.findTodoItemByDescription(description);

        if (optionalItem.isPresent()) {
            TodoItem item = optionalItem.get();
            item.setCompleted(true);
            repository.updateTodoItem(item);
        } else {
            throw new NoSuchElementException("To-do item not found with description: " + description);
        }
    }

    public List<TodoItem> getAllTodoItems() {
        return repository.getAllTodoItems();
    }
}