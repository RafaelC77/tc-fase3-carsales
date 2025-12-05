package tech.challenge.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSaleRequestDTO {

    private String buyerCpf;

    private String buyerName;

    private String buyerEmail;

    private String buyerPhone;

    private UUID carId;
}

