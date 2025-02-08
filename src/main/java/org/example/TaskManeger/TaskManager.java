package org.example.TaskManeger;

import org.example.TaskModel.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = loadTasks();
    }

    private List<Task> loadTasks() {
        return new ArrayList<>();
    }

    public void addTask(String description) {
        int id = tasks.size()+1;
        Task task = new Task(id, description, "todo");
        tasks.add(task);
        System.out.println("Task added (ID: " + id + ")");
    }

    public void updateTask (int id, String description) {
        for (Task task: tasks) {
            if(id == task.getId()) {
                task.setDescription(description);
                task.setUpdatedAt(LocalDateTime.now());
                System.out.println("Task updated successfully (ID: " + id + ")");
                return;
            }
        }
        System.out.println("Task not found (ID: " + id + ")");
    }

    public void markTask(int id, String status) {
        for(Task task: tasks) {
            if (task.getId() == id) {
                task.setStatus(status);
                task.setUpdatedAt(LocalDateTime.now());
                System.out.println("Task marked as " + status + " (ID: " + id + ")");
                return;
            }
        }
        System.out.println("Task not found (ID: " + id + ")");
    }

    public void deleteTask(int id) {
        for (Task task: tasks) {
            if(id == task.getId()) {
                tasks.remove(task);
                System.out.println("Task deleted successfully (ID: " + id + ")");
                return;
            }
        }
        System.out.println("Task not found (ID: " + id + ")");
    }

    public void listTasks() {
        for (Task task: tasks) {
            System.out.println(task);
        }
    }

    public void listFilteredTasks(String status) {
        for (Task task : tasks) {
            if (status == null || task.getStatus().equals(status)) {
                System.out.println(task);
            }
        }
    }
}
