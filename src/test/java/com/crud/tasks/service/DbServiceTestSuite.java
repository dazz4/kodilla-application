package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTestSuite {

    @InjectMocks
    private DbService dbService;

    @Test
    public void getAllTasks() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(Long.valueOf(1), "Test Task", "Shopping"));

        //When
        when(dbService.getAllTasks()).thenReturn(taskList);

        //Then
        assertEquals(1, dbService.getAllTasks().size());
    }

    @Test
    public void testGetTask() {
        //Given
        Task task = new Task(Long.valueOf(1), "Test Task", "Shopping");

        //When
        when(dbService.getTask(Long.valueOf(1))).thenReturn(Optional.of(task));

        //Then
        assertNotNull(dbService.getTask(Long.valueOf(1)));
    }

    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task(Long.valueOf(1), "Test Task", "Shopping");

        //When
        when(dbService.saveTask(task)).thenReturn(task);

        //Then
        assertEquals(task, dbService.saveTask(task));
    }

    @Test
    public void testDeleteTask() {
        //Given
        Task task = new Task(Long.valueOf(1), "Test Task", "Shopping");

        //When
        when(dbService.deleteTask(Long.valueOf(1))).thenReturn(true);

        //Then
        assertEquals(true, dbService.deleteTask(Long.valueOf(1)));
    }
}