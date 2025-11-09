package com.example.task.controller;

import com.example.task.entity.Task;
import com.example.task.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

record TaskCreateRequest(String title) {}
record TaskUpdateRequest(String title, boolean done) {}

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

  private final TaskService svc;

  public TaskController(TaskService svc) {
    this.svc = svc;
  }

  @GetMapping
  public List<Task> list() { return svc.list(); }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Task create(@RequestBody TaskCreateRequest b) { return svc.create(b.title()); }

  @GetMapping("/{id}")
  public Task get(@PathVariable Long id) { return svc.get(id); }

  @PutMapping("/{id}")
  public Task update(@PathVariable Long id, @RequestBody TaskUpdateRequest b) {
    return svc.update(id, b.title(), b.done());
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) { svc.delete(id); }
}
