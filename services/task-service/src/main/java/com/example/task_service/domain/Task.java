package com.demo.task.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import java.time.Instant;

@Entity
@Table(name = "tasks")
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String title;

  @Column(length = 2000)
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TaskStatus status = TaskStatus.TODO;

  @Column(name = "created_at", nullable = false, updatable = false)
  @PastOrPresent
  private Instant createdAt = Instant.now();

  @Column(name = "due_at")
  private Instant dueAt;

  public Long getId() { return id; }
  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
  public TaskStatus getStatus() { return status; }
  public void setStatus(TaskStatus status) { this.status = status; }
  public Instant getCreatedAt() { return createdAt; }
  public Instant getDueAt() { return dueAt; }
  public void setDueAt(Instant dueAt) { this.dueAt = dueAt; }
}
