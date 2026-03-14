package in.cg.controller;


import in.cg.model.RentalCar;
import in.cg.service.RentalCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentalcardetails")
public class RentalCarController {

    @Autowired
    private RentalCarService rentalCarService;

    @PostMapping
    public ResponseEntity<RentalCar> addRentalCar(
            @RequestBody RentalCar rentalCar) {
        RentalCar savedCar = rentalCarService.addRentalCar(rentalCar);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RentalCar>> getAllRentalCars() {
        List<RentalCar> cars = rentalCarService.getAllRentalCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalCar> getRentalCarById(
            @PathVariable Long id) {
        return rentalCarService.getRentalCarById(id)
                .map(car -> new ResponseEntity<>(car, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/available")
    public ResponseEntity<List<RentalCar>> getAvailableCars() {
        List<RentalCar> cars = rentalCarService.getAvailableCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/type/{carType}")
    public ResponseEntity<List<RentalCar>> getCarsByType(
            @PathVariable String carType) {
        List<RentalCar> cars = rentalCarService.getCarsByType(carType);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalCar> updateRentalCar(
            @PathVariable Long id,
            @RequestBody RentalCar rentalCar) {
        RentalCar updatedCar = rentalCarService
                .updateRentalCar(id, rentalCar);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRentalCar(
            @PathVariable Long id) {
        String message = rentalCarService.deleteRentalCar(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}