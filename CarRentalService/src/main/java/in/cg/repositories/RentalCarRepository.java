package in.cg.repositories;

import in.cg.model.RentalCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalCarRepository extends JpaRepository<RentalCar, Long> {

    List<RentalCar> findByAvailability(Boolean availability);

    List<RentalCar> findByCarType(String carType);

    RentalCar findByRegistrationNumber(String registrationNumber);
}