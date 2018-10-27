package com.epam.geometry.repository;

import com.epam.geometry.Calculator;
import com.epam.geometry.entity.Pyramid;
import com.epam.geometry.repository.Specification;

public class VolumeGreaterThanSpecification implements Specification {
    private double volume;
    private Calculator calculator = new Calculator();

    public VolumeGreaterThanSpecification(double volume) {
        this.volume = volume;
    }

    @Override
    public boolean specified(Pyramid object) {
        double currentVolume = calculator.calculateSpacePyramid(object);
        return Double.compare(currentVolume, volume) > 0;
    }
}
