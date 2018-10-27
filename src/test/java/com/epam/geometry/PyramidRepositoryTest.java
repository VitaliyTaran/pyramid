package com.epam.geometry;

import com.epam.geometry.entity.Point;
import com.epam.geometry.entity.Pyramid;
import com.epam.geometry.repository.VolumeGreaterThanSpecification;
import org.junit.Assert;
import org.junit.Test;
import com.epam.geometry.repository.PyramidRepository;
import com.epam.geometry.repository.Repository;

public class PyramidRepositoryTest {
    private static final Pyramid TESTING_PYRAMID = new Pyramid(
            new Point(2, 6, 2),
            new Point(0, 0, 0),
            new Point(4, 0, 0),
            new Point(4, 0, 4)
    );

    private Repository<Pyramid> repository = new PyramidRepository();

    @Test
    public void shouldFindByVolume() {
        repository.add(TESTING_PYRAMID);
        VolumeGreaterThanSpecification specification = new VolumeGreaterThanSpecification(10);
        Assert.assertTrue(specification.specified(TESTING_PYRAMID));
    }
}
