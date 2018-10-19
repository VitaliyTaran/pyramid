import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DataReaderTest {
    @Test
    public void dataSortedReaderTest() {
        DataReader reader = new DataReader();
        List<String> actual = new ArrayList<String>();
        actual.add("1.1");
        actual.add("1.2");
        List<String> list = reader.sortList(reader.readFileToList("src/main/resources/file.txt"));
        Assert.assertEquals(list,actual);
    }

}
