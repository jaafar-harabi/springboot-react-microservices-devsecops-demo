package com.example.task.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class Task {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  private boolean done = false;

  @Column(nullable = false, updatable = false)
  private Instant createdAt = Instant.now();

  public Task() {}
  public Task(String title) { this.title = title; }

  public Long getId() { return id; }
  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }
  public boolean isDone() { return done; }
  public void setDone(boolean done) { this.done = done; }
  public Instant getCreatedAt() { return createdAt; }
}
