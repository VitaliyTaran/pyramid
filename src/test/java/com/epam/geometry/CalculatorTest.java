package com.epam.geometry;

import com.epam.geometry.entity.Point;
import com.epam.geometry.entity.Pyramid;
import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {
    private static final double DELTA = 0.01;
    private static final double SPACE_OF_TEST_PYRAMID = 16;
    private static final double SUM_SQUARE_TRIANGLES_IN_PYRAMID = 73.22455571737483d;
    private static final Calculator CALCULATOR = new Calculator();
    private static final Pyramid TESTING_PYRAMID = new Pyramid(
            new Point(2, 6, 2),
            new Point(0, 0, 0),
            new Point(4, 0, 0),
            new Point(4, 0, 4)
    );


    @Test
    public void getSpacePyramidTest() {
        //When
        double expected = CALCULATOR.calculateSpacePyramid(TESTING_PYRAMID);
        //Then
        Assert.assertEquals(expected, SPACE_OF_TEST_PYRAMID, DELTA);
    }

    @Test
    public void getSumSquareTrianglesTest() {
        //When
        double sumSquareTriangles = CALCULATOR.calculateSumSquareTriangles(TESTING_PYRAMID);
        //Then
        Assert.assertEquals(sumSquareTriangles, SUM_SQUARE_TRIANGLES_IN_PYRAMID, DELTA);
    }

    @Test
    public void isPyramidTest() {
        //When
        boolean expected = CALCULATOR.isPyramid(TESTING_PYRAMID);
        //Then
        Assert.assertEquals(expected, true);
    }

    @Test
    public void isPyramidBasedOnXOYTest() {
        //Given
        CoordinatePlane xoy = CoordinatePlane.XOY;
        //When
        boolean isPyramidBasedOnCoordinatePlane = CALCULATOR.isPyramidBasedOnCoordinatePlane(TESTING_PYRAMID, xoy);
        //Then
        Assert.assertEquals(isPyramidBasedOnCoordinatePlane, false);
    }

    @Test
    public void isPyramidBasedOnXOZTest() {
        //Given
        CoordinatePlane xoz = CoordinatePlane.XOZ;
        //When
        boolean isPyramidBasedOnCoordinatePlane = CALCULATOR.isPyramidBasedOnCoordinatePlane(TESTING_PYRAMID, xoz);
        //Then
        Assert.assertEquals(isPyramidBasedOnCoordinatePlane, true);
    }

    @Test
    public void isPyramidBasedOnYOZTest() {
        //Given
        CoordinatePlane yoz = CoordinatePlane.YOZ;
        //When
        boolean isPyramidBasedOnCoordinatePlane = CALCULATOR.isPyramidBasedOnCoordinatePlane(TESTING_PYRAMID, yoz);
        //Then
        Assert.assertEquals(isPyramidBasedOnCoordinatePlane, false);
    }

    @Test
    public void planeSectionOfPyramidTest() {

        //When
        double expected = CALCULATOR.planeSectionOfPyramid(TESTING_PYRAMID, 4);
        //Then
        Assert.assertEquals(expected, 6.75, DELTA);
        // observer наблюдает (в нашем случае 1 и он singleton)  observerable наблюдаемый обьект
    }

    @Test
    public void calculatePointOnHeight() {

        Point point = CALCULATOR.calculatePointOnHeight(new Point(0, 0, 0), new Point(2, 6, 0), 6);
        Point expected = new Point(2, 6, 0);
        System.out.println(point.toString());

    }
}