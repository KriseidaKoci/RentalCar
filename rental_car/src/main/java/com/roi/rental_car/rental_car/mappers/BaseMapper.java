package com.roi.rental_car.rental_car.mappers;

import java.util.List;

public interface BaseMapper<E, D> {
    D toDto(E e);
    E toEntity(D d);
    List<D> toDtoList(List<E> e);
    List<E> toEntitity(List<D> d);
}
