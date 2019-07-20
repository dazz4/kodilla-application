package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskController taskController;

    @Test
    public void testGetTasks() throws Exception {
        //Given
        List<TaskDto> tasks = new ArrayList<>();
        tasks.add(new TaskDto(1L, "First Task", "Shopping"));

        when(taskController.getTasks()).thenReturn(tasks);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "First Task", "Shopping");

        when(taskController.getTask(1L)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTask").param("id", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("First Task")))
                .andExpect(jsonPath("$.content", is("Shopping")));
    }

    @Test
    public void testCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "First Task", "Shopping");

        when(taskController.createTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("First Task")))
                .andExpect(jsonPath("$.content", is("Shopping")));
    }

    @Test
    public void testUpdateTask() throws Exception {
        //Given
        TaskDto updatedTask = new TaskDto(1L, "Second Task", "Clean");

        when(taskController.updateTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(updatedTask);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedTask);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Second Task")))
                .andExpect(jsonPath("$.content", is("Clean")));
    }

    @Test
    public void testDeleteTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "First Task", "Shopping");

        when(taskController.deleteTask(1L)).thenReturn(true);

        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask?id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(true)));
    }
}