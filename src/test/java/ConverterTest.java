import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConverterTest {
    private static final String TEST_FILE_WAY_AND_NAME = "./src/test/resources/file.txt";

    @Test
    public void converterTest() {
        DataReader reader = new DataReader();
        ConverterStringToDigital converter = new ConverterStringToDigital();
        List<Double> actual = new ArrayList<Double>();
        actual.add(1.1);
        actual.add(1.2);
        List<String> readFileToList = reader.readFile(TEST_FILE_WAY_AND_NAME);
        List<Double> resultList = converter.convertToDigital(readFileToList);

        Assert.assertEquals(resultList, actual);
    }
}
