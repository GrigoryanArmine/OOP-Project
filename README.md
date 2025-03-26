# tetris-game 

#### 📌 Overview  
This project is a Java-based Tetris game, designed using Object-Oriented Programming (OOP) principles. The goal is to create a playable Tetris experience while implementing key OOP concepts such as classes and objects, inheritance, polymorphism, encapsulation, file handling, and exception handling.  

#### 🔹 Key Features  
- Classic Tetris Gameplay – Move, rotate, and place tetrominoes.  
- Collision Detection – Ensures blocks land correctly.  
- Row Clearing & Scoring – Full rows disappear, increasing the score.  
- Game Over Condition – Ends when blocks reach the top.  
- File I/O – Saves and loads high scores.
- Custom Tetrominoes Feature – Players can introduce custom tetrominoes by defining them in a TXT file.  

#### 🔹 Object-Oriented Design  
1) TetrisGame – Manages the game loop, user input, and overall logic.  
2) Board – Represents the game grid and handles row clearing.  
3) Tetromino (Parent Class) – Defines common behavior for all tetrominoes.  
4) Tetromino Subclasses (LShape, TShape, Square, etc.) – Inherit from Tetromino and implement specific rotations.  
5) ScoreManager – Handles high score storage using File I/O.  
6) Advanced Feature: Custom Shape Addition

🔹 Custom Tetrominoes Feature
In this advanced Tetris feature, players can introduce custom tetrominoes into the game by defining them in a TXT file. The file format will specify each shape’s structure using a simple notation (e.g., a grid representation or coordinate-based format).
When the game starts, it will parse the file and integrate the new shapes into the gameplay alongside standard tetrominoes. This feature allows for greater customization and replayability, making the game more dynamic and user-driven.
#### 🔹 OOP Concepts Used  
✅ Encapsulation – Keeps data hidden and ensures controlled access.  
✅ Inheritance – Tetromino subclasses extend a base class.  
✅ Polymorphism – Different tetrominoes override the rotation method.  
✅ Interfaces (Optional) – Could define common game behaviors.  
✅ Exception Handling – Used for file operations and input validation.  
