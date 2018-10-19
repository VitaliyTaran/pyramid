import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataReader {
    public List<String> readFileToList(String nameOfFileWithName) {
        List<String> lines = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(nameOfFileWithName)));
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public List<String> sortList(List<String> list) {
        Pattern pattern = Pattern.compile("^\\d+\\.\\d+$");
        List<String> result = new ArrayList<String>();
        for (String line : list) {
            String[] words = line.split(" ");
            for (String word : words) {
                Matcher matcher = pattern.matcher(word);
                if (matcher.matches()) {
                    result.add(word);
                }
            }
        }
        return result;
    }
}
