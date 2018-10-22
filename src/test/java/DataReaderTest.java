import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DataReaderTest {
    private static final String TEST_FILE_WAY_AND_NAME = "./src/test/resources/file.txt";

    @Test
    public void dataSortedReaderTest() {
        DataReader reader = new DataReader();
        List<String> actual = new ArrayList<String>();
        actual.add("ASDF");
        actual.add("1.1");
        actual.add("ASD");
        actual.add("1.s3");
        actual.add("2.Z1");
        actual.add("1.2");
        List<String> readFile = reader.readFile(TEST_FILE_WAY_AND_NAME);
        Assert.assertEquals(readFile, actual);
    }

}
