package com.example.Simpletodolist.dto;

import lombok.Data;

@Data
public class TaskRequestDTO {

    private String id;
    private String taskName;
    private boolean isActive;
    private String createdAt;
    private String updatedAt;
}
