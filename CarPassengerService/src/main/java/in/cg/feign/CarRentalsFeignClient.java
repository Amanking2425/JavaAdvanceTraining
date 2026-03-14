package in.cg.feign;

import in.cg.dto.RentalCarDTO;
import in.cg.dto.BookingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "carrentals-service")
public interface CarRentalsFeignClient {

    @GetMapping("/rentalcardetails")
    ResponseEntity<List<RentalCarDTO>> getAllRentalCars();

    @GetMapping("/rentalcardetails/{id}")
    ResponseEntity<RentalCarDTO> getRentalCarById(@PathVariable Long id);

    @GetMapping("/rentalcardetails/available")
    ResponseEntity<List<RentalCarDTO>> getAvailableCars();

    @GetMapping("/rentalcardetails/type/{carType}")
    ResponseEntity<List<RentalCarDTO>> getCarsByType(
            @PathVariable String carType);


    @GetMapping("/bookingdetails")
    ResponseEntity<List<BookingDTO>> getAllBookings();

    @GetMapping("/bookingdetails/{id}")
    ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id);

    @PostMapping("/bookingdetails")
    ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO booking);

    @GetMapping("/bookingdetails/passenger/{passengerId}")
    ResponseEntity<List<BookingDTO>> getBookingsByPassengerId(
            @PathVariable Long passengerId);
}