package com.epam.geometry.repository;

import com.epam.geometry.entity.Pyramid;

public interface Specification {
    boolean specified(Pyramid object);
}
