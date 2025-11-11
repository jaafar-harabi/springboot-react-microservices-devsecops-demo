package com.demo.task.api;

import com.demo.task.api.dto.TaskCreateDto;
import com.demo.task.api.dto.TaskDto;
import com.demo.task.api.dto.TaskUpdateDto;
import com.demo.task.domain.TaskStatus;
import com.demo.task.service.TaskService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

  private final TaskService service;
  public TaskController(TaskService service) { this.service = service; }

  @GetMapping
  @PreAuthorize("hasAuthority('SCOPE_tasks.read')")
  public Page<TaskDto> list(@PageableDefault(size = 20) Pageable pageable,
                            @RequestParam(required = false) TaskStatus status) {
    return service.list(pageable, status);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('SCOPE_tasks.read')")
  public TaskDto get(@PathVariable Long id) { return service.get(id); }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasAuthority('SCOPE_tasks.write')")
  public TaskDto create(@Valid @RequestBody TaskCreateDto in) { return service.create(in); }

  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('SCOPE_tasks.write')")
  public TaskDto update(@PathVariable Long id, @Valid @RequestBody TaskUpdateDto in) {
    return service.update(id, in);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasAuthority('SCOPE_tasks.write')")
  public void delete(@PathVariable Long id) { service.delete(id); }
}
