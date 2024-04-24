package com.andersen.testing.todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    void addTodoItem(TodoItem item);
    void removeTodoItem(TodoItem item);
    void updateTodoItem(TodoItem item);
    List<TodoItem> getAllTodoItems();
    Optional<TodoItem> findTodoItemByDescription(String description);
}
