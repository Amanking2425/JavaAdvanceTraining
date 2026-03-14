package in.cg.service;


import in.cg.dto.BookingDTO;
import in.cg.dto.RentalCarDTO;
import in.cg.feign.CarRentalsFeignClient;
import in.cg.model.Passenger;
import in.cg.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private CarRentalsFeignClient carRentalsFeignClient;



    public Passenger addPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Optional<Passenger> getPassengerById(Long id) {
        return passengerRepository.findById(id);
    }

    public Optional<Passenger> getPassengerByEmail(String email) {
        return passengerRepository.findByEmail(email);
    }

    public Passenger updatePassenger(Long id, Passenger updatedPassenger) {
        Optional<Passenger> existingPassenger =
                passengerRepository.findById(id);
        if (existingPassenger.isPresent()) {
            Passenger passenger = existingPassenger.get();
            passenger.setFirstName(updatedPassenger.getFirstName());
            passenger.setLastName(updatedPassenger.getLastName());
            passenger.setEmail(updatedPassenger.getEmail());
            passenger.setPhone(updatedPassenger.getPhone());
            passenger.setAddress(updatedPassenger.getAddress());
            passenger.setDrivingLicenseNumber(
                    updatedPassenger.getDrivingLicenseNumber());
            passenger.setAge(updatedPassenger.getAge());
            return passengerRepository.save(passenger);
        }
        throw new RuntimeException(
                "Passenger not found with id: " + id);
    }

    public String deletePassenger(Long id) {
        if (passengerRepository.existsById(id)) {
            passengerRepository.deleteById(id);
            return "Passenger deleted successfully with id: " + id;
        }
        throw new RuntimeException(
                "Passenger not found with id: " + id);
    }


    public List<RentalCarDTO> getAllRentalCars() {
        return carRentalsFeignClient.getAllRentalCars().getBody();
    }

    public RentalCarDTO getRentalCarById(Long carId) {
        return carRentalsFeignClient.getRentalCarById(carId).getBody();
    }

    public List<RentalCarDTO> getAvailableCars() {
        return carRentalsFeignClient.getAvailableCars().getBody();
    }

    public List<RentalCarDTO> getCarsByType(String carType) {
        return carRentalsFeignClient.getCarsByType(carType).getBody();
    }

    public BookingDTO bookCar(BookingDTO bookingDTO) {
        return carRentalsFeignClient.createBooking(bookingDTO).getBody();
    }

    public List<BookingDTO> getMyBookings(Long passengerId) {
        return carRentalsFeignClient
                .getBookingsByPassengerId(passengerId).getBody();
    }
}