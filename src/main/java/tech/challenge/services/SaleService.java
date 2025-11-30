package tech.challenge.services;

import jakarta.enterprise.context.ApplicationScoped;
import tech.challenge.entities.CarEntity;
import tech.challenge.entities.SaleEntity;
import tech.challenge.enums.CarStatus;
import tech.challenge.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@ApplicationScoped
public class SaleService {

    public SaleEntity createSale(SaleEntity sale) {
        CarEntity car = CarEntity.findById(sale.getCarId());
        if (car != null && car.getStatus() == CarStatus.AVAILABLE) {
            car.setStatus(CarStatus.RESERVED);
            SaleEntity.persist(sale);
        }
        return sale;
    }

    public SaleEntity confirmSale(String saleId) {
        UUID saleUUID = UUID.fromString(saleId);
        SaleEntity sale = SaleEntity.findById(saleUUID);
        if (sale != null) {
            sale.setPaymentStatus(PaymentStatus.PAID);
            sale.setSaleDate(LocalDateTime.now());
        }
        CarEntity car = CarEntity.findById(sale.getCarId());
        if (car != null) {
            car.setStatus(CarStatus.SOLD);
        }
        return sale;
    }
}
