package upskill.pt.CarDealerShip.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upskill.pt.CarDealerShip.Models.Car;

@Repository
public interface CarData extends JpaRepository<Car, Integer> {
}
