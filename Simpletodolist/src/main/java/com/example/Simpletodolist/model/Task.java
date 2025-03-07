package com.example.Simpletodolist.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection="Simpletodolist")
public class Task {

    @Id
    private String id;
    private String taskName;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
