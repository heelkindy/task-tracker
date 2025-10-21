import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {
    private static final String TASKS_FILE = "tasks.json";
    private List<Task> tasks;
    private int nextId;
    
    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.nextId = 1;
        loadTasks();
    }
    
    private void loadTasks() {
        try {
            if (Files.exists(Paths.get(TASKS_FILE))) {
                String content = new String(Files.readAllBytes(Paths.get(TASKS_FILE)));
                if (!content.trim().isEmpty()) {
                    parseTasksFromJson(content);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }
    }
    
    private void parseTasksFromJson(String jsonContent) {
        try {
            // Simple JSON parsing for our format
            String[] taskBlocks = jsonContent.split("\\{");
            for (String block : taskBlocks) {
                if (block.trim().isEmpty() || !block.contains("\"id\"")) continue;
                
                Task task = new Task();
                String[] lines = block.split("\n");
                
                for (String line : lines) {
                    line = line.trim();
                    if (line.contains("\"id\"")) {
                        task.setId(extractIntValue(line));
                    } else if (line.contains("\"description\"")) {
                        task.setDescription(extractStringValue(line));
                    } else if (line.contains("\"status\"")) {
                        task.setStatus(extractStringValue(line));
                    } else if (line.contains("\"createdAt\"")) {
                        task.setCreatedAt(extractStringValue(line));
                    } else if (line.contains("\"updatedAt\"")) {
                        task.setUpdatedAt(extractStringValue(line));
                    }
                }
                
                if (task.getId() > 0) {
                    tasks.add(task);
                    nextId = Math.max(nextId, task.getId() + 1);
                }
            }
        } catch (Exception e) {
            System.err.println("Error parsing tasks: " + e.getMessage());
        }
    }
    
    private int extractIntValue(String line) {
        try {
            String value = line.substring(line.indexOf(":") + 1).trim();
            value = value.replaceAll("[,\\s\"]", "");
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }
    
    private String extractStringValue(String line) {
        try {
            // Find the value after the colon
            int colonIndex = line.indexOf(":");
            if (colonIndex == -1) return "";
            
            String value = line.substring(colonIndex + 1).trim();
            
            // Remove quotes and trailing comma
            if (value.startsWith("\"") && value.endsWith("\"")) {
                return value.substring(1, value.length() - 1);
            } else if (value.startsWith("\"") && value.endsWith("\",")) {
                return value.substring(1, value.length() - 2);
            }
            
            return value;
        } catch (Exception e) {
            return "";
        }
    }
    
    private void saveTasks() {
        try {
            StringBuilder json = new StringBuilder();
            json.append("[\n");
            
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                json.append("  {\n");
                json.append("    \"id\": ").append(task.getId()).append(",\n");
                json.append("    \"description\": \"").append(escapeJson(task.getDescription())).append("\",\n");
                json.append("    \"status\": \"").append(task.getStatus()).append("\",\n");
                json.append("    \"createdAt\": \"").append(task.getCreatedAt()).append("\",\n");
                json.append("    \"updatedAt\": \"").append(task.getUpdatedAt()).append("\"\n");
                json.append("  }");
                if (i < tasks.size() - 1) {
                    json.append(",");
                }
                json.append("\n");
            }
            
            json.append("]");
            
            Files.write(Paths.get(TASKS_FILE), json.toString().getBytes());
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }
    
    private String escapeJson(String str) {
        return str.replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
    }
    
    public void addTask(String description) {
        Task task = new Task(nextId++, description, "todo");
        tasks.add(task);
        saveTasks();
        System.out.println("Task added successfully (ID: " + task.getId() + ")");
    }
    
    public void updateTask(int id, String description) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setDescription(description);
            task.updateTimestamp();
            saveTasks();
            System.out.println("Task updated successfully (ID: " + id + ")");
        } else {
            System.out.println("Task not found (ID: " + id + ")");
        }
    }
    
    public void deleteTask(int id) {
        Task task = findTaskById(id);
        if (task != null) {
            tasks.remove(task);
            saveTasks();
            System.out.println("Task deleted successfully (ID: " + id + ")");
        } else {
            System.out.println("Task not found (ID: " + id + ")");
        }
    }
    
    public void markTaskInProgress(int id) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setStatus("in-progress");
            task.updateTimestamp();
            saveTasks();
            System.out.println("Task marked as in-progress (ID: " + id + ")");
        } else {
            System.out.println("Task not found (ID: " + id + ")");
        }
    }
    
    public void markTaskDone(int id) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setStatus("done");
            task.updateTimestamp();
            saveTasks();
            System.out.println("Task marked as done (ID: " + id + ")");
        } else {
            System.out.println("Task not found (ID: " + id + ")");
        }
    }
    
    public void listAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        
        System.out.println("All Tasks:");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
    
    public void listTasksByStatus(String status) {
        List<Task> filteredTasks = tasks.stream()
            .filter(task -> task.getStatus().equals(status))
            .collect(Collectors.toList());
            
        if (filteredTasks.isEmpty()) {
            System.out.println("No " + status + " tasks found.");
            return;
        }
        
        System.out.println(status.toUpperCase() + " Tasks:");
        for (Task task : filteredTasks) {
            System.out.println(task);
        }
    }
    
    private Task findTaskById(int id) {
        return tasks.stream()
            .filter(task -> task.getId() == id)
            .findFirst()
            .orElse(null);
    }
}
