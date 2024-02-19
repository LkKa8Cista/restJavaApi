package upskill.pt.CarDealerShip.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upskill.pt.CarDealerShip.Models.CarBrand;

@Repository
public interface CarBrandData extends JpaRepository<CarBrand, Integer> {
}
