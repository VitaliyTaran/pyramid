package data;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {
    private static final String PATTERN_FOR_CONVERT_STRING_TO_DIGIT = "^\\d+\\.\\d+$";

    public List<Double> checkList(List<String> list) {
        Pattern pattern = Pattern.compile(PATTERN_FOR_CONVERT_STRING_TO_DIGIT);
        List<Double> result = new ArrayList<Double>();
        for (String line : list) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                result.add(Double.valueOf(line));
            }
        }
        return result;
    }
}