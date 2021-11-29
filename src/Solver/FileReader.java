package Solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public static List<String> getList() {
        try {
            List<String> list = new ArrayList<>();
            Scanner scanner = new Scanner(new File("src/sudoku1.txt"));
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