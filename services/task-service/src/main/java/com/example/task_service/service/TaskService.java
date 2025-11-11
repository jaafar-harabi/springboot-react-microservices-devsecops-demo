package com.demo.task.service;

import com.demo.task.api.dto.TaskCreateDto;
import com.demo.task.api.dto.TaskDto;
import com.demo.task.api.dto.TaskUpdateDto;
import com.demo.task.domain.TaskStatus;
import com.demo.task.repo.TaskRepository;
import com.demo.task.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskService {

  private final TaskRepository repo;

  public TaskService(TaskRepository repo) {
    this.repo = repo;
  }

  @Transactional(readOnly = true)
  public Page<TaskDto> list(Pageable pageable, TaskStatus status) {
    if (status == null) return repo.findAll(pageable).map(TaskDto::of);
    return repo.findByStatus(status, pageable).map(TaskDto::of);
  }

  public TaskDto create(TaskCreateDto in) {
    Task saved = repo.save(in.toEntity());
    return TaskDto.of(saved);
  }

  @Transactional(readOnly = true)
  public TaskDto get(Long id) {
    Task t = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found: " + id));
    return TaskDto.of(t);
  }

  public TaskDto update(Long id, TaskUpdateDto in) {
    Task t = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found: " + id));
    in.apply(t);
    return TaskDto.of(t);
  }

  public void delete(Long id) {
    repo.deleteById(id);
  }
}
