import entity.Point;
import entity.Pyramid;
import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {
    private static final double DELTA = 0.01;
    private static final double SPACE_OF_TEST_PYRAMID = 16;
    private static final double SUM_SQUARE_TRIANGLES_IN_PYRAMID = 73.22455571737483d;
    private static final Pyramid TESTING_PYRAMID = new Pyramid(
            new Point(2, 6, 2),
            new Point(0, 0, 0),
            new Point(4, 0, 0),
            new Point(4, 0, 4)
    );


    @Test
    public void getSpacePyramidTest() {
        Calculator calculator = new Calculator();
        //When
        double expected = calculator.calculateSpacePyramid(TESTING_PYRAMID);
        //Then
        Assert.assertEquals(expected, SPACE_OF_TEST_PYRAMID, DELTA);
    }

    @Test
    public void getSumSquareTrianglesTest() {
        Calculator calculator = new Calculator();
        //When
        double sumSquareTriangles = calculator.calculateSumSquareTriangles(TESTING_PYRAMID);
        //Then
        Assert.assertEquals(sumSquareTriangles, SUM_SQUARE_TRIANGLES_IN_PYRAMID, DELTA);
    }

    @Test
    public void isPyramidTest() {
        Calculator calculator = new Calculator();
        //When
        boolean expected = calculator.isPyramid(TESTING_PYRAMID);
        //Then
        Assert.assertEquals(expected, true);
    }

    @Test
    public void isPyramidBasedOnXOYTest() {
        Calculator calculator = new Calculator();
        //Given
        CoordinatePlane xoy = CoordinatePlane.XOY;
        //When
        boolean isPyramidBasedOnCoordinatePlane = calculator.isPyramidBasedOnCoordinatePlane(TESTING_PYRAMID, xoy);
        //Then
        Assert.assertEquals(isPyramidBasedOnCoordinatePlane, false);
    }

    @Test
    public void isPyramidBasedOnXOZTest() {
        Calculator calculator = new Calculator();
        //Given
        CoordinatePlane xoz = CoordinatePlane.XOZ;
        //When
        boolean isPyramidBasedOnCoordinatePlane = calculator.isPyramidBasedOnCoordinatePlane(TESTING_PYRAMID, xoz);
        //Then
        Assert.assertEquals(isPyramidBasedOnCoordinatePlane, true);
    }

    @Test
    public void isPyramidBasedOnYOZTest() {
        Calculator calculator = new Calculator();
        //Given
        CoordinatePlane yoz = CoordinatePlane.YOZ;
        //When
        boolean isPyramidBasedOnCoordinatePlane = calculator.isPyramidBasedOnCoordinatePlane(TESTING_PYRAMID, yoz);
        //Then
        Assert.assertEquals(isPyramidBasedOnCoordinatePlane, false);
    }

    @Test
    public void planeSectionOfPyramidTest() {
        Calculator calculator = new Calculator();
        //When
        double expected = calculator.planeSectionOfPyramid(TESTING_PYRAMID, 4);
        //Then
        Assert.assertEquals(expected, 6.75, DELTA);
    }
}