package com.demo.task.api.dto;

import com.demo.task.domain.Task;
import com.demo.task.domain.TaskStatus;
import java.time.Instant;

public record TaskDto(Long id, String title, String description, TaskStatus status, Instant createdAt, Instant dueAt) {
  public static TaskDto of(Task t) {
    return new TaskDto(t.getId(), t.getTitle(), t.getDescription(), t.getStatus(), t.getCreatedAt(), t.getDueAt());
  }
}
