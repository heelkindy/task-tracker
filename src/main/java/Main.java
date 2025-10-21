public class Main {
    private static TaskManager taskManager;
    
    public static void main(String[] args) {
        taskManager = new TaskManager();
        
        if (args.length == 0) {
            printUsage();
            return;
        }
        
        String command = args[0];
        
        try {
            switch (command) {
                case "add":
                    handleAddCommand(args);
                    break;
                case "update":
                    handleUpdateCommand(args);
                    break;
                case "delete":
                    handleDeleteCommand(args);
                    break;
                case "mark-in-progress":
                    handleMarkInProgressCommand(args);
                    break;
                case "mark-done":
                    handleMarkDoneCommand(args);
                    break;
                case "list":
                    handleListCommand(args);
                    break;
                default:
                    System.out.println("Unknown command: " + command);
                    printUsage();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    private static void handleAddCommand(String[] args) {
        if (args.length < 2) {
            System.out.println("Error: Description is required for add command");
            printUsage();
            return;
        }
        
        // Join all arguments after "add" to form the description
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            if (i > 1) description.append(" ");
            description.append(args[i]);
        }
        
        taskManager.addTask(description.toString());
    }
    
    private static void handleUpdateCommand(String[] args) {
        if (args.length < 3) {
            System.out.println("Error: ID and description are required for update command");
            printUsage();
            return;
        }
        
        try {
            int id = Integer.parseInt(args[1]);
            
            // Join all arguments after ID to form the description
            StringBuilder description = new StringBuilder();
            for (int i = 2; i < args.length; i++) {
                if (i > 2) description.append(" ");
                description.append(args[i]);
            }
            
            taskManager.updateTask(id, description.toString());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid task ID. Please provide a valid number.");
        }
    }
    
    private static void handleDeleteCommand(String[] args) {
        if (args.length < 2) {
            System.out.println("Error: ID is required for delete command");
            printUsage();
            return;
        }
        
        try {
            int id = Integer.parseInt(args[1]);
            taskManager.deleteTask(id);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid task ID. Please provide a valid number.");
        }
    }
    
    private static void handleMarkInProgressCommand(String[] args) {
        if (args.length < 2) {
            System.out.println("Error: ID is required for mark-in-progress command");
            printUsage();
            return;
        }
        
        try {
            int id = Integer.parseInt(args[1]);
            taskManager.markTaskInProgress(id);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid task ID. Please provide a valid number.");
        }
    }
    
    private static void handleMarkDoneCommand(String[] args) {
        if (args.length < 2) {
            System.out.println("Error: ID is required for mark-done command");
            printUsage();
            return;
        }
        
        try {
            int id = Integer.parseInt(args[1]);
            taskManager.markTaskDone(id);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid task ID. Please provide a valid number.");
        }
    }
    
    private static void handleListCommand(String[] args) {
        if (args.length == 1) {
            // List all tasks
            taskManager.listAllTasks();
        } else if (args.length == 2) {
            String status = args[1];
            switch (status) {
                case "done":
                case "todo":
                case "in-progress":
                    taskManager.listTasksByStatus(status);
                    break;
                default:
                    System.out.println("Error: Invalid status. Use 'done', 'todo', or 'in-progress'");
                    printUsage();
            }
        } else {
            System.out.println("Error: Too many arguments for list command");
            printUsage();
        }
    }
    
    private static void printUsage() {
        System.out.println("Task Tracker CLI - Usage:");
        System.out.println("  java Main add \"<description>\"           - Add a new task");
        System.out.println("  java Main update <id> \"<description>\"   - Update a task");
        System.out.println("  java Main delete <id>                  - Delete a task");
        System.out.println("  java Main mark-in-progress <id>        - Mark task as in-progress");
        System.out.println("  java Main mark-done <id>               - Mark task as done");
        System.out.println("  java Main list                         - List all tasks");
        System.out.println("  java Main list <status>                - List tasks by status (done/todo/in-progress)");
        System.out.println();
        System.out.println("Examples:");
        System.out.println("  java Main add \"Buy groceries\"");
        System.out.println("  java Main update 1 \"Buy groceries and cook dinner\"");
        System.out.println("  java Main delete 1");
        System.out.println("  java Main mark-in-progress 1");
        System.out.println("  java Main mark-done 1");
        System.out.println("  java Main list");
        System.out.println("  java Main list done");
    }
}
