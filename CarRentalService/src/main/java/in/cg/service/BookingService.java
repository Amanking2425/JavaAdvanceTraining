package in.cg.service;

import in.cg.model.Booking;
import in.cg.model.RentalCar;
import in.cg.repositories.BookingRepository;
import in.cg.repositories.RentalCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RentalCarRepository rentalCarRepository;

    // CREATE BOOKING
    public Booking createBooking(Booking booking) {
        // Calculate total price based on days and car price
        Optional<RentalCar> car = rentalCarRepository.findById(booking.getCarId());
        if (car.isPresent()) {
            long days = ChronoUnit.DAYS.between(
                booking.getBookingDate(), booking.getReturnDate()
            );
            booking.setTotalPrice(days * car.get().getPricePerDay());
            booking.setStatus("CONFIRMED");

            // Mark car as unavailable
            RentalCar rentalCar = car.get();
            rentalCar.setAvailability(false);
            rentalCarRepository.save(rentalCar);
        }
        return bookingRepository.save(booking);
    }

    // READ ALL BOOKINGS
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // READ BY ID
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    // READ BY PASSENGER ID
    public List<Booking> getBookingsByPassengerId(Long passengerId) {
        return bookingRepository.findByPassengerId(passengerId);
    }

    // UPDATE BOOKING
    public Booking updateBooking(Long id, Booking updatedBooking) {
        Optional<Booking> existingBooking = bookingRepository.findById(id);
        if (existingBooking.isPresent()) {
            Booking booking = existingBooking.get();
            booking.setPassengerId(updatedBooking.getPassengerId());
            booking.setCarId(updatedBooking.getCarId());
            booking.setBookingDate(updatedBooking.getBookingDate());
            booking.setReturnDate(updatedBooking.getReturnDate());
            booking.setTotalPrice(updatedBooking.getTotalPrice());
            booking.setStatus(updatedBooking.getStatus());
            booking.setPickupLocation(updatedBooking.getPickupLocation());
            booking.setDropLocation(updatedBooking.getDropLocation());
            return bookingRepository.save(booking);
        }
        throw new RuntimeException("Booking not found with id: " + id);
    }

    // DELETE BOOKING
    public String deleteBooking(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            // Make car available again
            Optional<RentalCar> car = rentalCarRepository
                    .findById(booking.get().getCarId());
            if (car.isPresent()) {
                car.get().setAvailability(true);
                rentalCarRepository.save(car.get());
            }
            bookingRepository.deleteById(id);
            return "Booking cancelled successfully with id: " + id;
        }
        throw new RuntimeException("Booking not found with id: " + id);
    }
}
