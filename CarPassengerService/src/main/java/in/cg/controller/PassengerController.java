package in.cg.controller;

import in.cg.dto.RentalCarDTO;
import in.cg.dto.BookingDTO;
import in.cg.model.Passenger;
import in.cg.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passengerdetails")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;


    @PostMapping
    public ResponseEntity<Passenger> addPassenger(
            @RequestBody Passenger passenger) {
        Passenger saved = passengerService.addPassenger(passenger);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        List<Passenger> passengers =
                passengerService.getAllPassengers();
        return new ResponseEntity<>(passengers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengerById(
            @PathVariable Long id) {
        return passengerService.getPassengerById(id)
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Passenger> getPassengerByEmail(
            @PathVariable String email) {
        return passengerService.getPassengerByEmail(email)
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passenger> updatePassenger(
            @PathVariable Long id,
            @RequestBody Passenger passenger) {
        Passenger updated = passengerService
                .updatePassenger(id, passenger);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePassenger(
            @PathVariable Long id) {
        String message = passengerService.deletePassenger(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/rentalcars")
    public ResponseEntity<List<RentalCarDTO>> viewAllRentalCars() {
        List<RentalCarDTO> cars =
                passengerService.getAllRentalCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/rentalcars/{carId}")
    public ResponseEntity<RentalCarDTO> viewRentalCarById(
            @PathVariable Long carId) {
        RentalCarDTO car =
                passengerService.getRentalCarById(carId);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @GetMapping("/rentalcars/available")
    public ResponseEntity<List<RentalCarDTO>> viewAvailableCars() {
        List<RentalCarDTO> cars =
                passengerService.getAvailableCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/rentalcars/type/{carType}")
    public ResponseEntity<List<RentalCarDTO>> viewCarsByType(
            @PathVariable String carType) {
        List<RentalCarDTO> cars =
                passengerService.getCarsByType(carType);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<BookingDTO> bookCar(
            @RequestBody BookingDTO bookingDTO) {
        BookingDTO booking = passengerService.bookCar(bookingDTO);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    @GetMapping("/{passengerId}/bookings")
    public ResponseEntity<List<BookingDTO>> getMyBookings(
            @PathVariable Long passengerId) {
        List<BookingDTO> bookings =
                passengerService.getMyBookings(passengerId);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
}