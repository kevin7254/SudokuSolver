package GUI;

import Solver.FileReader;
import Solver.Solver;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.util.List;
import java.util.Objects;


public class SudokuViewer {

    private final String title = "SudokuSolver";
    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private final int GRID_SIZE = 9;
    private final JTextField[][] fields;
    private final Solver solver;

    public SudokuViewer(Solver solver) {
        this.solver = solver;
        this.fields = guiBoard();
        SwingUtilities.invokeLater(this::createWindow);
    }

    /**
     * Creates a format for the available inputs on Sudoku board
     *
     * @param s inserted string for formatting
     * @return MaskFormatter to be used for formatting with JFormattedTextField
     */
    private MaskFormatter format(String s) {
        MaskFormatter f = null;
        try {
            f = new MaskFormatter(s);
        } catch (java.text.ParseException e) {
            System.err.println("bad formatter: " + e.getMessage());
        }
        if (f != null) {
            f.setInvalidCharacters("0");
        }
        return f;
    }


    /**
     * Creates a new JFrame window
     */
    private void createWindow() {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setLocationRelativeTo(null);
        JPanel textPane = new JPanel();

        textPane.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        Container pane = frame.getContentPane();

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                textPane.add(fields[i][j]);
            }
        }

        pane.add(textPane, BorderLayout.CENTER);
        pane.add(buttonLogic(), BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    /**
     * Initialize the JTextFields on the board
     *
     * @return the board
     */
    private JTextField[][] guiBoard() {
        JTextField[][] fields = new JTextField[GRID_SIZE][GRID_SIZE];
        Font font1 = new Font("SansSerif", Font.BOLD, 20);


        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                fields[i][j] = new JFormattedTextField(format("#"));
                fields[i][j].setHorizontalAlignment(JTextField.CENTER);
                fields[i][j].setFont(font1);
            }
        }

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (((i % 6 < 3) && (j % 6 < 3)) || ((i % 6 > 2) && (j % 6 > 2))) {
                    fields[i][j].setBackground(Color.ORANGE);
                }
            }
        }
        return fields;
    }

    /**
     * Initialize JPanel for the buttons.
     *
     * @return JPanel with working JButtons
     */
    private JPanel buttonLogic() {
        JPanel buttonPane = new JPanel();
        JButton genBtn = new JButton("Generate Sudoku");
        JButton solveBtn = new JButton("Solve");
        JButton giveUpBtn = new JButton("Give up");

        genBtn.addActionListener(event -> {
            int c = 0;
            List<String> temp = FileReader.getList();
            int[][] arr = new int[GRID_SIZE][GRID_SIZE];
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    fields[i][j].setText("");
                    arr[i][j] = Integer.parseInt(Objects.requireNonNull(temp).get(c));
                    if (!temp.get(c).equals("0")) {
                        fields[i][j].setText(temp.get(c));
                        fields[i][j].setEditable(false);
                    } else {
                        fields[i][j].setEditable(true);
                    }
                    c++;
                }
            }
            solver.init(arr);
        });

        solveBtn.addActionListener(event -> {
            Solver temp = new Solver();
            boolean finished = true;
            int[][] tempBoard = new int[GRID_SIZE][GRID_SIZE];
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    if (finished) {
                        if ((fields[i][j]).getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Please fill in the whole sodoku");
                            finished = false;
                        } else {
                            try {
                                tempBoard[i][j] = Integer.parseInt(Objects.requireNonNull(fields[i][j]).getText());
                            } catch (NumberFormatException e) {
                                finished = false;
                                JOptionPane.showMessageDialog(null, "Please enter a valid number");
                            }

                        }
                    }
                }
            }
            if (finished) {
                temp.init(tempBoard);
                if (!temp.solve(0, 0)) {
                    JOptionPane.showMessageDialog(null, "Wrong Solution");
                } else JOptionPane.showMessageDialog(null, "Right solution!!!");

            }

        });

        giveUpBtn.addActionListener(event -> {
            solver.solve(0, 0);
            int[][] arr = solver.getBoard();
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    fields[i][j].setText(String.valueOf(arr[i][j]));
                }
            }
        });

        buttonPane.add(genBtn);
        buttonPane.add(solveBtn);
        buttonPane.add(giveUpBtn);

        return buttonPane;
    }
}