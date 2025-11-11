package com.demo.task.api.dto;

import com.demo.task.domain.Task;
import com.demo.task.domain.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import java.time.Instant;

public record TaskUpdateDto(@NotBlank String title, String description, TaskStatus status, Instant dueAt) {
  public void apply(Task t) {
    t.setTitle(title);
    t.setDescription(description);
    if (status != null) t.setStatus(status);
    t.setDueAt(dueAt);
  }
}
