#!/bin/bash

# Task Tracker CLI - Run Script
# This script runs the Task Tracker CLI with the provided arguments

# Check if classes directory exists
if [ ! -d "classes" ]; then
    echo "❌ Classes directory not found. Please run './build.sh' first."
    exit 1
fi

# Check if Main.class exists
if [ ! -f "classes/Main.class" ]; then
    echo "❌ Main.class not found. Please run './build.sh' first."
    exit 1
fi

# Run the application with all provided arguments
java -cp classes Main "$@"
