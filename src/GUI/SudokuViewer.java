package GUI;

import javax.swing.*;
import java.awt.*;

public class SudokuViewer {

    private final JTextField [][] fields = new JTextField[9][9];
    private final String title = "SudokuSolver";
    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private final Font font1 = new Font("SansSerif", Font.BOLD, 20);

    public SudokuViewer () {
        for (int i = 0; i < 9;i++) {
            for (int j = 0; j<9;j++) {
                fields[i][j] = new JTextField();
                fields[i][j].setHorizontalAlignment(JTextField.CENTER);
                fields[i][j].setFont(font1);
            }
        }
        SwingUtilities.invokeLater(this::createWindow);
    }

    private void createWindow() {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setLocationRelativeTo(null);
        JPanel textPane = new JPanel();
        JPanel buttonPane = new JPanel();
        textPane.setLayout(new GridLayout(9,9));

        Container pane = frame.getContentPane();
        setJTextFieldColor();

        for (int i = 0; i < 9;i++) {
            for (int j = 0; j<9;j++) {
                textPane.add(fields[i][j]);
            }
        }

        buttonPane.add(new JButton("Solve"));
        buttonPane.add(new JButton("Clear"));

        pane.add(textPane, BorderLayout.CENTER);
        pane.add(buttonPane, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void setJTextFieldColor() {
        for (int i = 0; i<9;i++) {
            for(int j = 0; j<9;j++) {
                if ( ((i % 6 < 3) && (j % 6 < 3)) || ( (i % 6 > 2) && (j % 6 > 2))) {
                    fields[i][j].setBackground(Color.ORANGE);
                }
            }
        }
    }
}
