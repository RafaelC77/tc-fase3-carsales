package tech.challenge.services;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import tech.challenge.entities.CarEntity;
import tech.challenge.enums.CarStatus;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CarService {
    public CarEntity createCar(CarEntity carEntity) {

        CarEntity.persist(carEntity);

        return carEntity;
    }

    public List<CarEntity> listAvailableCars() {
        return CarEntity.list("status", Sort.by("price"), CarStatus.AVAILABLE);
    }

    public List<CarEntity> listSoldCars() {
        return CarEntity.list("status", Sort.by("price"), CarStatus.SOLD);
    }
    public List<CarEntity> findAll() {
        return CarEntity.findAll().list();
    }

    public CarEntity findById(UUID id) {
        return CarEntity.findById(id);
    }

    public CarEntity updateCar(UUID id, CarEntity updateCar) {
        CarEntity existingCar = CarEntity.findById(id);
        if (existingCar != null && existingCar.getStatus() == CarStatus.AVAILABLE) {
            existingCar.setBrand(updateCar.getBrand());
            existingCar.setModel(updateCar.getModel());
            existingCar.setYear(updateCar.getYear());
            existingCar.setColor(updateCar.getColor());
            existingCar.setPrice(updateCar.getPrice());

            return existingCar;
        }
        return null;
    }
}
