# Sudoku-Solver

## Algorithms and Analysis Assignment 2

#### Introduction and Background

Sudoku was a game first popularised in Japan in the 80s but dates back to the 18th century and the “Latin Square” game. The aim of Sudoku is to place numbers from 1 to 9 in cells of a 9 by 9 grid, such that in each row, column and 3 by 3 block/box all 9 digits are present. Typical Sudoku puzzle will have some of the cells initially filled in with digits and a well designed game will have one unique solution. In this assignment you will implement algorithms that can solve puzzles of Sudoku and its variants.

#### Sudoku

Sudoku puzzles are typically played on a 9 by 9 grid, where each cell can be filled in with discrete values from 1-9. Sudoku puzzles have some of these cells pre-filled with values, and the aim is to fill in all the remaining cells with values that would form a valid solution. A valid solution (for a 9 by 9 grid with values 1-9) needs to satisfy a number of constraints:

1. Every cell is assigned a value between 1 to 9.

2. Every row contains 9 unique values from 1 to 9.

3. Every column contains 9 unique values from 1 to 9.

4. Every 3 by 3 block (called a box) contains 9 unique values from 1 to 9.

#### Killer sudoku

Killer Sudoku puzzles are typically played on 9 by 9 grids also and have many elements of Sudoku puzzles, including all of its constraints. It additionally has cages, which are subset of cells that have a total assigned to them. A valid Killer Sudoku must also satisfy the constraint that the values assigned to a cage are unique and add up to the total.
Formally, a valid solution for a Killer Sudoku of 9 by 9 grid and 1-9 as values needs to satisfy all of the following constraints (the first 4 are the same as standard Sudoku):

1. Every cell is assigned a value between 1 to 9.

2. Every row contains 9 unique values from 1 to 9.

3. Every column contains 9 unique values from 1 to 9.

4. Every 3 by 3 block/box contains 9 unique values from 1 to 9.

5. The sum of values in the cells of each cage must be equal to the cage target total and all the values in a cage must be unique.


#### Tasks

The assignment is broken up into a number of tasks. Apart from Task A that should be completed initially, all other tasks can be completed in an order you are more comfortable with, but we have ordered them according to what we perceive to be their difficulty. Task E is considered a high distinction task and hence we suggest to tackle this after you have completed the other tasks.

*Task A: Implement Sudoku Grid (4 marks)*

Implement the grid representation, including reading in from file and outputting a solved grid to an output file. Note we will use the output file to evaluate the correctness of your implementations and algorithms.

A typically Sudoku puzzle is played on a 9 by 9 grid, but there are 4 by 4, 16 by 16, 25 by 25 and larger. In this task and subsequent tasks, your implementation should be able to represent and solve Sudoku and variants of any valid sizes, e.g., 4 by 4 and above. You won’t get a grid size that isn’t a perfect square, e.g., 7 by 7 is not a valid grid size, and all puzzles will be square in shape.

In addition, the values/symbols of the puzzles may not be sequential digits, e.g., 1-9 for a 9 by 9 grid, but could be any set of 9 unique non-negative integer digits. The same Sudoku rules and constraints still hold for non-standard set of values/symbols. Your implementation should be able to read this in and handle any set of valid integer values/symbols.

*Task B: Implement Backtracking Solver for Sudoku (9 marks)*

To help to understand the problem and the challenges involved, the first task is to develop a backtracking approach to solve Sudoku puzzles.

*Task C: Exact Cover Solver - Algorithm X (7 marks)*

In this task, you will implement the first approaches to solve Sudoku as an exact cover problem - Algorithm X.

*Task D: Exact Cover Solver - Dancing Links (7 marks)*

In this task, you will implement the second of two approaches to solve Sudoku as an exact cover problem - the Dancing Links algorithm. We suggest to attempt to understand and implement Algorithm X first, then the Dancing Links approach.

*Task E: Killer Sudoku Solver (16 marks)*

In this task, you will take what you have learnt from the first two tasks and devise and implement 2 solvers for Killer Sudoku puzzles. One will be based on backtracking and the other should be more efficient (in running time) than the backtracking one. Your implementation will be assessed for its ability to solve Killer Sudoku puzzles of various difficulties within reasonable time, as well as your proposed approach, which will be detailed in a short (1-2 pages) report. We are as interested in your approach and rationale behind it as much as the correctness and efficiency of your approach.
