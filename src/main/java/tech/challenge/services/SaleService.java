package tech.challenge.services;

import jakarta.enterprise.context.ApplicationScoped;
import tech.challenge.dtos.CreateSaleRequestDTO;
import tech.challenge.entities.CarEntity;
import tech.challenge.entities.SaleEntity;
import tech.challenge.enums.CarStatus;
import tech.challenge.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@ApplicationScoped
public class SaleService {

    public SaleEntity createSale(CreateSaleRequestDTO dto) {
        CarEntity car = CarEntity.findById(dto.getCarId());
        if (car != null && car.getStatus() == CarStatus.AVAILABLE) {
            SaleEntity sale = new SaleEntity();
            sale.setBuyerCpf(dto.getBuyerCpf());
            sale.setBuyerName(dto.getBuyerName());
            sale.setBuyerEmail(dto.getBuyerEmail());
            sale.setBuyerPhone(dto.getBuyerPhone());
            sale.setCarId(dto.getCarId());
            sale.setPaymentStatus(PaymentStatus.PENDING);

            car.setStatus(CarStatus.RESERVED);
            SaleEntity.persist(sale);
            return sale;
        }
        return null;
    }

    public SaleEntity confirmSale(String saleId) {
        UUID saleUUID = UUID.fromString(saleId);
        SaleEntity sale = SaleEntity.findById(saleUUID);
        if (sale != null) {
            sale.setPaymentStatus(PaymentStatus.PAID);
            sale.setSaleDate(LocalDateTime.now());

            CarEntity car = CarEntity.findById(sale.getCarId());
            if (car != null) {
                car.setStatus(CarStatus.SOLD);
            }
        }
        return sale;
    }
}
