package com.roi.rental_car.rental_car.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class City {
    @Id
    private String name;
    @OneToMany(mappedBy = "city")
    private List<Branch> branches;

}
