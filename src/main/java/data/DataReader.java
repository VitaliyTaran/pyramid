package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataReader {
    private static final String WORD_DELIMITER = " ";

    public List<String> readFile(String nameOfFileWithWay) {
        List<String> result = new ArrayList<String>();
        try {
            FileReader fileReader = new FileReader(nameOfFileWithWay);
            BufferedReader reader = new BufferedReader(fileReader);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNext()) {
                String[] words = scanner.nextLine().split(WORD_DELIMITER);
                result.addAll(Arrays.asList(words));
            }
            scanner.close();
            reader.close();
            fileReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}