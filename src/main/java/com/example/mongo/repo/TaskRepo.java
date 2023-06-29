package com.example.mongo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mongo.model.Task;

public interface TaskRepo extends MongoRepository<Task, String>
{

	List<Task> findBySeverity(int severity);

	@Query("{assignee: ?0 }")
	List<Task> getTaskByAssignee(String assignee);
}
