package Solver;

public class SudokuApplication {
    public static void main(String[] args) {
        Solver solver = new Solver();
        int row = 2;
        int col = 2;
        int value = 8;
        boolean s = solver.checkIfLegal(row, col, value);
        System.out.println(s);
    }
}
