package com.roi.rental_car.rental_car.dto;

import com.roi.rental_car.rental_car.static_data.Status;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
public class UnavailableStatusDTO {
    private Long statusId;
    private Status status;
    private LocalDate date;
}
