package com.andersen.testing.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class TodoRepositoryImpl implements TodoRepository {
    private final List<TodoItem> todoItems = new ArrayList<>();

    @Override
    public void addTodoItem(TodoItem item) {
        todoItems.add(item);
    }

    @Override
    public void removeTodoItem(TodoItem item) {
        todoItems.remove(item);
    }

    @Override
    public void updateTodoItem(TodoItem item) {
        for (int i = 0; i < todoItems.size(); i++) {
            TodoItem currentItem = todoItems.get(i);

            if (currentItem.getDescription().equals(item.getDescription())) {
                currentItem.setCompleted(item.isCompleted());

                todoItems.set(i, currentItem);
                return;
            }
        }

        throw new NoSuchElementException("To-do item not found with description: " + item.getDescription());
    }

    @Override
    public List<TodoItem> getAllTodoItems() {
        return new ArrayList<>(todoItems);
    }

    @Override
    public Optional<TodoItem> findTodoItemByDescription(String description) {
        for (TodoItem item : todoItems) {
            if (item.getDescription().equals(description)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }
}