package upskill.pt.CarDealerShip.DTOs;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import upskill.pt.CarDealerShip.Models.CarBrand;
import upskill.pt.CarDealerShip.Models.CarModel;
import upskill.pt.CarDealerShip.Models.Seller;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarModelDTO extends RepresentationModel<CarModelDTO> {

    public int id;
    public String name;
    public CarBrand brand;

    public static CarModelDTO toCarModelDTO(CarModel carModel){
        return new CarModelDTO(carModel.getId(), carModel.getName(), carModel.getBrand());
    }

}
