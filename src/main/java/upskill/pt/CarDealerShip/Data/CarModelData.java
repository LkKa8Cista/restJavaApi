package upskill.pt.CarDealerShip.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upskill.pt.CarDealerShip.Models.CarModel;

@Repository
public interface CarModelData extends JpaRepository<CarModel, Integer> {
}
