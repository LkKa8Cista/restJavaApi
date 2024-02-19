package upskill.pt.CarDealerShip.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upskill.pt.CarDealerShip.Models.Seller;

@Repository
public interface SellerData extends JpaRepository<Seller, Integer> {
}
