package com.example.mongo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.mongo.model.Task;
import com.example.mongo.repo.TaskRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaskService
{
	private final TaskRepo taskRepo;

	public TaskService(TaskRepo taskRepo)
	{
		this.taskRepo = taskRepo;
	}

	// CRUD CREATE, READ, UPDATE, DELETE

	public Task addTask(Task task)
	{
		task.setTaskId(UUID.randomUUID().toString().split("-")[0]);

		Task addedTask = taskRepo.save(task);
		log.info("Added new task successfully into collection: {}", addedTask);

		return addedTask;
	}

	public List<Task> findAllTasks()
	{
		return taskRepo.findAll();
	}

	public Task getTaskByTaskId(String taskId)
	{
		return taskRepo.findById(taskId).get();
	}

	public List<Task> getTaskBySeverity(int severity)
	{
		return taskRepo.findBySeverity(severity);
	}

	public List<Task> getTaskByAssignee(String assignee)
	{
		return taskRepo.getTaskByAssignee(assignee);
	}

	public Task updateTask(Task taskRequest)
	{
		// get the existing document from DB
		// populate new value from request to existing object/entity/document
		Task existingTask = taskRepo.findById(taskRequest.getTaskId()).get();
		log.info("existing task that is going to be updated on DB is: {}", existingTask);

		existingTask.setDescription(taskRequest.getDescription());
		existingTask.setSeverity(taskRequest.getSeverity());
		existingTask.setAssignee(taskRequest.getAssignee());
		existingTask.setStoryPoint(taskRequest.getStoryPoint());

		return taskRepo.save(existingTask);
	}

	public String deleteTask(String taskId)
	{
		taskRepo.deleteById(taskId);
		log.info("task deleted from collection is: {}", taskId);

		return taskId + " task deleted from dashboard";
	}
}
