package com.demo.task.api.dto;

import com.demo.task.domain.Task;
import com.demo.task.domain.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import java.time.Instant;

public record TaskCreateDto(@NotBlank String title, String description, TaskStatus status, Instant dueAt) {
  public Task toEntity() {
    Task t = new Task();
    t.setTitle(title);
    t.setDescription(description);
    if (status != null) t.setStatus(status);
    t.setDueAt(dueAt);
    return t;
  }
}
