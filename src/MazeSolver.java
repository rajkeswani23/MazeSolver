// Raj Keswani
// April 5, 2024

/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // Should be from start to end cells
        // Returns the correct path in order
        Stack <MazeCell> pathStack = new Stack<>();
        MazeCell current = maze.getEndCell();

        // Goes until the current cell is the start cell
        while (current != maze.getStartCell())
        {
            pathStack.push(current);
            current = current.getParent();
        }
        ArrayList <MazeCell> solution = new ArrayList<>();
        solution.add(maze.getStartCell());
        // Adds everything from stack into array list flipping the order
        while (!pathStack.isEmpty())
        {
            solution.add(pathStack.pop());
        }

        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        Stack<MazeCell> cellsVisit = new Stack<>();
        MazeCell current = maze.getStartCell();

        // Explores cell in maze using DFS
        while(current != maze.getEndCell())
        {
            int col = current.getCol();
            int row = current.getRow();

            // North
            if (maze.isValidCell(row - 1, col))
            {
                cellsVisit.push(maze.getCell(row - 1, col));
                maze.getCell(row - 1, col).setExplored(true);
                maze.getCell(row - 1, col).setParent(current);
            }

            // East
            if (maze.isValidCell(row, col + 1))
            {
                cellsVisit.push(maze.getCell(row, col + 1));
                maze.getCell(row, col + 1).setExplored(true);
                maze.getCell(row, col + 1).setParent(current);
            }

            // South
            if (maze.isValidCell(row + 1, col))
            {
                cellsVisit.push(maze.getCell(row + 1, col));
                maze.getCell(row + 1, col).setExplored(true);
                maze.getCell(row + 1, col).setParent(current);
            }

            // West
            if (maze.isValidCell(row, col - 1))
            {
                cellsVisit.push(maze.getCell(row, col - 1));
                maze.getCell(row, col - 1).setExplored(true);
                maze.getCell(row, col - 1).setParent(current);
            }

            current = cellsVisit.pop();
        }

        // Returns the solution found in DFS
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        Queue<MazeCell> cellsVisit = new LinkedList<>();
        MazeCell current = maze.getStartCell();
        current.setExplored(true);

        // Explores cells in maze using BFS
        while(current != maze.getEndCell())
        {
            int col = current.getCol();
            int row = current.getRow();

            // North
            if (maze.isValidCell(row - 1, col))
            {
                cellsVisit.add(maze.getCell(row - 1, col));
                maze.getCell(row - 1, col).setExplored(true);
                maze.getCell(row - 1, col).setParent(current);
            }

            // East
            if (maze.isValidCell(row, col + 1))
            {
                cellsVisit.add(maze.getCell(row, col + 1));
                maze.getCell(row, col + 1).setExplored(true);
                maze.getCell(row, col + 1).setParent(current);
            }

            // South
            if (maze.isValidCell(row + 1, col))
            {
                cellsVisit.add(maze.getCell(row + 1, col));
                maze.getCell(row + 1, col).setExplored(true);
                maze.getCell(row + 1, col).setParent(current);
            }

            // West
            if (maze.isValidCell(row, col - 1))
            {
                cellsVisit.add(maze.getCell(row, col - 1));
                maze.getCell(row, col - 1).setExplored(true);
                maze.getCell(row, col - 1).setParent(current);
            }

            current = cellsVisit.remove();
        }

        // Returns fastest solution for BFS
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
