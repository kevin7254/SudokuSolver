package GUI;

import Solver.FileReader;
import Solver.Solver;

import javax.swing.*;
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

    private JTextField[][] guiBoard() {
        JTextField[][] fields = new JTextField[GRID_SIZE][GRID_SIZE];
        Font font1 = new Font("SansSerif", Font.BOLD, 20);


        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                fields[i][j] = new JTextField();
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
                    if (!temp.get(c).equals("0"))
                        fields[i][j].setText(temp.get(c));
                    c++;
                }
            }
            solver.init(arr);
        });

        solveBtn.addActionListener(event -> {
            solver.solve(0, 0);
            int[][] arr = solver.getBoard();
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    fields[i][j].setText(String.valueOf(arr[i][j]));
                }
            }
        });

        giveUpBtn.addActionListener(event -> {
            solver.clear();
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    fields[i][j].setText("");
                }
            }
        });

        buttonPane.add(genBtn);
        buttonPane.add(solveBtn);
        buttonPane.add(giveUpBtn);

        return buttonPane;
    }
}
