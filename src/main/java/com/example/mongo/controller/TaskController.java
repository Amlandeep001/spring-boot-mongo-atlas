package com.example.mongo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongo.model.Task;
import com.example.mongo.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController
{
	private final TaskService taskService;

	public TaskController(TaskService taskService)
	{
		this.taskService = taskService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Task createTask(@RequestBody Task task)
	{
		return taskService.addTask(task);
	}

	@GetMapping
	public List<Task> getTasks()
	{
		return taskService.findAllTasks();
	}

	@GetMapping("/{taskId}")
	public Task getTaskTas(@PathVariable String taskId)
	{
		return taskService.getTaskByTaskId(taskId);
	}

	@GetMapping("/severity/{severity}")
	public List<Task> findTaskUsingSeverity(@PathVariable int severity)
	{
		return taskService.getTaskBySeverity(severity);
	}

	@GetMapping("/assignee/{assignee}")
	public List<Task> getTaskByAssignee(@PathVariable String assignee)
	{
		return taskService.getTaskByAssignee(assignee);
	}

	@PutMapping
	public Task modifyTask(@RequestBody Task task)
	{
		return taskService.updateTask(task);
	}

	@DeleteMapping("/{taskId}")
	public String deleteTask(@PathVariable String taskId)
	{
		return taskService.deleteTask(taskId);
	}
}
