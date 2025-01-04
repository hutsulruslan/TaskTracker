package org.example.TaskModel;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {
    final private int id;
    private String description;
    private String status;
    final private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(int id, String description, String status) {
        this.id = id;
        this.description = description;
        this.status = status;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    //Task Id getter
    public int getId() {
        return id;
    }

    //Task Description getter and setter
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Task Status getter and setter
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //Task create DateTime getter
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    //Task update DateTime getter and setter
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(description, task.description) && Objects.equals(status, task.status) && Objects.equals(createdAt, task.createdAt) && Objects.equals(updatedAt, task.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, status, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
