package in.cg.service;

import in.cg.model.RentalCar;
import in.cg.repositories.RentalCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalCarService {

    @Autowired
    private RentalCarRepository rentalCarRepository;

    public RentalCar addRentalCar(RentalCar rentalCar) {
        return rentalCarRepository.save(rentalCar);
    }

    public List<RentalCar> getAllRentalCars() {
        return rentalCarRepository.findAll();
    }

    public Optional<RentalCar> getRentalCarById(Long id) {
        return rentalCarRepository.findById(id);
    }

    public List<RentalCar> getAvailableCars() {
        return rentalCarRepository.findByAvailability(true);
    }

    public List<RentalCar> getCarsByType(String carType) {
        return rentalCarRepository.findByCarType(carType);
    }

    public RentalCar updateRentalCar(Long id, RentalCar updatedCar) {
        Optional<RentalCar> existingCar = rentalCarRepository.findById(id);
        if (existingCar.isPresent()) {
            RentalCar car = existingCar.get();
            car.setCarName(updatedCar.getCarName());
            car.setCarModel(updatedCar.getCarModel());
            car.setCarType(updatedCar.getCarType());
            car.setPricePerDay(updatedCar.getPricePerDay());
            car.setAvailability(updatedCar.getAvailability());
            car.setRegistrationNumber(updatedCar.getRegistrationNumber());
            car.setFuelType(updatedCar.getFuelType());
            car.setSeatingCapacity(updatedCar.getSeatingCapacity());
            return rentalCarRepository.save(car);
        }
        throw new RuntimeException("RentalCar not found with id: " + id);
    }

    public String deleteRentalCar(Long id) {
        if (rentalCarRepository.existsById(id)) {
            rentalCarRepository.deleteById(id);
            return "RentalCar deleted successfully with id: " + id;
        }
        throw new RuntimeException("RentalCar not found with id: " + id);
    }
}