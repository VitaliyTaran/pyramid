import data.DataReader;
import data.DataValidator;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DataValidatorTest {
    private static final String TEST_FILE_WAY_AND_NAME = "./src/test/resources/file.txt";

    @Test
    public void converterTest() {
        //given
        DataReader reader = new DataReader();
        DataValidator converter = new DataValidator();
        List<Double> actual = new ArrayList<Double>();
        actual.add(1.1);
        actual.add(1.2);
        //when
        List<String> readFileToList = reader.readFile(TEST_FILE_WAY_AND_NAME);
        List<Double> resultList = converter.checkList(readFileToList);
        //then
        Assert.assertEquals(resultList, actual);
    }
}
