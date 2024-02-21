package upskill.pt.CarDealerShip.DTOs;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import upskill.pt.CarDealerShip.Enums.Enumerators;
import upskill.pt.CarDealerShip.Models.CarBrand;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarBrandDTO extends RepresentationModel<CarBrandDTO> {
    public int id;
    public String name;
    public Enumerators.Country country;

    public static CarBrandDTO toCarBrandDTO(CarBrand carBrand){
        return new CarBrandDTO(carBrand.getId(), carBrand.getName(), carBrand.getCountry());
    }
}
