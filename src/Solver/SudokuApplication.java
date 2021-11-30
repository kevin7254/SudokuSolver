package Solver;

import GUI.SudokuViewer;

public class SudokuApplication {
    public static void main(String[] args) {

        Solver solver = new Solver();

        new SudokuViewer(solver);
    }
}
