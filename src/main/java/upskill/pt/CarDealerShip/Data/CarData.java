package upskill.pt.CarDealerShip.Data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import upskill.pt.CarDealerShip.Models.Car;

@Repository
public interface CarData extends JpaRepository<Car, Integer> {
    @Query("select d from Car d where d.status = Status.IN_STOCK")
    Page<Car> findInStockCars(Pageable page);

    @Query("select d from Car d where d.seller.id = :id")
    Page<Car> findCarsSoldBy(Pageable page, int id);

    @Query("SELECT c FROM Car c WHERE c.model.brand.name = :name")
    Page<Car> findCarsByBrand(Pageable page, String name);
//    @Query("SELECT c FROM Car c, CarBrand b, CarModel m WHERE c.model.id  = model.id and m.brand.name like :name")

    @Query(value = "SELECT * FROM Car WHERE TYPE = :type", nativeQuery = true)
    Page<Car> findCarsByType(Pageable page, int type);

}
