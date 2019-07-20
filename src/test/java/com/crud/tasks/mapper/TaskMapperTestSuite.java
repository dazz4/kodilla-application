package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    private TaskMapper mapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(Long.valueOf(1), "Test Title", "Test Content");

        //When
        Task task = mapper.mapToTask(taskDto);

        //Then
        assertNotNull(task);
        assertEquals(Long.valueOf(1), task.getId());
        assertEquals("Test Title", task.getTitle());
        assertEquals("Test Content", task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(Long.valueOf(1), "Test Title", "Test Content");

        //When
        TaskDto taskDto = mapper.mapToTaskDto(task);

        //Then
        assertNotNull(taskDto);
        assertEquals(Long.valueOf(1), taskDto.getId());
        assertEquals("Test Title", taskDto.getTitle());
        assertEquals("Test Content", taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task task = new Task(Long.valueOf(1), "Test Title", "Test Content");
        List<Task> list = new ArrayList<>();
        list.add(task);

        //When
        List<TaskDto> taskDtoList = mapper.mapToTaskDtoList(list);

        //Then
        assertEquals(1, taskDtoList.size());
    }
}