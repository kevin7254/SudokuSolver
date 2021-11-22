package Solver;

public class Solver implements SudokuSolver{
    private int[][] field;
    
    /**
    * Creates a Solver
     */
    public Solver(){
        
    }
    /**
     * Check if it is legal to place value at row, col.
     * @return true if value can be placed at row, col, false otherwise
     */
    boolean checkIfLegal(int row, int col, int value);
        
    /**
     * Check if the position at row, col has a start value
     * @return true if the position has no start value, false otherwise
     */
    boolean checkIfEmpty(int row, int col){
        return field[row][col] == 0;
    }

    /**
     * Initializes the board with values in the matrix start.
     */
    void init(int[][] start);

    /**
     * Returns the solution.
     * @return int matrix with a valid solution
     */
    int[][] getBoard();

    /**
     * Method to solve the sudoku.
     * @return true if solution was found, false otherwise
     */
    boolean solve(int row, int col); //solve(0, 0)

    /**
     * Adds value value at position row, col.
     */
    void add(int row, int col, int value){
        field[row][col] = value;
    }

    /**
     * Clears the sudoku.
     */
    void clear(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                field[i][j] = 0;
            }
        }
    }

    /**
     * Removes the value at row, col.
     */
    void remove(int row, int col){
        field[row][col] = 0;
    }
}