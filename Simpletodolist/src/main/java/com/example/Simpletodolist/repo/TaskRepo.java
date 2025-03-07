package com.example.Simpletodolist.repo;

import com.example.Simpletodolist.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TaskRepo extends MongoRepository<Task,String> {

    @Query(value = "{'isActive':true}")
    List<Task> findAllIsActive();
}
