# Task Tracker CLI

A simple command-line interface (CLI) application built in Java to track and manage your tasks. This project helps you practice programming skills including working with the filesystem, handling user inputs, and building a simple CLI application.

> **Project Inspiration**: This project is based on the Task Tracker project from [roadmap.sh](https://roadmap.sh/projects/task-tracker), designed for learning purposes to practice programming skills and build practical CLI applications.

## Features

-   ✅ Add, update, and delete tasks
-   ✅ Mark tasks as in-progress or done
-   ✅ List all tasks or filter by status
-   ✅ Persistent storage using JSON file
-   ✅ Error handling and input validation
-   ✅ No external dependencies

## Task Properties

Each task contains the following properties:

-   **id**: Unique identifier for the task
-   **description**: Short description of the task
-   **status**: Task status (todo, in-progress, done)
-   **createdAt**: Date and time when the task was created
-   **updatedAt**: Date and time when the task was last updated

## Installation & Setup

### Prerequisites

-   Java 8 or higher installed on your system
-   Git (for cloning the repository)

### Quick Start

1. **Clone the repository:**

    ```bash
    git clone https://github.com/heelkindy/task-tracker.git
    cd task-tracker
    ```

2. **Build the application:**

    ```bash
    # On Unix/Linux/macOS:
    ./build.sh
    ```

3. **Run the application:**

    ```bash
    # On Unix/Linux/macOS:
    ./run.sh add "Your first task"

    # Or manually:
    java -cp classes Main add "Your first task"
    ```

## Usage

### Basic Commands

```bash
# Using the run script (recommended):
./run.sh add "Buy groceries"
./run.sh update 1 "Buy groceries and cook dinner"
./run.sh delete 1
./run.sh mark-in-progress 1
./run.sh mark-done 1
./run.sh list
./run.sh list done
./run.sh list todo
./run.sh list in-progress

# Or manually:
java -cp classes Main add "Buy groceries"
java -cp classes Main update 1 "Buy groceries and cook dinner"
java -cp classes Main delete 1
java -cp classes Main mark-in-progress 1
java -cp classes Main mark-done 1
java -cp classes Main list
java -cp classes Main list done
java -cp classes Main list todo
java -cp classes Main list in-progress
```

### Command Reference

| Command            | Description              | Usage                                                                                               |
| ------------------ | ------------------------ | --------------------------------------------------------------------------------------------------- |
| `add`              | Add a new task           | `./run.sh add "description"` or `java -cp classes Main add "description"`                           |
| `update`           | Update an existing task  | `./run.sh update <id> "new description"` or `java -cp classes Main update <id> "new description"`   |
| `delete`           | Delete a task            | `./run.sh delete <id>` or `java -cp classes Main delete <id>`                                       |
| `mark-in-progress` | Mark task as in-progress | `./run.sh mark-in-progress <id>` or `java -cp classes Main mark-in-progress <id>`                   |
| `mark-done`        | Mark task as done        | `./run.sh mark-done <id>` or `java -cp classes Main mark-done <id>`                                 |
| `list`             | List all tasks           | `./run.sh list` or `java -cp classes Main list`                                                     |
| `list <status>`    | List tasks by status     | `./run.sh list <done\|todo\|in-progress>` or `java -cp classes Main list <done\|todo\|in-progress>` |

### Examples

```bash
# Create some tasks
./run.sh add "Learn Java"
./run.sh add "Build a CLI app"
./run.sh add "Write documentation"

# Check all tasks
./run.sh list

# Start working on a task
./run.sh mark-in-progress 1

# Complete a task
./run.sh mark-done 1

# Update a task description
./run.sh update 2 "Build a task tracker CLI app"

# Check only completed tasks
./run.sh list done
```

## Data Storage

Tasks are automatically saved to a `tasks.json` file in the current directory. The file is created automatically when you add your first task. The JSON format is human-readable and can be edited manually if needed.

Example `tasks.json`:

```json
[
    {
        "id": 1,
        "description": "Learn Java",
        "status": "done",
        "createdAt": "2024-01-15 10:30:00",
        "updatedAt": "2024-01-15 11:45:00"
    },
    {
        "id": 2,
        "description": "Build a task tracker CLI app",
        "status": "in-progress",
        "createdAt": "2024-01-15 10:35:00",
        "updatedAt": "2024-01-15 12:00:00"
    }
]
```

## Error Handling

The application includes comprehensive error handling:

-   Invalid command arguments
-   Non-existent task IDs
-   Invalid status values
-   File system errors
-   JSON parsing errors

## Project Structure

```
task-tracker/
├── src/
│   └── main/
│       └── java/
│           ├── Main.java          # CLI interface and command handling
│           ├── Task.java          # Task model class
│           └── TaskManager.java   # Task management and JSON operations
├── classes/              # Compiled classes (created after build)
├── tasks.json           # Task storage (created automatically)
├── build.sh             # Build script for Unix/Linux/macOS
├── run.sh               # Run script for Unix/Linux/macOS
├── .gitignore           # Git ignore rules
└── README.md            # This file
```

## Development

This project was built using:

-   **Java** (no external dependencies)
-   **Native file system operations**
-   **Simple JSON parsing and generation**
-   **Command-line argument processing**

## Acknowledgments

This project is inspired by the [Task Tracker project on roadmap.sh](https://roadmap.sh/projects/task-tracker), which provides excellent learning opportunities for developers to practice programming skills and build practical applications.

## License

This project is open source and available under the MIT License.
