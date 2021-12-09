package Solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    private static int count = 0;
    private static final String[] filenames = {"src/recources/sudoku0.txt","src/recources/sudoku1.txt",
            "src/recources/sudoku2.txt", "src/recources/sudoku3.txt", "src/recources/sudoku4.txt"};

    public static List<String> getList() {
        try {
            List<String> list = new ArrayList<>();
            Scanner scanner = new Scanner(new File(filenames[count++ % filenames.length]));
            scanner.findWithinHorizon("\uFEFF", 1);
            scanner.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\")+");
            while(scanner.hasNext()) {
                list.add(scanner.next());
            }
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}