package tech.challenge.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequestDTO {

    private String model;

    private String brand;

    private Integer year;

    private String color;

    private Double price;
}

