package com.example.Simpletodolist.serviceImpl;

import com.example.Simpletodolist.dto.TaskRequestDTO;
import com.example.Simpletodolist.dto.TaskResponseDTO;
import com.example.Simpletodolist.model.Task;
import com.example.Simpletodolist.repo.TaskRepo;
import com.example.Simpletodolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepo taskRepo;


    @Override
    public String addTask(TaskRequestDTO taskRequestDTO) {
        Optional<Task> existingTask=taskRepo.findById(taskRequestDTO.getId());
        if (existingTask.isEmpty()){
            throw new RuntimeException("Task not found");
        }
        Task task = new Task();
        task.setTaskName(taskRequestDTO.getTaskName());
        task.setActive(true);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        taskRepo.save(task);
        return "Created Successfully";
    }

    @Override
    public TaskResponseDTO getTask(String id) {
        Task task = taskRepo.findById(id)
                .orElseThrow(()->new RuntimeException("task not found"));
        TaskResponseDTO taskResponseDTO= new TaskResponseDTO();
        taskResponseDTO.setId(task.getId());
        taskResponseDTO.setTaskName(task.getTaskName());
        taskResponseDTO.setActive(task.isActive());
        taskResponseDTO.setCreatedAt(String.valueOf(task.getCreatedAt()));
        taskResponseDTO.setUpdatedAt(String.valueOf(task.getUpdatedAt()));
        return taskResponseDTO;
    }

    @Override
    public List<TaskResponseDTO> getAllTasks() {
        List<Task> taskList=taskRepo.findAllIsActive();
        List<TaskResponseDTO> taskResponseDTOList = new ArrayList<>();
        for(Task task:taskList){
            TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
            taskResponseDTO.setId(task.getId());
            taskResponseDTO.setTaskName(task.getTaskName());
            taskResponseDTO.setActive(task.isActive());
            taskResponseDTO.setCreatedAt(String.valueOf(task.getCreatedAt()));
            taskResponseDTO.setUpdatedAt(String.valueOf(task.getUpdatedAt()));
            taskResponseDTOList.add(taskResponseDTO);
        }
        return taskResponseDTOList;
    }

    @Override
    public String updateTask(TaskRequestDTO taskRequestDTO) {
        Task task = taskRepo.findById(taskRequestDTO.getId())
                .orElseThrow(()->new RuntimeException("task not found"));
        task.setTaskName(taskRequestDTO.getTaskName());
        task.setUpdatedAt(LocalDateTime.now());
        taskRepo.save(task);
        return "Updated Successfully";
    }

    @Override
    public String deleteTask(String id) {
        Task task = taskRepo.findById(id)
                .orElseThrow(()->new RuntimeException("task not found"));
        task.setActive(false);
        taskRepo.save(task);
        return "Deleted Successfully";
    }
}
