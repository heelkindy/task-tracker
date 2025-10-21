#!/bin/bash

# Task Tracker CLI - Build Script
# This script compiles the Java application

echo "🔨 Building Task Tracker CLI..."

# Create classes directory if it doesn't exist
mkdir -p classes

# Compile Java files
javac -d classes src/main/java/*.java

if [ $? -eq 0 ]; then
    echo "✅ Build successful! Compiled classes are in the 'classes' directory."
    echo ""
    echo "To run the application, use:"
    echo "  java -cp classes Main <command>"
    echo ""
    echo "Example:"
    echo "  java -cp classes Main add \"Your first task\""
else
    echo "❌ Build failed! Please check for compilation errors."
    exit 1
fi
