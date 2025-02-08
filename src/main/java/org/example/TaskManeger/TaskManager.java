package org.example.TaskManeger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.TaskModel.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskManager {
    private List<Task> tasks;
    private static final String TASKS_FILE = "tasks.json";
    private Gson gson;

    public TaskManager() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        tasks = loadTasks();
    }

    private List<Task> loadTasks() {
        try {
            Path path = Paths.get(TASKS_FILE);
            if (Files.exists(path)) {
                String json = new String(Files.readAllBytes(path));
                return gson.fromJson(json, new TypeToken<List<Task>>(){}.getType());
            }
        } catch (IOException e) {
            Logger.getLogger(TaskManager.class.getName()).log(Level.SEVERE, "Error loading tasks", e);
        }
        return new ArrayList<>();
    }

    private void saveTasks() {
        try {
            String json = gson.toJson(tasks);
            Files.write(Paths.get(TASKS_FILE), json.getBytes());
        } catch (IOException e) {
            Logger.getLogger(TaskManager.class.getName()).log(Level.SEVERE, "Error saving tasks", e);
        }
    }

    public void addTask(String description) {
        int id = tasks.size()+1;
        Task task = new Task(id, description, "todo");
        tasks.add(task);
        saveTasks();
        System.out.println("Task added (ID: " + id + ")");
    }

    public void updateTask (int id, String description) {
        for (Task task: tasks) {
            if(id == task.getId()) {
                task.setDescription(description);
                task.setUpdatedAt(LocalDateTime.now());
                saveTasks();
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
                saveTasks();
                System.out.println("Task marked as " + status + " (ID: " + id + ")");
                return;
            }
        }
        System.out.println("Task not found (ID: " + id + ")");
    }

    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
        saveTasks();
        System.out.println("Task deleted successfully (ID: " + id + ")");
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
