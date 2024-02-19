package upskill.pt.CarDealerShip.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upskill.pt.CarDealerShip.Models.Color;

@Repository
public interface ColorData extends JpaRepository<Color, Integer> {
}
