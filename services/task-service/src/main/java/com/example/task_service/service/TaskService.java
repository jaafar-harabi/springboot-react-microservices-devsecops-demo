package com.example.task.service;

import com.example.task.entity.Task;
import com.example.task.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

  private final TaskRepository repo;

  public TaskService(TaskRepository repo) {
    this.repo = repo;
  }

  public List<Task> list() { return repo.findAll(); }

  public Task create(String title) { return repo.save(new Task(title)); }

  public Task get(Long id) { return repo.findById(id).orElseThrow(); }

  public Task update(Long id, String title, boolean done) {
    Task t = get(id);
    t.setTitle(title);
    t.setDone(done);
    return repo.save(t);
  }

  public void delete(Long id) { repo.deleteById(id); }
}
