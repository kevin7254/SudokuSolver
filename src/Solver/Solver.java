package Solver;

import java.text.BreakIterator;

public class Solver implements SudokuSolver {
    private int[][] field;

    /**
     * Creates a Solver
     */
    public Solver() { //TODO backtracking
        field = new int[9][9];
    }

    /**
     * Check if it is legal to place value at row, col.
     *
     * @return true if value can be placed at row, col, false otherwise
     */

    public boolean checkIfLegal(int row, int col, int value) {
        //kollar om value inte finns på samma rad
        for (int i = 0; i < 9; i++) {
            if (value == field[row][i] && (i != col)) {
                return false;
            }
        }
        //kollar om value inte finns på samma kolumn
        for (int i = 0; i < 9; i++) {
            if (value == field[i][col] && (i != row)) {
                return false;
            }
        }

        //Kollar om value inte redan finns i dens 3x3
        int sRow = row - row % 3,
                sCol = col - col % 3;

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if ((field[i + sRow][j + sCol] == value) && (row != (i + sRow)) && (col != (j + sCol))) {
                    //System.out.println(field[i + sRow][j + sCol] == value);
                    return false;
                }
        return true;
    }


    /**
     * Check if the position at row, col has a start value
     *
     * @return true if the position has no start value, false otherwise
     */

    public boolean checkIfEmpty(int row, int col) {
        return field[row][col] == 0;
    }

    /**
     * Initializes the board with values in the matrix start.
     */

    public void init(int[][] start) {
        field = start;
    }


    /**
     * Returns the solution.
     *
     * @return int matrix with a valid solution
     */

    public int[][] getBoard() {
        return field;
    }


    /**
     * Method to solve the sudoku.
     *
     * @return true if solution was found, false otherwise
     */

    public boolean solve(int row, int col) {
        if (checkIfEmpty(row, col) == true) {// om platsen är nollställd
            for (int i = 1; i <= 9; i++) {
                if (checkIfLegal(row, col, i)) {// om i går att sätta in i field[row][col]
                 if(col == 8 && row == 8){
                    add(row, col, i);
                    return true;
                } else if(col != 8){
                        add(row, col, i);
                        if (solve(row, col+1) == true) {
                            return true;
                       } }else if (col == 8) {
                            add(row, col, i);
                            if (solve(row +1, 0) == true) {
                                return true;
                        }
                        }
                    }}
                    remove(row, col);
                    return false;
    }if (checkIfEmpty(row, col) == false) {
        if (checkIfLegal(row, col, field[row][col])) {
            if (col == 8 ) {
              if (solve(row, col+1) == true) {
                            return true;
            }
        }else if (col != 8) {
            if (solve(row, col+1) == true) {
                return true;
        }
    }
        }
        return false;
    }
return false;
    }


    /**
     * Adds value value at position row, col.
     */

    public void add(int row, int col, int value) {
        field[row][col] = value;
    }

    /**
     * Clears the sudoku.
     */

    public void clear() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                field[i][j] = 0;
            }
        }
    }

    /**
     * Removes the value at row, col.
     */

    public void remove(int row, int col) {
        field[row][col] = 0;
    }
}