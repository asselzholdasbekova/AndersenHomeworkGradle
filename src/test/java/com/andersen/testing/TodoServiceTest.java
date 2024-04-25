package com.andersen.testing;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.andersen.testing.todo.TodoItem;
import com.andersen.testing.todo.TodoRepository;
import com.andersen.testing.todo.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

class TodoServiceTest {

    @Mock
    private TodoRepository repository;

    private TodoService todoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        todoService = new TodoService(repository);
    }

    @Test
    void addTodoItem_ShouldAddNewItem() {
        // Given
        String description = "Test todo";

        // When
        todoService.addTodoItem(description);

        // Then
        verify(repository, times(1)).addTodoItem(any(TodoItem.class));
    }

    @Test
    void removeTodoItem_ShouldRemoveItem() {
        // Given
        String description = "Test todo";
        TodoItem item = new TodoItem(description, false);
        when(repository.findTodoItemByDescription(description)).thenReturn(Optional.of(item));

        // When
        todoService.removeTodoItem(description);

        // Then
        verify(repository, times(1)).removeTodoItem(item);
    }

    @Test
    void markItemAsCompleted_ShouldUpdateItemToCompleted() {
        // Given
        String description = "Test todo";
        TodoItem item = new TodoItem(description, false);
        when(repository.findTodoItemByDescription(description)).thenReturn(Optional.of(item));

        // When
        todoService.markItemAsCompleted(description);

        // Then
        assertThat(item.isCompleted()).isTrue();
        verify(repository, times(1)).updateTodoItem(item);
    }

    @Test
    void getAllTodoItems_ShouldReturnAllItems() {
        // Given
        List<TodoItem> expectedItems = List.of(
                new TodoItem("Test todo 1", false),
                new TodoItem("Test todo 2", true)
        );
        when(repository.getAllTodoItems()).thenReturn(expectedItems);

        // When
        List<TodoItem> actualItems = todoService.getAllTodoItems();

        // Then
        assertThat(actualItems).isEqualTo(expectedItems);
    }

    @Test
    void removeTodoItem_ShouldThrowExceptionWhenItemNotFound() {
        // Given
        String description = "Test todo";
        when(repository.findTodoItemByDescription(description)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(NoSuchElementException.class, () -> {
            todoService.removeTodoItem(description);
        });
    }

    @Test
    void markItemAsCompleted_ShouldThrowExceptionWhenItemNotFound() {
        // Given
        String description = "Test todo";
        when(repository.findTodoItemByDescription(description)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(NoSuchElementException.class, () -> {
            todoService.markItemAsCompleted(description);
        });
    }
}