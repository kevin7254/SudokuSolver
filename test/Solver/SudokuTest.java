package Solver;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Solver.Solver;

public class SudokuTest {
    private Solver solve;

    @BeforeEach
    public void setUp() {
        solve = new Solver();
    }

    @AfterEach
    public void tearDown() {
        solve = null;
    }

    /**
     * Tests if correctly returns true when possible to put value in box and false when not
     */
    @Test
    public void checkIfLegalTest() {
        assertTrue(solve.checkIfLegal(0, 0, 1), "Wrong result from checkIfLegal");
        solve.add(0, 0, 1);
        assertFalse(solve.checkIfLegal(1, 1, 1), "Wrong result from checkIfLegal");
    }

    /**
     * Tests if correctly returns true when box is empty and false when not
     */
    @Test
    public void checkIfEmptyTest() {
        assertTrue(solve.checkIfEmpty(0, 0), "Wrong result from checkIfEmpty");
        solve.add(0, 0, 1);
        assertFalse(solve.checkIfEmpty(0, 0), "Wrong result from checkIfEmpty");
    }

    /**
     * Tests if correctly clears the board
     */
    @Test
    public void clearTest() {
        solve.add(0, 0, 1);
        solve.add(7, 7, 7);
        solve.clear();
        assertTrue(solve.checkIfEmpty(0, 0), "Wrong result from clear");
        assertTrue(solve.checkIfEmpty(7, 7), "Wrong result from clear");
    }

    /**
     * Tests if correctly removing contents of selected box
     */
    @Test
    public void removeTest() {
        solve.add(0, 0, 1);
        solve.remove(0, 0);
        assertTrue(solve.checkIfEmpty(0, 0), "Did not remove the contents of selected box");
    }

    /**
     * Tests if empty board is solvable
     */
    @Test
    public void solveTestEmptyBoard() {
        assertTrue(solve.solve(0, 0), "Wrong result from solve");
    }

    /**
     * Tests if returns false when board does not have solution
     */
    @Test
    public void solveTestFaultyBoard() {
        solve.add(0, 0, 5);
        solve.add(0, 5, 5);
        assertFalse(solve.solve(0, 0));
        solve.clear();

        solve.add(0, 0, 5);
        solve.add(0, 7, 5);
        assertFalse(solve.solve(0, 0));
        solve.clear();

        solve.add(0, 0, 5);
        solve.add(2, 2, 5);
        assertFalse(solve.solve(0, 0));
    }

    /**
     * Testcase for faulty board then removing faulty digit and trying again with correct board
     */
    @Test
    public void testCase3() {
        solve.add(0, 0, 1);
        solve.add(0, 1, 2);
        solve.add(0, 2, 3);
        solve.add(1, 0, 4);
        solve.add(1, 1, 5);
        solve.add(1, 2, 6);
        solve.add(2, 3, 7);
        assertFalse(solve.solve(0, 0), "Wrong result from solve");
        solve.remove(2, 3);
        assertTrue(solve.solve(0, 0), "Wrong result from solve");
    }

    /**
     * Testcase for faulty board which is then cleared to see if solve functions on board which was cleared
     */
    @Test
    public void testCase4() {
        solve.add(0, 0, 5);
        solve.add(0, 7, 5);
        assertFalse(solve.solve(0, 0), "Wrong result from solve");
        solve.clear();
        assertTrue(solve.solve(0, 0), "Wrong result from solve");
    }

    /**
     * Test of example sudoku
     */
    @Test
    public void testSudoku() {
        solve.add(0, 2, 8);
        solve.add(0, 5, 9);
        solve.add(0, 7, 6);
        solve.add(0, 8, 2);
        solve.add(1, 8, 5);
        solve.add(2, 0, 1);
        solve.add(2, 2, 2);
        solve.add(2, 3, 5);
        solve.add(3, 3, 2);
        solve.add(3, 4, 1);
        solve.add(3, 7, 9);
        solve.add(4, 1, 5);
        solve.add(4, 6, 6);
        solve.add(5, 0, 6);
        solve.add(5, 7, 2);
        solve.add(5, 8, 8);
        solve.add(6, 0, 4);
        solve.add(6, 1, 1);
        solve.add(6, 3, 6);
        solve.add(6, 5, 8);
        solve.add(7, 0, 8);
        solve.add(7, 1, 6);
        solve.add(7, 4, 3);
        solve.add(7, 6, 1);
        solve.add(8, 6, 4);
        assertTrue(solve.solve(0, 0), "Wrong result from solve");
    }

    /**
     * Tests if solve method correctly ignores bad input and solves the empty board
     */
    @Test
    public void badInputTest() {
        solve.add(0, 0, -1);
        assertTrue(solve.solve(0, 0), "Wrong result from solve");
        solve.clear();
        solve.add(0, 0, 10);
        assertTrue(solve.solve(0, 0), "Wrong resutl from solve");
    }

}
