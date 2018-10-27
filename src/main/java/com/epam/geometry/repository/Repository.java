package com.epam.geometry.repository;

import java.util.List;

public interface Repository <T> {
   List<T> findBy(Specification specification);
    void add(T object);
}
