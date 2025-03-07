package com.example.Simpletodolist.service;

import com.example.Simpletodolist.dto.TaskRequestDTO;
import com.example.Simpletodolist.dto.TaskResponseDTO;

import java.util.List;

public interface TaskService {

    String addTask(TaskRequestDTO taskRequestDTO);

    TaskResponseDTO getTask(String id);

    List<TaskResponseDTO> getAllTasks();

    String updateTask(TaskRequestDTO taskRequestDTO);

    String deleteTask(String id);


}
