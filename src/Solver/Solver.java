package Solver;

public class Solver implements SudokuSolver {
    private int[][] field;

    /**
     * Creates a Solver
     */
    public Solver() {
        field = new int[9][9];
    }

    /**
     * Check if it is legal to place value at row, col.
     *
     * @param row   row
     * @param col   column
     * @param value value
     * @return true if value can be placed at row, col, false otherwise
     */

    public boolean checkIfLegal(int row, int col, int value) {
        for (int i = 0; i < 9; i++) {
            if (value == field[row][i] && (i != col)) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (value == field[i][col] && (i != row)) {
                return false;
            }
        }
        int sRow = row - row % 3,
                sCol = col - col % 3;

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if ((field[i + sRow][j + sCol] == value) && (row != (i + sRow)) && (col != (j + sCol))) {
                    return false;
                }
        return true;
    }


    /**
     * Check if the position at row, col has a start value
     *
     * @param row row
     * @param col column
     * @return true if the position has no start value, false otherwise
     */

    public boolean checkIfEmpty(int row, int col) {
        return field[row][col] == 0;
    }

    /**
     * Initializes the board with values in the matrix start.
     *
     * @param start the board to initialize
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
        return field.clone();
    }


    /**
     * Method to solve the sudoku.
     *
     * @param row row
     * @param col column
     * @return true if solution was found, false otherwise
     */

    public boolean solve(int row, int col) {
        if (checkIfEmpty(row, col)) {// om platsen är nollställd
            for (int i = 1; i <= 9; i++) {
                if (checkIfLegal(row, col, i)) {// om i går att sätta in i field[row][col]
                    if (col == 8 && row == 8) {
                        add(row, col, i);
                        return true;
                    } else if (col != 8) {
                        add(row, col, i);
                        if (solve(row, col + 1)) return true;
                    } else {
                        add(row, col, i);
                        if (solve(row + 1, 0)) return true;
                    }
                }
            }
            remove(row, col);
            return false;

        }
        if (!(checkIfEmpty(row, col))) {
            if (checkIfLegal(row, col, field[row][col])) {
                if (col == 8 && row == 8) return true;
                if (col == 8) if (solve(row + 1, 0)) return true;
                if (col != 8) return solve(row, col + 1);
            }
            return false;
        }
        return false;
    }

    /**
     * Adds value value at position row, col.
     *
     * @param row   row
     * @param col   column
     * @param value value
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
     *
     * @param row row
     * @param col column
     */

    public void remove(int row, int col) {
        field[row][col] = 0;
    }
}