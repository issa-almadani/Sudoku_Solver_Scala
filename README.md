## Sudoku Solver in Scala
______

### Purpose
The purpose of this project is to write a Scala program that is capable of taking in an incomplete Sudoku board and produce a list of possible solutions for the board given that a correct solution exists

### Project Goals
The project goal is to fully create a functional scala program based Richard Bard's research paper "Functional Pearl: A program to solve Sudoku". 
- Fully translate the functional program logic written for Haskell to a scala-based program
- Write and test all functions defined in the research paper
- Test the program's ability to solve solvable Sudoku boards
- Code a TUI and GUI for users to be able to run the program and input custom games to solve
- Code a testing interface that allows games to be loaded in from a file to be solved

### How to Run Project
The program has multiple versions of the Sudoku function based on the original research paper. It will run the `sudoku_v3` function by default. Seeing as the program requires a lot of memory to compute, the stack must be expanded to prevent stack overflow, which is why the following command needs to be written to run the program:

`scala -J-Xss200m sudoku 9 3 123456789 . None` 

Note that the -J-Xss200m flag extends the stack. The command line parameters are the boardsize, boxsize, cell values, blank space character, and path to potential file to load in if Sudoku board is written in it. 
